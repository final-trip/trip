package com.ssafy.board.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	private BoardMapper boardMapper;

	private S3Client amazonS3Client;

	// S3 버킷 이름
	@Value("${cloud.aws.s3.bucket}")
	public String bucket;

	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper, S3Client amazonS3Client) {
		super();
		this.boardMapper = boardMapper;
		this.amazonS3Client = amazonS3Client;

	}

	@Override
	@Transactional
	public void writeArticle(BoardDto boardDto) throws Exception {
		System.out.println("글입력 전 dto : " + boardDto);
		boardMapper.writeArticle(boardDto);
		System.out.println("글입력 후 dto : " + boardDto);
//		List<FileInfoDto> fileInfos = boardDto.getFileInfos();
//
//		if (fileInfos != null && !fileInfos.isEmpty()) {
//			boardMapper.registerFile(boardDto);
//		}

	}

	@Override
	public List<BoardDto> listArticle(
//			Map<String, String> map
	) throws Exception {
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "b.user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
//		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
//		param.put("start", start);
//		param.put("listsize", SizeConstant.LIST_SIZE);

		return boardMapper.listArticle();
//		return boardMapper.listArticle(param);
	}
//
//	@Override
//	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
//		PageNavigation pageNavigation = new PageNavigation();
//
//		int naviSize = SizeConstant.NAVIGATION_SIZE;
//		int sizePerPage = SizeConstant.LIST_SIZE;
//		int currentPage = Integer.parseInt(map.get("pgno"));
//
//		pageNavigation.setCurrentPage(currentPage);
//		pageNavigation.setNaviSize(naviSize);
////		
//		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
//		
//		int totalCount = boardMapper.getTotalArticleCount(param);
//		
// 		pageNavigation.setTotalCount(totalCount);
//		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//		pageNavigation.setTotalPageCount(totalPageCount);
//		boolean startRange = currentPage <= naviSize;
//		pageNavigation.setStartRange(startRange);
//		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//		pageNavigation.setEndRange(endRange);
//		pageNavigation.makeNavigator();
//
//		return pageNavigation;
//	}

	@Override
	public BoardDto getArticle(int articleNo) throws Exception {
		return boardMapper.getArticle(articleNo);
	}

//
//	@Override
//	public void updateHit(int articleNo) throws Exception {
//		boardMapper.updateHit(articleNo);
//	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws Exception {
		// TODO : BoardDaoImpl의 modifyArticle 호출
		boardMapper.modifyArticle(boardDto);
	}

	@Override
	@Transactional
	public void deleteArticle(int articleNo) throws Exception {
		// TODO : BoardDaoImpl의 deleteArticle 호출
		boardMapper.deleteFile(articleNo);
		boardMapper.deleteArticle(articleNo);

	}

	@Override
	public List<BoardDto> alllistArticle() throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.alllistArticle();

	}

	@Override
	public List<FileInfoDto> getfilelist(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.getfilelist(articleNo);
	}

	@Override
	public void toggleLike(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("articleNo" + articleNo);
		boardMapper.toggleLike(articleNo);
	}

	// 로컬에 파일 업로드 하기
	private File convert(MultipartFile file) throws Exception {
		File convertFile = new File(System.getProperty("user.dir") + "/" + file.getOriginalFilename());
		if (convertFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
			try (FileOutputStream fos = new FileOutputStream(convertFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기
																				// 위함
				fos.write(file.getBytes());
			}
			return convertFile;
		}
		return null;
	}

	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		return file;
	}

	// S3로 파일 업로드하기
	public String registerfile(MultipartFile uploadFile, String dirName, int articleNo) throws Exception {

		// 확장자
		File file = convertMultipartFileToFile(uploadFile);

		String uploadName = file.getName();
		log.debug("uppppppp" + uploadName);
		String extension = uploadName.substring(uploadName.lastIndexOf(".") + 1);
		log.debug("extttttt" + extension);
		extension = extension.toLowerCase();
		// 이미지 파일 확장자가 아닌 경우 exception 발생.
		if (!extension.equals("bmp") && !extension.equals("rle") && !extension.equals("dib")
				&& !extension.equals("jpeg") && !extension.equals("jpg") && !extension.equals("png")
				&& !extension.equals("gif") && !extension.equals("jfif") && !extension.equals("tif")
				&& !extension.equals("tiff") && !extension.equals("raw")) {
			throw new IllegalStateException("이미지 확장자가 아닙니다.");
		}

		String fileName = dirName + "/" + UUID.randomUUID() + "." + extension; // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(file, fileName); // s3로 업로드
		removeNewFile(file);
		log.debug("lllllllllllllll" + uploadImageUrl);
		String key = fileName.replace(dirName + "/", ""); // 키 값 저장.
// of,sf,articleno
		log.debug("originalfileeeeeeeeee" + uploadFile.getName());
		String original_file = uploadFile.getName();
		String save_file = uploadImageUrl;
		boardMapper.registerfile(original_file, save_file, articleNo);

		return uploadImageUrl;
	}

	public String putS3(File uploadFile, String fileName) {
		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).build();
		amazonS3Client.putObject(request, uploadFile.toPath());

		return amazonS3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(fileName)).toExternalForm();
	}

//	 로컬에 저장된 이미지 지우기
	public void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("File delete success");
			return;
		}
		log.info("File delete fail");
	}

}
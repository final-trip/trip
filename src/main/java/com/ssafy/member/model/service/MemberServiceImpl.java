package com.ssafy.member.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j

public class MemberServiceImpl implements MemberService {

	private MemberMapper memberMapper;
	private S3Client amazonS3Client;

	// S3 버킷 이름
	@Value("${cloud.aws.s3.bucket}")
	public String bucket;

	@Autowired
	public MemberServiceImpl(MemberMapper memberMapper, S3Client amazonS3Client) {
		super();
		this.memberMapper = memberMapper;
		this.amazonS3Client = amazonS3Client;

	}

	@Override
	public int idCheck(String userId) throws Exception {
		return memberMapper.idCheck(userId);
	}

	@Override
	public void joinMember(MemberDto memberDto) throws Exception {
		memberMapper.joinMember(memberDto);
	}

	@Override
	public MemberDto loginMember(MemberDto memberDto) throws Exception {
		return memberMapper.loginMember(memberDto);
	}

	/* ADMIN */
	@Override
	public List<MemberDto> listMember() throws Exception {
		return memberMapper.listMember();
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return memberMapper.getMember(userId);
	}

	@Override
	public void updateMember(MemberDto memberDto) throws Exception {

		memberMapper.updateMember(memberDto);
	}

	@Override
	public void deleteMember(String userId) throws Exception {
		memberMapper.deleteMember(userId);
	}

	public static File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
		File file = new File(multipartFile.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		return file;
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

	// S3로 파일 업로드하기
	public String registerfile(MultipartFile uploadFile, String dirName, String userId) throws Exception {

		File file = convertMultipartFileToFile(uploadFile); // 확장자
		String uploadName = file.getName();
		log.debug("uuuuuuuuuu" + uploadName);
		log.debug("uuuuuuuuuu" + file.getAbsolutePath());

		String extension = uploadName.substring(uploadName.lastIndexOf(".") + 1);
		extension = extension.toLowerCase();
		log.debug("extensionnnnnnn	" + extension);
		// 이미지 파일 확장자가 아닌 경우 exception 발생.
		if (!extension.equals("bmp") && !extension.equals("rle") && !extension.equals("dib")
				&& !extension.equals("jpeg") && !extension.equals("jpg") && !extension.equals("png")
				&& !extension.equals("gif") && !extension.equals("jfif") && !extension.equals("tif")
				&& !extension.equals("tiff") && !extension.equals("raw")) {
			throw new IllegalStateException("이미지 확장자가 아닙니다.");
		}

		String fileName = dirName + "/" + UUID.randomUUID() + "." + extension; // S3에 저장된 파일 이름
		log.debug("filenameeeee " + fileName);
		String uploadImageUrl = putS3(file, fileName); // s3로 업로드
		removeNewFile(file);
		log.debug("lllllllllllllll" + uploadImageUrl);
		String key = fileName.replace(dirName + "/", ""); // 키 값 저장.
// of,sf,articleno
		log.debug("originalfileeeeeeeeee" + uploadFile.getName());
		String original_file = uploadFile.getName();
		String imgurl = uploadImageUrl;
		memberMapper.registerfile(uploadImageUrl, userId);

		return uploadImageUrl;
	}

//	 로컬에 저장된 이미지 지우기
	public void removeNewFile(File targetFile) {
		if (targetFile.delete()) {
			log.info("File delete success");
			return;
		}
		log.info("File delete fail");
	}

//	public String putS3(File uploadFile, String fileName) {
//		amazonS3Client.putObject(
//				new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//		return amazonS3Client.getUrl(bucket, fileName).toString();
//	}
	public String putS3(File uploadFile, String fileName) {
		log.debug("innnn " + uploadFile.getAbsolutePath() + uploadFile.getName());
		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).build();
		amazonS3Client.putObject(request, uploadFile.toPath());
		log.debug("put " + uploadFile.getAbsolutePath());
		return amazonS3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(fileName)).toExternalForm();
	}
	
	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}
	
	@Override
	public MemberDto userInfo(String userId) throws Exception {
		return memberMapper.userInfo(userId);
	}
	
	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return memberMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}

//	@Override
//	public String modifyimg(File fileInfos, String string, String userId) throws Exception {
//		// TODO Auto-generated method stub
//		return memberMapper.modifyimg(fileInfos, string, userId);
//	}

//	@Override
//	public MemberDto getmemberinfo(String userId) throws Exception {
//		// TODO Auto-generated method stub
//		return memberMapper.getmemberinfo(userId);
//	}

}
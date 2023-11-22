package com.ssafy.member.model.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	public MemberDto loginMember(Map<String, String> map) throws Exception {
		return memberMapper.loginMember(map);
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

	// S3로 파일 업로드하기
	public String registerfile(File uploadFile, String dirName, String userId) throws Exception {

		// 확장자
		String uploadName = uploadFile.getName();
		String extension = uploadName.substring(uploadName.lastIndexOf(".") + 1);
		extension = extension.toLowerCase();

		// 이미지 파일 확장자가 아닌 경우 exception 발생.
		if (!extension.equals("bmp") && !extension.equals("rle") && !extension.equals("dib")
				&& !extension.equals("jpeg") && !extension.equals("jpg") && !extension.equals("png")
				&& !extension.equals("gif") && !extension.equals("jfif") && !extension.equals("tif")
				&& !extension.equals("tiff") && !extension.equals("raw")) {
			throw new IllegalStateException("이미지 확장자가 아닙니다.");
		}

		String fileName = dirName + "/" + UUID.randomUUID() + "." + extension; // S3에 저장된 파일 이름
		String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
//		removeNewFile(uploadFile);
		log.debug("lllllllllllllll" + uploadImageUrl);
		String key = fileName.replace(dirName + "/", ""); // 키 값 저장.
// of,sf,articleno
		log.debug("originalfileeeeeeeeee" + uploadFile.getName());
		String original_file = uploadFile.getName();
		String imgurl = uploadImageUrl;
		memberMapper.registerfile(uploadImageUrl, userId);

		return uploadImageUrl;
	}

//	public String putS3(File uploadFile, String fileName) {
//		amazonS3Client.putObject(
//				new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
//		return amazonS3Client.getUrl(bucket, fileName).toString();
//	}
	public String putS3(File uploadFile, String fileName) {
		PutObjectRequest request = PutObjectRequest.builder().bucket(bucket).key(fileName).build();
		amazonS3Client.putObject(request, uploadFile.toPath());

		return amazonS3Client.utilities().getUrl(builder -> builder.bucket(bucket).key(fileName)).toExternalForm();
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
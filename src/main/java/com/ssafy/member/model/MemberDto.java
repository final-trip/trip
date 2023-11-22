package com.ssafy.member.model;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비번, 이름을 가진   Domain Class")

public class MemberDto {

	@ApiModelProperty(value = "회원아이디")
	private String userId;
	@ApiModelProperty(value = "회원이름")
	private String userName;

	@ApiModelProperty(value = "회원비밀번호")
	private String userPwd;

	@ApiModelProperty(value = "가입일")
	private String joinDate;

	@ApiModelProperty(value = "가입일")
	private String address;

	private String imgurl;

	private MultipartFile imgfile;

	public File getFiles() {
		if (imgfile != null && !imgfile.isEmpty()) {
			String filePath = "C:\\Users\\SSAFY\\Downloads\\" + imgfile.getOriginalFilename();
			this.imgurl = filePath;

			return new File(filePath);
		} else {
			return null; // Or handle the case when no file is uploaded
		}
	}

}
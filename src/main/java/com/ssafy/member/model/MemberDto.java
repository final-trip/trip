package com.ssafy.member.model;

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

	@ApiModelProperty(value = "주소")
	private String address;
	
	@ApiModelProperty(value = "위도")
	private double lat;

	@ApiModelProperty(value = "경도")
	private double lng;
}
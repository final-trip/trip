package com.ssafy.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@ApiModel(value = "MemberDto (회원정보)", description = "회원 정보를 가진 Domain Class")
public class MemberDto {
    @ApiModelProperty(value = "회원 ID")
    private int member_id; // 데이터베이스 필드에 맞게 이름 변경

    @ApiModelProperty(value = "회원 이름")
    private String userName;

    @ApiModelProperty(value = "회원 아이디")
    private String userId;

    @ApiModelProperty(value = "회원 비밀번호")
    private String userPwd;

    @ApiModelProperty(value = "가입일")
    private String joinDate;

    @ApiModelProperty(value = "주소")
    private String address;
}

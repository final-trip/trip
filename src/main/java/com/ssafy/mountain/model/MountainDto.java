package com.ssafy.mountain.model;

import java.sql.Date;
import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@ApiModel(value = "MountainDto (산 정보)", description = "산 정보를 가진 class")

public class MountainDto {
	@ApiModelProperty(value = "산 일련 번호")
	private int mntilistno;

	@ApiModelProperty(value = "산정복한 사람")
	private String memberid;

	@ApiModelProperty(value = "산 이름")
	private String mntiname;

	@ApiModelProperty(value = "산의 상세 정보")
	private String mntidetails;

	@ApiModelProperty(value = "산의 주소")
	private String mntiadd;

	@ApiModelProperty(value = "산의 높이")
	private int mntihigh;

	@ApiModelProperty(value = "시도 코드")
	private int sido_code;

	@ApiModelProperty(value = "구군 코드")
	private int gugun_code;

	@ApiModelProperty(value = "산 이미지 URL")
	private String mntiimg;

	@ApiModelProperty(value = "산 정복한숫자")
	private int memberconquerednum;

	@ApiModelProperty(value = "최근 산 정복한날짜")
	private Date conquereddate;
	
	@ApiModelProperty(value = "위도")
	private double lat;
	
	@ApiModelProperty(value = "경도")
	private double lng;

}

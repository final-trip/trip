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

@ApiModel(value = "MountainDto (산 정복한 사람 랭킹)", description = "산 정보를 가진 class")

public class MountainrankingResponseDto {

	@ApiModelProperty(value = "산정복한 사람")
	private String userName;

	@ApiModelProperty(value = "산 정복한숫자")
	private int memberconquerednum;

	@ApiModelProperty(value = "랭킹")
	private int ranks;

	@ApiModelProperty(value = "최근 산 정복한날짜")
	private Date conquereddate;

}

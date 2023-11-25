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

public class MountainrankingRequestDto {
	@ApiModelProperty(value = "산 일련 번호")
	private int mntilistno;

}

package com.ssafy.mountain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@ApiModel(value = "MountainDto (회원정보)", description = "산 정보를 가진 class")

public class MountainDto {
	@ApiModelProperty(value = "산코드 ")
	private int mntnid;

	@ApiModelProperty(value = "산명 ")
	private String mntnnm;
	@ApiModelProperty(value = "100대명산 선정이유 ")
	private String hndfmsmtnslctnrson;
	@ApiModelProperty(value = "산정보소재지(소재지) ")
	private String mntninfopoflc;
	@ApiModelProperty(value = "산정보 높이 ")
	private int mntninfohght;

}

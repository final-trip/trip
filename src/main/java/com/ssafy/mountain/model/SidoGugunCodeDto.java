package com.ssafy.mountain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "SidoGugunCodeDto : 시도, 구군정보", description = "시도, 구군의 이름을 나타낸다.")
public class SidoGugunCodeDto {

	@ApiModelProperty(value = "시도코드")
	private String sido_code;
	@ApiModelProperty(value = "시도이름")
	private String sido_name;
	@ApiModelProperty(value = "구군코드")
	private String gugun_node;
	@ApiModelProperty(value = "구군이름")
	private String gugun_name;
	
}

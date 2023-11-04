package com.ssafy.wishlist.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

@ApiModel(value = "WishListDto (계획정보)", description = "계획정보   Domain Class")

public class WishListDto {
	
	private int wish_list_id;
	
	private String userId;
	
	private int mntilistno;

}

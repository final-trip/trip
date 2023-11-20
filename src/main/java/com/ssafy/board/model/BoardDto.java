package com.ssafy.board.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor


@ApiModel(value = "BoardDto (게시판정보)", description = "게시판 내용을 가진 Domain Class")
public class BoardDto {
	
	@ApiModelProperty(value = "게시판 번호")
	private int articleNo;
	
	@ApiModelProperty(value = "유저 번호")
	private String userId;
	
	@ApiModelProperty(value = "유저 이름")
	private String userName;

	@ApiModelProperty(value = "게시판 제목")
	private String subjects;
	
	@ApiModelProperty(value = "게시판 내용")
	private String content;
	
	@ApiModelProperty(value = "게시판 번호")
	private String registerTime;
	

	@ApiModelProperty(value = "게시판 파일")
	private List<FileInfoDto> fileInfos;
	
	private int likes;

}
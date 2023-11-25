package com.ssafy.comment.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

@ApiModel(value = "CommentDto (댓글정보)", description = "댓글 내용을 가진 Domain Class")
public class CommentDto {

	@ApiModelProperty(value = "댓글 번호")
	private int idcomments;

	@ApiModelProperty(value = "유저 번호")
	private String userId;

	@ApiModelProperty(value = "유저 이름")
	private int articleNo;

	private String content;

}
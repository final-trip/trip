package com.ssafy.board.model;

import com.ssafy.board.model.FileInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@ApiModel(value = "BoardDto (게시판정보)", description = "게시판 내용을 가진 Domain Class")
public class BoardDto {
    @ApiModelProperty(value = "게시판 번호")
    private int board_id; // board_id 필드 추가

    @ApiModelProperty(value = "게시판 번호")
    private int articleNo;

    @ApiModelProperty(value = "게시판 제목")
    private String subject;

    @ApiModelProperty(value = "게시판 내용")
    private String content;

    @ApiModelProperty(value = "유저 번호")
    private String member_id; // member_id 필드 추가

    @ApiModelProperty(value = "게시판 번호")
    private String registerTime;

    @ApiModelProperty(value = "게시판 파일")
    private List<FileInfoDto> fileInfos;
}

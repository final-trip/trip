package com.ssafy.board.model;

import lombok.Data;
import lombok.Getter;

@Data
public class FileInfoDto {
	private int file_info_id;
	private int board_id;
	private String type;
	private String file_path;
	private String save_folder;
	private String original_file;
	private String save_file;

}

package com.ssafy.board.model.mapper;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;

@Mapper
public interface BoardMapper {

	void writeArticle(BoardDto boardDto) throws SQLException;
	
	void registerFile(BoardDto boardDto) throws Exception;

//	List<BoardDto> listArticle(Map<String, Object> param) throws SQLException;
	List<BoardDto> listArticle() throws SQLException;

	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
//	int getTotalArticleCount( ) throws SQLException;

	BoardDto getArticle(int articleNo) throws SQLException;

	void modifyArticle(BoardDto boardDto) throws SQLException;

	void deleteFile(int articleNo) throws Exception;

	void deleteArticle(int articleNo) throws SQLException;

	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;

	List<BoardDto> alllistArticle() throws Exception;

	List<FileInfoDto> getfilelist(int articleNo) throws Exception;

	void toggleLike(int articleNo) throws Exception;

	void registerfile(String original_file, String save_file, int articleNo) throws Exception;

}
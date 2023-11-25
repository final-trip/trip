package com.ssafy.comment.model.mapper;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.FileInfoDto;
import com.ssafy.comment.model.CommentDto;

@Mapper
public interface CommentMapper {

	void writeComment(CommentDto CommentDto) throws SQLException;

	void registerFile(CommentDto CommentDto) throws Exception;

	List<CommentDto> listComments() throws SQLException;

	int getTotalCommentCount(Map<String, Object> param) throws SQLException;

	CommentDto getComment(int idcomments) throws SQLException;

	void modifyComment(CommentDto CommentDto) throws SQLException;

	void deleteFile(int idcomments) throws Exception;

	void deleteComment(int idcomments) throws SQLException;

	List<CommentDto> alllistComments() throws Exception;

	List<FileInfoDto> getfilelist(int idcomments) throws Exception;

	void toggleLike(int idcomments) throws Exception;

	void registerfile(String original_file, String save_file, int idcomments) throws Exception;

	CommentDto getcomment(int idcomments) throws Exception;

	void modifycomment(CommentDto commentDto) throws Exception;

	void deletecomment(int idcomments) throws Exception;

	List<CommentDto> listComments(int articleNo) throws Exception;

}
package com.ssafy.comment.model.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.comment.model.CommentDto;
import com.ssafy.util.PageNavigation;

public interface CommentService {

	void writeComment(CommentDto commentDto) throws Exception;

	List<CommentDto> listComments(int articleNo) throws Exception;

	CommentDto getcomment(int idcomments) throws Exception;

	void modifycomment(CommentDto commentDto) throws Exception;

	void deletecomment(int idcomments) throws Exception;

 
}

package com.ssafy.comment.model.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.FileInfoDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.comment.model.CommentDto;
import com.ssafy.comment.model.mapper.CommentMapper;
import com.ssafy.comment.model.service.CommentService;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public void writeComment(CommentDto commentDto) throws Exception {
		// TODO Auto-generated method stub
		log.debug("writeee" + commentDto.toString());
		commentMapper.writeComment(commentDto);
	}

	@Override
	public CommentDto getcomment(int idcomments) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.getcomment(idcomments);
	}

	@Override
	public void modifycomment(CommentDto commentDto) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.modifycomment(commentDto);

	}

	@Override
	public void deletecomment(int idcomments) throws Exception {
		// TODO Auto-generated method stub
		commentMapper.deletecomment(idcomments);

	}

	@Override
	public List<CommentDto> listComments(int articleNo) throws Exception {
		// TODO Auto-generated method stub
		return commentMapper.listComments(articleNo);
	}

}
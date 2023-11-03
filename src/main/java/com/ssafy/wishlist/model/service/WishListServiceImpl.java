package com.ssafy.wishlist.model.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.service.MountainService;
import com.ssafy.wishlist.model.WishListDto;
import com.ssafy.wishlist.model.mapper.WishListMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class WishListServiceImpl implements WishListService {
	WishListMapper wishmapper;

	public WishListServiceImpl(WishListMapper wishmapper) {
		super();
		this.wishmapper = wishmapper;
	}

	@Override
	public void addwishlist(WishListDto wishListDto) throws SQLException {
		// TODO Auto-generated method stub	
		wishmapper.

	}

	@Override
	public WishListDto getwishlist(String memberid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletewishlist() throws SQLException {
		// TODO Auto-generated method stub

	}

}

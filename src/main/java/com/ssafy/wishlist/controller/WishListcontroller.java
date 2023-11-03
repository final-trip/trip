package com.ssafy.wishlist.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.service.MountainService;
import com.ssafy.wishlist.model.WishListDto;
import com.ssafy.wishlist.model.service.WishListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(tags = { "계획 컨트롤러  API V1" })

@RequestMapping("/wishlist")
public class WishListcontroller {

	private WishListService wishListService;

	public WishListcontroller(WishListService wishListService) {
		super();
		this.wishListService = wishListService;
	}

	@PostMapping("/add")
	public void addwishlist(WishListDto wishListDto) throws SQLException {
		wishListService.addwishlist(wishListDto);
	}

}

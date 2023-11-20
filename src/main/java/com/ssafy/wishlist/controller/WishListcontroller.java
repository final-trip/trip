package com.ssafy.wishlist.controller;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.member.controller.MemberController;
import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.service.MountainService;
import com.ssafy.wishlist.model.WishListDto;
import com.ssafy.wishlist.model.service.WishListService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Controller
@Api(tags = { "계획 컨트롤러  API V1" })
@Slf4j
@RequestMapping("/wishlist")
public class WishListcontroller {

	private WishListService wishListService;

	public WishListcontroller(WishListService wishListService) {
		super();
		this.wishListService = wishListService;
	}

	
	
	
	@PostMapping("/add")
	public ResponseEntity<String> registerMember(@RequestBody WishListDto wishListDto) {
		log.debug("wishListDto info : {}", wishListDto);
		try {
			wishListService.addwishlist(wishListDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("wishlist registered successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register wishlist");
		}

	}
	
	
	
	
	@GetMapping("/getwishlist")
	public ResponseEntity<List<WishListDto>> getMembers(@RequestParam("userId") String userid) throws Exception {
		List<WishListDto> wishlist = wishListService.getwishlist(userid);
		for(WishListDto wish : wishlist) {
			System.out.println(wish);
		}
		if (wishlist.isEmpty()) {
			return ResponseEntity.noContent().build();
			}
		
		return new ResponseEntity<List<WishListDto>>(wishlist, HttpStatus.OK);
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<String> login(@RequestBody Map<String, String> map) throws SQLException {
		log.debug("input values : {}", map);
		wishListService.deletewishlist(map);
		return ResponseEntity.ok("delete wishlist item successfully");
	}
	
	
	
	
}

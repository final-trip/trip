package com.ssafy.wishlist.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.wishlist.model.WishListDto;

@Mapper
public interface WishListMapper {
	void addwishlist(WishListDto wishListDto) throws SQLException;

	List<WishListDto> getwishlist(String userId) throws SQLException;

	void deletewishlist(WishListDto wishListDto) throws SQLException;
	
}

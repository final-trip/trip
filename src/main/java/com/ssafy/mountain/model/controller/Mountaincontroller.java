package com.ssafy.mountain.model.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.service.MountainService;

@Controller
@RequestMapping("/user")
public class Mountaincontroller {

	private MountainService mountainservice;

	public Mountaincontroller(MountainService mountainservice) {
		super();
		this.mountainservice = mountainservice;
	}

	@PostMapping
	public void addmountain(MountainDto mountainDto) throws SQLException {
		mountainservice.addmountain(mountainDto);
	}

	@GetMapping
	public List<MountainDto> getUnconqueredMountains(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return mountainservice.getUnconqueredMountains(memberDto);
	}

	@GetMapping
	public List<MountainDto> getUnconqueredMountainsAscendingByHeight(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return mountainservice.getUnconqueredMountainsAscendingByHeight(memberDto);
	}

	@GetMapping
	public List<MountainDto> getNearestUnconqueredMountains(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return mountainservice.getNearestUnconqueredMountains(memberDto);
	}
}

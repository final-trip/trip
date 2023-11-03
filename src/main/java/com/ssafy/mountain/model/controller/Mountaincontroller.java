package com.ssafy.mountain.model.controller;

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

@Controller
@RequestMapping("/mountain")
public class Mountaincontroller {

	private MountainService mountainservice;

	public Mountaincontroller(MountainService mountainservice) {
		super();
		this.mountainservice = mountainservice;
	}

	@PostMapping("/add")
	public ResponseEntity<String> addMountain(@RequestBody MountainDto mountainDto) {
		try {
			mountainservice.addmountain(mountainDto);
			return ResponseEntity.status(HttpStatus.CREATED).body("Mountain added successfully");
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add mountain");
		}
	}

	@GetMapping("/unconquered")
	public ResponseEntity<List<MountainDto>> getUnconqueredMountains(@RequestParam("memberId") int memberId) {
		try {
			List<MountainDto> unconqueredMountains = mountainservice.getUnconqueredMountains(memberId);
			return ResponseEntity.ok(unconqueredMountains);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@GetMapping("/all")
	public ResponseEntity<List<MountainDto>> allmountains() throws SQLException {
		List<MountainDto> allMountainDtos = mountainservice.allmountains();
		return ResponseEntity.ok(allMountainDtos);
	}

	@GetMapping("/unconquered/ascending")
	public ResponseEntity<List<MountainDto>> getUnconqueredMountainsAscendingByHeight(
			@RequestParam("memberId") int memberId) {
		try {
			List<MountainDto> unconqueredMountainsAscending = mountainservice
					.getUnconqueredMountainsAscendingByHeight(memberId);
			return ResponseEntity.ok(unconqueredMountainsAscending);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

	@GetMapping("/unconquered/nearest")
	public ResponseEntity<List<MountainDto>> getNearestUnconqueredMountains(@RequestParam("memberId") int memberId) {
		try {
			List<MountainDto> nearestUnconqueredMountains = mountainservice.getNearestUnconqueredMountains(memberId);
			return ResponseEntity.ok(nearestUnconqueredMountains);
		} catch (SQLException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
		}
	}

//
//	@PostMapping()
//	public void addmountain(MountainDto mountainDto) throws SQLException {
//		mountainservice.addmountain(mountainDto);
//	}
//
//	@GetMapping
//	public ResponseEntity<List<MountainDto>> getUnconqueredMountains(MemberDto memberDto) throws SQLException {
//		// TODO Auto-generated method stub
//		return mountainservice.getUnconqueredMountains(memberDto);
//	}
//
//	@GetMapping
//	public List<MountainDto> getUnconqueredMountainsAscendingByHeight(MemberDto memberDto) throws SQLException {
//		// TODO Auto-generated method stub
//		return mountainservice.getUnconqueredMountainsAscendingByHeight(memberDto);
//	}
//
//	@GetMapping
//	public List<MountainDto> getNearestUnconqueredMountains(MemberDto memberDto) throws SQLException {
//		// TODO Auto-generated method stub
//		return mountainservice.getNearestUnconqueredMountains(memberDto);
//	}
}

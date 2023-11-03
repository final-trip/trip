package com.ssafy.mountain.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;

public interface MountainService {
	// 산 추가
	void addmountain(MountainDto mountainDto) throws SQLException;

	// 정복하지않은산
	List<MountainDto> getUnconqueredMountains(int memberid) throws SQLException;

	// 정복하지않은산 높이로 오름차순
	List<MountainDto> getUnconqueredMountainsAscendingByHeight(int memberid) throws SQLException;

	// 정복하지않은산 가까운 순
	List<MountainDto> getNearestUnconqueredMountains(int memberid) throws SQLException;

	List<MountainDto> allmountains() throws SQLException;

	// 정복한 산 추가
	void AddConqueredMountain(int memberid, int mntilistno) throws SQLException;
}

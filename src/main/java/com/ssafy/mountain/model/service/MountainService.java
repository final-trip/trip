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
	List<MountainDto> getUnconqueredMountains(String memberid) throws SQLException;

	// 정복하지않은산 높이로 오름차순

	List<MountainDto> getUnconqueredMountainsAscendingByHeight(String memberid) throws SQLException;

	// 정복하지않은산 가까운 순
	List<MountainDto> getNearestUnconqueredMountains(String memberid) throws SQLException;

	List<MountainDto> allmountains() throws SQLException;


	List<MountainDto> getSearchResult(String word) throws SQLException;

	// 정복한 산 추가
	void AddConqueredMountain(String memberid, int mntilistno) throws SQLException;

	void Updateconquerednum(int mntilistno) throws SQLException;

	int IsconqueredMountain(String memberid,int mntilistno) throws SQLException;

<<<<<<< HEAD
	void Updateconquerednumofmountain(String memberId, int mntilistno) throws SQLException;
=======
>>>>>>> 20e22dbbf1e627307f74c608082ebaab61906728

}

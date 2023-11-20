package com.ssafy.mountain.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.SidoGugunCodeDto;

@Mapper
public interface MountainMapper {

	// 산 추가
	void AddMountain(MountainDto mountainDto) throws SQLException;

	// 정복하지않은산
	List<MountainDto> getUnconqueredMountains(String memberid) throws SQLException;

	// 정복하지않은산 높이로 오름차순
	List<MountainDto> getUnconqueredMountainsAscendingByHeight(String memberid) throws SQLException;

	// 정복하지않은산 가까운 순
	List<MountainDto> getNearestUnconqueredMountains(String memberid) throws SQLException;

	List<MountainDto> getAllMountains() throws SQLException;

	List<MountainDto> getrandom2() throws SQLException;

	List<MountainDto> getSearchResult(String word) throws SQLException;

	List<MountainDto> getConqueredMountains(String userId) throws SQLException;

	void AddConqueredMountain(String memberid, int mntilistno);

	void Updateconquerednum(int mntilistno) throws SQLException;

	void Updateconquerednumofmountain(String memberid, int mntilistno) throws SQLException;

	int IsconqueredMountain(String memberid, int mntilistno) throws SQLException;

	List<SidoGugunCodeDto> getSido() throws SQLException;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

	MountainDto getSearchResultdetail(int mntilistno) throws SQLException;

	int gettotalconquerednum(String userId) throws SQLException;

	int getmountainnum(String userId, int sido_code, int gugun_code, String word) throws SQLException;
}

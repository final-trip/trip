package com.ssafy.mountain.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.mapper.MemberMapper;
import com.ssafy.mountain.model.MountainDto;
import com.ssafy.mountain.model.mapper.MountainMapper;

@Service
public class MountainServiceImpl implements MountainService {

//	@Autowired
//	private SqlSession sqlSession;

	private MountainMapper mountainMapper;

	public MountainServiceImpl(MountainMapper mountainMapper) {
		super();
		this.mountainMapper = mountainMapper;
	}

	@Override
	public void addmountain(MountainDto mountainDto) throws SQLException {
		// TODO Auto-generated method stub
		mountainMapper.AddMountain(mountainDto);
	}

	@Override
	public List<MountainDto> getUnconqueredMountains(String memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getUnconqueredMountains(memberid);
	}

	@Override
	public List<MountainDto> getUnconqueredMountainsAscendingByHeight(String memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getUnconqueredMountainsAscendingByHeight(memberid);
	}

	@Override
	public List<MountainDto> getNearestUnconqueredMountains(String memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getNearestUnconqueredMountains(memberid);
	}

	@Override
	public List<MountainDto> allmountains() throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getAllMountains();
	}

	@Override
	public List<MountainDto> getSearchResult(String word) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getSearchResult(word);
	}

	public void AddConqueredMountain(String userId, int mntilistno) throws SQLException {
		// TODO Auto-generated method stub
		mountainMapper.AddConqueredMountain(userId, mntilistno);
	}

	@Override
	public void Updateconquerednum(int mntilistno) throws SQLException {
		// TODO Auto-generated method stub
		mountainMapper.Updateconquerednum(mntilistno);
	}

	@Override
	public int IsconqueredMountain(String memberid, int mntilistno) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.IsconqueredMountain(memberid, mntilistno);
	}

	@Override
	public void Updateconquerednumofmountain(String memberId, int mntilistno) throws SQLException {
		// TODO Auto-generated method stub
		mountainMapper.Updateconquerednumofmountain(memberId, mntilistno);

	}

	@Override
	public List<MountainDto> getrandom2() throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getrandom2();

	}

	@Override
	public List<MountainDto> getConqueredMountains(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getConqueredMountains(userId);
	}

	@Override
	public MountainDto getSearchResultdetail(String mntiname) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getSearchResultdetail(mntiname);
	}

}

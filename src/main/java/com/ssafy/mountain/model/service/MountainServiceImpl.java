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
	public List<MountainDto> getUnconqueredMountains(int memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getUnconqueredMountains(memberid);
	}

	@Override
	public List<MountainDto> getUnconqueredMountainsAscendingByHeight(int memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getUnconqueredMountainsAscendingByHeight(memberid);
	}

	@Override
	public List<MountainDto> getNearestUnconqueredMountains(int memberid) throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getNearestUnconqueredMountains(memberid);
	}

	@Override
	public List<MountainDto> allmountains() throws SQLException {
		// TODO Auto-generated method stub
		return mountainMapper.getAllMountains();
	}

	@Override
	public void AddConqueredMountain(int memberid, int mntilistno) throws SQLException {
		// TODO Auto-generated method stub
		mountainMapper.AddConqueredMountain(memberid, mntilistno);
	}

}

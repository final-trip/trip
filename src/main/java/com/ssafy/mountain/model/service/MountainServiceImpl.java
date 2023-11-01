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
		mountainMapper.addmountain(mountainDto);
	}

	@Override
	public List<MountainDto> getUnconqueredMountains(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MountainDto> getUnconqueredMountainsAscendingByHeight(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MountainDto> getNearestUnconqueredMountains(MemberDto memberDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

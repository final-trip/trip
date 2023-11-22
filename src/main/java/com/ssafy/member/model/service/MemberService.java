package com.ssafy.member.model.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int idCheck(String userId) throws Exception;

	void joinMember(MemberDto memberDto) throws Exception;

	MemberDto loginMember(Map<String, String> map) throws Exception;

	/* Admin */
	List<MemberDto> listMember() throws Exception;

	MemberDto getMember(String userId) throws Exception;

	void updateMember(MemberDto memberDto) throws Exception;

	void deleteMember(String userid) throws Exception;

	public String registerfile(File uploadFile, String dirName, String userId) throws Exception;

//	void updateMember(MemberDto memberDto) throws Exception;

//	String modifyimg(File fileInfos, String string, String userId) throws Exception;

//	MemberDto getmemberinfo(String userId) throws Exception;

}
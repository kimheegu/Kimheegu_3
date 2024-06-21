package com.example.infomation.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.infomation.member.dao.MemberDAO;
import com.example.infomation.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation = Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public List listMembers() throws Exception{
		List membersList = null;
		membersList = memberDAO.selectAllMemberList();
		return membersList;
	}
	
	@Override
	public int addMember(MemberVO member) throws Exception{
		return memberDAO.insertMember(member);
	}
	
	@Override
	public int removeMember(String id) throws Exception{
		return memberDAO.deleteMember(id);
	}
	
	@Override
	public MemberVO login(MemberVO memberVO) throws Exception{
		return memberDAO.loginById(memberVO);
	}
	
	@Override
	public int updateMember(MemberVO memberVO) throws Exception{
		return memberDAO.updateMember(memberVO);
	}
}

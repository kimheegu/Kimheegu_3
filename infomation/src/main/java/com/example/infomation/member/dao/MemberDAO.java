package com.example.infomation.member.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.infomation.member.vo.MemberVO;

@Mapper
@Repository("memberDAO")
public interface MemberDAO {
	public List<MemberVO> selectAllMemberList() throws DataAccessException;
	public int insertMember(MemberVO memberVO) throws DataAccessException;
	public int deleteMember(String id) throws DataAccessException;
	public MemberVO loginById(MemberVO memberVO) throws DataAccessException;
	public int updateMember(MemberVO memberVO) throws DataAccessException;
}

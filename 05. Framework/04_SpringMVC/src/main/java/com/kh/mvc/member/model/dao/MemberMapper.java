package com.kh.mvc.member.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.mvc.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member findMemberById(@Param("id") String id);

	int insertMember(Member member);

	int updateMember(Member member);

	int deleteMember(int no);

//	// ▼ 이렇게 쓸 수도 있지만, 쿼리문이 길어지면 가독성이 떨어지므로 비추
//	@Select("select * from member")
//	List<Member> findAll();
}

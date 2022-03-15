package com.kh.mybatis.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {
	public int getMemberCount(SqlSession session) {
		
		/*
		 * SqlSession 객체가 제공하는 메소드를 통해 SQL을 실행 시킴
		 * 객체 한 개를 조회하기 위해, SqlSession 객체의 selectOne() 메소드 사용
		 *      ▷ 최대 두 개의 매개값을 받을 수 있음
		 * 		- 첫 번째 매개값은 쿼리문이 존재하는 경로임 ("Mapper네임스페이스.쿼리문아이디") 
		 *      - 두 번째 매개값은 쿼리문에서 사용될 파라미터 객체
		 */
		return session.selectOne("memberMapper.selectCount");
	}

	public List<Member> findAll(SqlSession session) {
		
		// ▼ 값이 있으면 List, 없으면 빈 List 객체를 줄 것임
		return session.selectList("memberMapper.selectAll");
	}

	public Member findMemberById(SqlSession session, String id) {

		// ▼ 값이 있으면 resultMap 에설정된 객체 타입으로, 없으면 null을 줄 것임
		return session.selectOne("memberMapper.selectMemberById", id);
	}

	public int insertMember(SqlSession session, Member member) {
		
				
		return session.insert("memberMapper.insertMember", member);
	}

	public int updateMember(SqlSession session, Member member) {
		
		return session.update("memberMapper.updateMember", member);
	}

	public int delete(SqlSession session, int no) {
		
		return session.delete("memberMapper.deleteMember", no);
	}
}

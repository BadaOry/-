package com.kh.mvc.member.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.mvc.member.model.dao.MemberMapper;
import com.kh.mvc.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
//	@Autowired
//	private SqlSession session;
	
	@Override
	public Member findMemberById(String id) {
		
		return mapper.findMemberById(id);
	}
	
	@Override
	public Member login(String id, String password) {
		Member member = null;
/*
//		member= dao.findMemberById(session, id);
		member= mapper.findMemberById(id);
//		System.out.println(mapper.findAll());
		
		// ▼ 매번 암호화가 바뀌는걸 알 수 있는 코드
		System.out.println(passwordEncoder.encode(password));
//		System.out.println(passwordEncoder.encode(password));
		System.out.println(member.getPassword());
		// ▼ password 를 원문 / 입력한거 비교해서 matches 메소드를 쓰면 true, false 를 리턴해줌
		System.out.println(passwordEncoder.matches(password, member.getPassword()));
		
		// ▼ 이제 이 방식으로는 로그인 로직이 안돌아감
		if (member != null && member.getPassword().equals(password)) {
			
			return member;
		} else {
			
			return null;
		}
*/
//		member= mapper.findMemberById(id);
		member= this.findMemberById(id);
		
		return member != null && 
				passwordEncoder.matches(password, member.getPassword()) ? member : null;
		
	}

	@Override
	@Transactional
	public int save(Member member) {
		int result = 0;
		
		if(member.getNo() != 0) {
			// ▼ update
			result = mapper.updateMember(member);
		} else {
			// ▼ 패스워드 암호화
			member.setPassword(passwordEncoder.encode(member.getPassword()));
			// ▼ insert
			result = mapper.insertMember(member);
		}
		
		// ▼ 일부러 에러 내보는 코드
//		if(true) {
//			throw new RuntimeException();
//		}
		
		return result;
	}

	@Override
	public Boolean isDuplicateId(String id) {
		
		// ▼ null 이 아니라는건, true 가 반환되었다는 얘기 
		//   = 중복된 아이디가 있다는 뜻
//		return mapper.findMemberById(id) != null;
		return this.findMemberById(id) != null;
	}

	@Override
	public int delete(int no) {

		return mapper.deleteMember(no);
	}



}

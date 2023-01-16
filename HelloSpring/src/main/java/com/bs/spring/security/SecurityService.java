package com.bs.spring.security;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.vo.Member;

public class SecurityService implements UserDetailsService {
										//UserDetailsService 인터페이스 추가

	private MemberDao dao;
	private SqlSessionTemplate session;
	
	@Autowired	
	public SecurityService(MemberDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub		
		
		Member m=dao.selectmemberById(session, Member.builder().userId(username).build());	//username에 id가 알아서 들어감		
				
		//로그인 실패시 처리 
		if(m==null) throw new UsernameNotFoundException(username);
		
		return m;
	}

}

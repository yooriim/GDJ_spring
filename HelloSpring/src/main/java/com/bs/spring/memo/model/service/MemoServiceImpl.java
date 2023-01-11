package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
	private MemoDao dao;
	private SqlSessionTemplate session;
	
	@Autowired
	public MemoServiceImpl(MemoDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}


	@Override
	public List<Memo> memoList() {
		// TODO Auto-generated method stub
		return dao.memoList(session);
	}


	@Override
	public int insertMemo(Memo m) {
		// TODO Auto-generated method stub
		return dao.insertMemo(session,m);
	}


	@Override
	public int selectMemoListCount() {
		// TODO Auto-generated method stub
		return dao.selectMemoListCount(session);
	}
	
	@Override
	public List<Memo> selectMemoListPage(Map<String,Integer> param){
		return dao.selectMemoListPage(session,param);
	}
	
	
}

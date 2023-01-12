package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDao dao;	
	private SqlSessionTemplate session;
	
	@Autowired
	public BoardServiceImpl(BoardDao dao,SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> boardList() {
		// TODO Auto-generated method stub
		return dao.boardList(session);
	}

	@Override
	public List<Board> boardListPage(Map<String, Integer> param) {
		// TODO Auto-generated method stub
		return dao.boardListPage(session,param);
	}

	@Override
	public int boardListCount() {
		// TODO Auto-generated method stub
		return dao.boardListCount(session);
	}

	@Override
	public int insertBoard(Board b) {
		// TODO Auto-generated method stub
		return dao.insertBoard(session,b);
	}

	@Override
	public Board viewBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.viewBoard(session, boardNo);
	}
	
	
	

	
}

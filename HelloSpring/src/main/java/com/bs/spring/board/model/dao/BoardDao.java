package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.vo.Board;

public interface BoardDao {

	List<Board> boardList(SqlSessionTemplate session);
	
	List<Board> boardListPage(SqlSessionTemplate session,Map<String,Integer> param);
	
	int boardListCount(SqlSessionTemplate session);
	
	int insertBoard(SqlSessionTemplate session, Board b);
	
	Board viewBoard(SqlSessionTemplate session, int boardNo);
}

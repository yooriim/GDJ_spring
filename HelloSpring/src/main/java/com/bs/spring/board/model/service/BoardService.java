package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.vo.Board;

public interface BoardService {
	
	List<Board> boardList();
	
	List<Board> boardListPage(Map<String,Integer> param);
	
	int boardListCount();
	
	int insertBoard(Board b);
	
	Board viewBoard(int boardNo);
	
}

package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
	//@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
	public int insertBoard(Board b) {
		//1. 게시글을 등록하고
		//2. 첨부파일을 등록 
		//int boardNo=0;
		log.debug("insert전 : "+b.getBoardNo());
		int result=dao.insertBoard(session,b);
		//if(result>0) dao.selectBoardSeq(session); //attachment에서 fk로 쓰일 번호 가져오기! 근디 이렇게 하면 db에 넘 자주가니까 아래 for문사용
//		b.setBoardNo(boardNo);
		log.debug("insert후 : "+b.getBoardNo());
		
		if(result>0) {
			for(Attachment a : b.getFiles()){ 
				a.setBoardNo(b);
				result=dao.insertAttachment(session,a); 
			}			
		}else {
			throw new RuntimeException("입력실패!");
		}
		 
		
		return result;
	}

	@Override
	public Board viewBoard(int boardNo) {
		// TODO Auto-generated method stub
		return dao.viewBoard(session, boardNo);
	}
	
	
	

	
}

package com.bs.spring.board.model.vo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dao.BoardDao;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> boardList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("board.boardList");
	}

	@Override
	public List<Board> boardListPage(SqlSessionTemplate session, Map<String, Integer> param) {
		// TODO Auto-generated method stub
		return session.selectList("board.boardList",null,
				new RowBounds((param.get("cPage")-1)*param.get("numPerpage"),param.get("numPerpage")));
	}

	@Override
	public int boardListCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("board.boardListCount");
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		// TODO Auto-generated method stub
		return session.insert("board.insertBoard",b);
	}

	@Override
	public Board viewBoard(SqlSessionTemplate session, int boardNo) {
		// TODO Auto-generated method stub
		return session.selectOne("board.viewBoard",boardNo);
	}
	
	

	
	
}

package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> memoList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.memoList");
	}
	
	@Override
	public List<Memo> selectMemoListPage(SqlSessionTemplate session, Map<String,Integer> param){
		return session.selectList("memo.memoList",null,
				new RowBounds((param.get("cPage")-1)*param.get("numPerpage"),param.get("numPerpage")));		
		
	}

	@Override
	public int writeMemo(SqlSessionTemplate session, Memo m) {
		// TODO Auto-generated method stub
		return session.insert("memo.writeMemo",m);
	}

	@Override
	public int selectMemoListCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("memo.selectMemoListCount");
	}
	
	
	
}

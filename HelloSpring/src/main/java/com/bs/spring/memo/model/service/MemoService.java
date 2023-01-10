package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	
	List<Memo> memoList();
	
	int writeMemo(Memo m);
	
	int selectMemoListCount();
	
	List<Memo> selectMemoListPage(Map<String,Integer> param);
	
}

package com.team404.freeboard.service;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;

public interface FreeBoardService {

	
	public int regist(FreeBoardVO vo);	//등록
	public ArrayList<FreeBoardVO> getList(); //조회
	public FreeBoardVO getDetail(int bno); //조회
	public int Update(FreeBoardVO vo);	//변경
	public int delete(int bno);//삭제
	
}

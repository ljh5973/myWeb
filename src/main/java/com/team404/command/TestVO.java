package com.team404.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //toString, getter ,setter 자동생성
@AllArgsConstructor	//모든 멤버변수를 초기화 해주는 생성자
@NoArgsConstructor  //기본생성자
public class TestVO {
	
	
	private String name;
	private String age;
	private String data;
	private int birth;
}

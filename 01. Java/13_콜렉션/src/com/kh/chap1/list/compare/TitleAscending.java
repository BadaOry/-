package com.kh.chap1.list.compare;

import java.util.Comparator;

import com.kh.chap1.list.model.vo.Music;

public class TitleAscending implements Comparator<Music> {

	@Override
	public int compare(Music music1, Music music2) {
		/*
		 * 반환되는 정수값(music)을 가지고 정렬 기준을 잡음
		 *   - 자신과 매개값을 받아서 비교후, 정수 값 리턴
		 *   - 비교해서 같으면 0을 반환하고, 첫번째 인자가 크다면 양의 정수 반환, 
		 *     자신이 작으면 음의 정수 반환 
		 */
		
		// 문자열에서 구현되어있는 compareTo() 사용
		return music1.getTitle().compareTo(music2.getTitle());
	}	
}

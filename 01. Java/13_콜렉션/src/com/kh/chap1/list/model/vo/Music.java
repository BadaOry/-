package com.kh.chap1.list.model.vo;

public class Music implements Comparable<Music>{
	private String title;
	
	private String artist;
	
	private int ranking;
	
	public Music() {
	}

	public Music(String title, String artist, int ranking) {
		this.title = title;
		this.artist = artist;
		this.ranking = ranking;
	}
	
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public int getRanking() {
		return ranking;
	}

	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + ", ranking=" + ranking + "]";
	}

	// comparable 을 implements하면 Music에 에러뜨는데
	// ctrl 1 해서 add comparable 어쩌구 하면 됨
	@Override
	public int compareTo(Music music) {
		/*
		 * 반환되는 정수값(music)을 가지고 정렬 기준을 잡음
		 *   - 자신과 매개값으로 전달된 객체가 같은 타입인지 비교
		 *   - 비교해서 같으면 0을 반환하고, 자신이 크다면 양의 정수 반환, 
		 *     자신이 작으면 음의 정수 반환 
		 */
		
		// 랭킹 순서대로 정렬하는 코드
		int result = (this.ranking > music.ranking) ? 1 : (this.ranking == music.ranking) ? 0 : -1;
		
		return result;
	}

}

package com.kh.chap1.list.compare;

import java.util.Comparator;

import com.kh.chap1.list.model.vo.Music;

public class ArtistAscending implements Comparator<Music> {

	@Override
	public int compare(Music music1, Music music2) {
		
		int result = music1.getArtist().compareTo(music2.getArtist());
		
		if( result == 0) {
			return (music1.getRanking() > music2.getRanking()) ? 1 : (music1.getRanking() == music2.getRanking()) ? 0 : -1;
			
		}
		
		
		return result;
	}

}

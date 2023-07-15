package ch4.item20.artist;

import java.util.Random;

public class Artist implements SingerSongwriter{
	private String name;
	private String genre;
	private int clipIdx;

	public Artist(String name, String genre) {
		this.name = name;
		this.genre = genre;
		clipIdx = 1;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	@Override
	public AudioClip strum() {
		System.out.println("strum ~" + new Random().nextInt(clipIdx));
		return null;
	}

	@Override
	public void actSensitive() {
		System.out.println("actSensitive");
	}

	@Override
	public AudioClip sing(Song s) {
		AudioClip audioClip = new AudioClip(s, clipIdx++);
		System.out.println("audioClip = " + audioClip);
		return audioClip;
	}

	@Override
	public Song compose(int chartPosition) {
		Song song = Song.makeSong(chartPosition);
		System.out.println("compose = " + song);
		return song;
	}
}

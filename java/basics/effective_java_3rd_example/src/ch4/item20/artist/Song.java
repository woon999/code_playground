package ch4.item20.artist;

import java.util.stream.Stream;

public enum Song {
	SONG_1(1, "A"),
	SONG_2(2, "B"),
	SONG_3(3, "C"),
	SONG_4(4, "D"),
	SONG_5(5, "E"),
	SONG_6(6, "F"),
	SONG_7(7, "G"),
	SONG_8(8, "H"),
	SONG_9(9, "I");

	private final int chartPosition;
	private final String songName;

	private Song(int chartPosition, String songName) {
		this.chartPosition = chartPosition;
		this.songName = songName;
	}

	public static Song makeSong(int chartPosition){
		return Stream.of(Song.values())
			.filter(i -> i.getChartPosition() == chartPosition)
			.findFirst()
			.orElseThrow(IllegalStateException::new);
	}

	public int getChartPosition() {
		return chartPosition;
	}

	public String getSongName() {
		return songName;
	}

	@Override
	public String toString() {
		return "Song{" +
			"chartPosition=" + chartPosition +
			", songName='" + songName + '\'' +
			'}';
	}
}


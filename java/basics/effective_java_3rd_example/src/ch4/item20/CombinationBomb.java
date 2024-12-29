package ch4.item20;

import org.junit.jupiter.api.Test;

abstract class Singer {
	abstract void sing(String s);
}

class AbstractSinger extends Singer {
	@Override void sing(String s) { }
}

abstract class Songwriter{
	abstract void compose(int chartPosition);
}


class AbstractSongwriter extends Songwriter {
	@Override void compose(int chartPosition) { }
}

abstract class SingerSongWriter{
	abstract void strum();
	abstract void actSensitive();
}

class AbstractSingerSongWriter extends SingerSongWriter {
	private Singer singer;
	private Songwriter songwriter;

	public AbstractSingerSongWriter(Singer singer, Songwriter songwriter) {
		this.singer = singer;
		this.songwriter = songwriter;
	}

	@Override void strum() { }
	@Override void actSensitive() { }

	public Singer getSinger() {
		return singer;
	}

	public Songwriter getSongwriter() {
		return songwriter;
	}
}

public class CombinationBomb {
	@Test
	public void makeSingerSongWriter_test(){
		Singer singer = new AbstractSinger();
		Songwriter songwriter = new AbstractSongwriter();
		AbstractSingerSongWriter artist = new AbstractSingerSongWriter(singer, songwriter);

		artist.actSensitive();
		artist.strum();
		artist.getSinger().sing("abc");
		artist.getSongwriter().compose(1);
	}
}

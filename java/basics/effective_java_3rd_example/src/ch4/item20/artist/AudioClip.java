package ch4.item20.artist;

public class AudioClip {
	private Song song;
	private int clipNo;

	public AudioClip(Song song, int clipNo) {
		this.song = song;
		this.clipNo = clipNo;
	}

	public Song getSong() {
		return song;
	}

	public int getClipName() {
		return clipNo;
	}

	@Override
	public String toString() {
		return "AudioClip{" +
			"song=" + song +
			", clipNo='" + clipNo + '}';
	}
}

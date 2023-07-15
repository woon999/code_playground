package ch2.item6.adapter_pattern;

public class MediaAdapter implements MediaPlayer{
	private AdvancedMediaPlayer mediaPlayer;

	public MediaAdapter(String type) {
		if(type.equalsIgnoreCase("mp4")){
			mediaPlayer = new MP4();
		}else if(type.equalsIgnoreCase("mkv")){
			mediaPlayer = new MKV();
		}
	}

	@Override public void play(String type, String filename) {
		System.out.print("Using Adapter --> ");
		if(type.equalsIgnoreCase("mp4")){
			mediaPlayer.playMP4(filename);
		}else if(type.equalsIgnoreCase("mkv")){
			mediaPlayer.playMKV(filename);
		}

	}
}

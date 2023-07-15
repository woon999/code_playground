package ch2.item6.adapter_pattern;

public class MP4 implements AdvancedMediaPlayer {

	@Override public void playMP4(String filename){
		System.out.println("Playing MP4 file = " + filename);
	}

	@Override public void playMKV(String filename){}
}

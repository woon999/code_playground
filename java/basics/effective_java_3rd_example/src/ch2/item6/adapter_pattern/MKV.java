package ch2.item6.adapter_pattern;

public class MKV implements AdvancedMediaPlayer {

	@Override public void playMKV(String filename){
		System.out.println("Playing MKV file = " + filename);
	}

	@Override public void playMP4(String filename){}
}

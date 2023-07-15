package ch2.item6.adapter_pattern;

public class AudioPlayer implements MediaPlayer{
	MediaAdapter formatAdapter;

	@Override public void play(String type, String filename) {

		// mp3파일은 기본 셋으로 재생
		if(type.equalsIgnoreCase("mp3")){
			System.out.println("Playing mp3 file. name :" + filename);
		} else if (type.equalsIgnoreCase("mp4") || type.equalsIgnoreCase("mkv")){
			formatAdapter = new MediaAdapter(type);
			formatAdapter.play(type, filename);
		} else {
			System.out.println("Invalid Media. " + type + " format not supported");
		}


	}
}

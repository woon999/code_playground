package ch2.item6.adapter_pattern;

public class Main {
	public static void main(String[] args) {
		AudioPlayer player = new AudioPlayer();

		player.play("mp3", "abc.mp3");
		player.play("mp4", "abc.mp4");
		player.play("mkv", "abc.mkv");
		player.play("avi", "abc.avi");


	}
}

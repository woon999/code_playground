package ch2.item9.try_with_resources;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
	private static String defaultVal = "-1";
	private static final String PATH = "src/ch2.item9/try_with_resources";
	private static final int BUFFER_SIZE = 5;
	public static void main(String[] args) throws IOException {
		String data = firstLineOfFile(PATH + "/abc.txt");
		System.out.println(data);
	}

	static String firstLineOfFile(String path) {
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			return br.readLine();
		} catch (IOException e){
			return defaultVal;
		}
	}

	static void copy(String src, String dst) throws IOException{
		try(InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dst)){
			byte[] buf = new byte[BUFFER_SIZE];
			int n ;
			while((n=in.read(buf)) >= 0){
				out.write(buf, 0, n);
			}
		}
	}
}

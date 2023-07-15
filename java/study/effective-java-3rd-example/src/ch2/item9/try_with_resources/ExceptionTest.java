package ch2.item9.try_with_resources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionTest {
	private static String defaultVal = "-1";
	private static final String PATH = "src/ch2.item9/try_finally";
	public static void main(String[] args) throws IOException {
		String data = firstLineOfFile(PATH + "/exception.txt");
		System.out.println(data);
	}

	static String firstLineOfFile(String path) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			if(br.readLine().equals("throw exception")){
				throw new IOException("first Exception");
			}
			return br.readLine();
		} catch (IOException e){
			return defaultVal;
		}
	}
}

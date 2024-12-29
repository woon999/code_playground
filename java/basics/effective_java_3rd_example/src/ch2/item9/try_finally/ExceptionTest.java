package ch2.item9.try_finally;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExceptionTest {
	private static final String PATH = "src/ch2.item9/try_finally";
	public static void main(String[] args) throws IOException {
		String data = firstLineOfFile(PATH + "/exception.txt");
		System.out.println(data);
	}

	static String firstLineOfFile(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		try{
			if(br.readLine().equals("throw exception")){
				throw new IOException("first Exception"); // 첫 번째 예외
			}
			return br.readLine();
		} catch (Exception e){
			return e.getMessage();
		} finally{
			br.close();
			br.readLine(); // 두 번째 예외
		}
	}
}

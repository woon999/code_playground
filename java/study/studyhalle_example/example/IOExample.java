package com.example.study.livestudy.example;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Scanner;


// 파일 읽고 쓰기
public class IOExample {


    public static void main(String[] args) throws IOException {

        // 버퍼에 담기 위한 문자열 입력
        String s = "파일 내용 입력합니다.다다다다다";

        //바이트 배열에 문자열 저장
        byte[] b = s.getBytes();

        // 버퍼 생성 후 바이트 배열 길이만큼 공간 할당
        ByteBuffer box = ByteBuffer.allocate(b.length);

        // 버퍼에 바이트배열 저장
        box.put(b);
        // 버퍼 위치(position)는 0으로 limit와 capacity값과 같게 설정
        box.clear();

        // 쓰기 가능한 파일 생성
        FileOutputStream fos = new FileOutputStream("a1.txt");
        FileChannel out = fos.getChannel();

        // 해당 파일에 버퍼 내용 쓰기
        out.write(box);

        // 채널과 관련된 스트림 모두 close
        out.close();

        // 읽기 가능한 파일 생성
        FileInputStream fis = new FileInputStream("a1.txt");
        FileChannel in = fis.getChannel();

        // 읽을 파일 크기 만큼 공간 할당한 바이트 버퍼 생성
        ByteBuffer box2 = ByteBuffer.allocate((int)in.size());

        // 파일의 내용을 읽어 버퍼에 담음
        in.read(box2);

        System.out.println(box2.array());

        Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // array() 메소드를 이용하여 버퍼의 내용을 바이트 배열에 저장한다.
        byte buffer[] = box2.array();

        String a = new String(buffer);

        System.out.println(a);
    }



}

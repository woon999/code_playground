package ThreadEx;

import javax.swing.*;

// interrupt()
public class ThreadEx13 {
    public static void main(String[] args) {
//        ThreadEx13_1 th1 = new ThreadEx13_1();
//        ThreadEx.ThreadEx13_2 th1 = new ThreadEx.ThreadEx13_2();
        ThreadEx13_3 th1 = new ThreadEx13_3();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt(); // interrupt()를 호출하면, interrupted상태가 true가 된다.
        System.out.println("isInterrupted():" + th1.isInterrupted()); // true
    }
}

//class ThreadEx13_1 extends Thread{
//    public void run(){
//        int i=10;
//
//        while(i!=0 && !isInterrupted()){
//            System.out.println(i--);
//            for (long x=0; x<2_500_000_000L; x++) ; // 시간 지연
//        }
//        System.out.println("카운트가 종료되었습니다.");
//    }
//}

class ThreadEx13_2 extends Thread{
    public void run(){
        int i=10;
        while(i!=0 && !isInterrupted()){
            System.out.println(i--);
            try{
                Thread.sleep(1000);
                System.out.println("#isInterrupted():" + isInterrupted()); // true
            }catch (InterruptedException e){}
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}

class ThreadEx13_3 extends Thread{
    public void run(){
        int i=10;
        while(i!=0 && !isInterrupted()){
            System.out.println(i--);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                interrupt();
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}


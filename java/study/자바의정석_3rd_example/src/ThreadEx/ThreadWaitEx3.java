package ThreadEx;

import java.util.ArrayList;

// wait() & notify()
public class ThreadWaitEx3 {
    public static void main(String[] args) throws Exception{
        Table4 table = new Table4(); // 여러 쓰레드가 공유하는 객체

        new Thread(new Cook4(table), "COOK1").start();
        new Thread(new Customer4(table, "donut"), "CUST1").start();
        new Thread(new Customer4(table, "burger"), "CUST2").start();
        Thread.sleep(2000);
        System.exit(0); // 프로그램 전체를 종료. (모든 쓰레드가 종료됨)
    }
}


class Customer3 implements Runnable{
    private Table4 table;
    private String food;

    Customer3(Table4 table, String food){
        this.table = table;
        this.food = food;
    }

    public void run(){
        while(true){
            try { Thread.sleep(100); } catch (InterruptedException e){}
            String name = Thread.currentThread().getName();
            table.remove(food);
            System.out.println(name +" ate a " + food);
        }
    }
}

class Cook3 implements Runnable{
    private Table4 table;
    Cook3(Table4 table) { this.table = table; }
    public void run(){
        while(true) {
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);
            try { Thread.sleep(10); } catch (InterruptedException e) { }
        }
    }
}

class Table3{
    String[] dishNames ={ "donut", "donut", "burger"};
    final int MAX_FOOD = 6;

    private ArrayList<String> dishes = new ArrayList<>();
    public synchronized void add(String dish){ // synchronized를 추가
        while(dishes.size() >= MAX_FOOD){
            String name = Thread.currentThread().getName();
            System.out.println(name+" is waiting");
            try{
                wait(); // 음식이 가득찼으므로 COOK쓰레드를 기다리게 한다.
                Thread.sleep(500);
            }catch (InterruptedException e){}
        }
        dishes.add(dish);
        notify(); // 음식이 추가되면 기다리고 있는 CUST를 깨우게 함
        System.out.println("Dishes : " + dishes.toString());
    }

    public void remove(String dishName){
        synchronized(this){
            String name = Thread.currentThread().getName();
            while(dishes.size() ==0){
                System.out.println(name+" is waiting");
                try {
                    wait(); // 접시가 비면 CUST쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                }
                catch (InterruptedException e) { }
            }

            while(true){
                for(int i=0; i<dishes.size(); i++){
                    if(dishName.equals(dishes.get(i))){
                        dishes.remove(i);
                        notify(); // 음식을 하나라도 비우면 잠자고 있는 COOK을 깨운다.
                        return; // 음식먹었으면 return
                    }
                }
                // 음식 못 먹었으면 wait
                try{
                    System.out.println(name+" is waiting");
                    wait(); // 원하는 음식이 없는 CUST쓰레드를 기다리게 한다.
                    Thread.sleep(500);
                }catch (InterruptedException e){}
            } // while(true)
        } // synchronized
    }
    public int dishNum(){ return dishNames.length; }
}
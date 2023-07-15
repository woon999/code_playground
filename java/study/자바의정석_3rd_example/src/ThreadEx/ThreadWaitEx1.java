package ThreadEx;

import java.util.ArrayList;

public class ThreadWaitEx1 {
    public static void main(String[] args) throws Exception{
        Table1 table = new Table1(); // 여러 쓰레드가 공유하는 객체

        new Thread(new Cook1(table), "COOK1").start();
        new Thread(new Customer1(table, "donut"), "CUST1").start();
        new Thread(new Customer1(table, "donut"), "CUST2").start();
        new Thread(new Customer1(table, "donut"), "CUST3").start();
        new Thread(new Customer1(table, "donut"), "CUST4").start();
        new Thread(new Customer1(table, "burger"), "CUST5").start();

        Thread.sleep(100); // 0.1초 후 강제 종료시킨다.
        System.exit(0); // 프로그램 전체를 종료. (모든 쓰레드가 종료됨)
    }
}


class Customer1 implements Runnable{
    private Table1 table;
    private String food;

    Customer1(Table1 table, String food){
        this.table = table;
        this.food = food;
    }

    public void run(){
        while(true){
            try { Thread.sleep(10); } catch (InterruptedException e){}
            String name = Thread.currentThread().getName();

            if(eatFood()){
                System.out.println(name +" ate a " + food);
            }else{
                System.out.println(name +" failed to eat. :(");
            }
        }
    }

    boolean eatFood(){ return table.remove(food); }
}

class Cook1 implements Runnable{
    private Table1 table;
    Cook1(Table1 table) { this.table = table; }
    public void run(){
        while(true) {
            //임의의 요리를 하나 선택해서 table에 추가한다.
            int idx = (int) (Math.random() * table.dishNum());
            table.add(table.dishNames[idx]);

            try { Thread.sleep(1); } catch (InterruptedException e) { }
        }
    }
}
class Table1{
    String[] dishNames ={ "donut", "donut", "burger"};
    final int MAX_FOOD = 6;

    private ArrayList<String> dishes = new ArrayList<>();
    public void add(String dish){
        // 테이블에 음식이 가득찼으면, 테이블에 음식을 추가하지 않는다.
        if(dishes.size() >= MAX_FOOD) return;
        dishes.add(dish);
        System.out.println("Dishes : " + dishes.toString());
    }

    public boolean remove(String dishName){
        // 저장된 요리와 일치하는 요리를 테이블에서 제거한다.
        for(int i=0; i<dishes.size(); i++){
            if(dishName.equals(dishes.get(i))){
                dishes.remove(i);
                return true;
            }
        }
        return false;
    }
    public int dishNum(){ return dishNames.length; }
}


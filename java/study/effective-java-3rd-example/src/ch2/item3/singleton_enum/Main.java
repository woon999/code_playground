package ch2.item3.singleton_enum;

public class Main {
    public static void main(String[] args) {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
        Elvis elvis2 = Elvis.INSTANCE;
        elvis2.leaveTheBuilding();

    }
}

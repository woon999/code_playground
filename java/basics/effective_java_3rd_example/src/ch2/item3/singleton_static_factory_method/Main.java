package ch2.item3.singleton_static_factory_method;

public class Main {
    public static void main(String[] args) {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
        Elvis elvis2 = Elvis.getInstance();
        elvis2.leaveTheBuilding();
    }
}

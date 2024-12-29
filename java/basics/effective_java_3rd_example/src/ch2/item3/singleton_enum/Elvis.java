package ch2.item3.singleton_enum;

public enum Elvis {
    INSTANCE;
    public static int count=0;

    public void leaveTheBuilding(){
        System.out.println(INSTANCE + ", Elvis leaveTheBuilding"+ (count++));
    }
}

package ch2.item1.immutable;

import java.util.Arrays;

// java.lang.String 클래스를 간단하게 카피하여 Immutable Class 구현
public class Immutable {
    private final String name;

    Immutable(String name){
        this.name = name;
    }

    public String concat(String str){
        int sLen = str.length();
        if(sLen == 0) return this.name;

        int len = this.name.length();
        char[] arr = name.toCharArray();
        char[] buf = Arrays.copyOf(arr, len+sLen);
        str.getChars(0,sLen, buf, len);
        return new String(buf);// 객체 변경 x 새로운 객체 반환
    }

    @Override
    public String toString() {
        return "(Immutable)name = " + name;
    }

    public static void main(String[] args) {
        String name = "test";
        Immutable im = new Immutable(name);
        System.out.println(im +", (local variable)name = " + name );
        System.out.println("im 주소값 = " +Integer.toHexString(System.identityHashCode(im))+ ", name 주소값 = " + Integer.toHexString(System.identityHashCode(name)));

        name = im.concat("day");
        System.out.println("\n"+im +", (local variable)name = " + name );
        System.out.println("im 주소값 = " +Integer.toHexString(System.identityHashCode(im))+ ", name 주소값 = " + Integer.toHexString(System.identityHashCode(name)));
    }
}
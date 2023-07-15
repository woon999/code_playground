package ch2.item1.enumset;


import java.util.EnumSet;

enum Alpha{
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,AY,AZ,AAA,AAB,AAC,AAD,AAE,AAF,AAG,AAH,AAI,AAJ,AAK,AAL
}

enum Alpha2{
    A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,AA,AB,AC,AD,AE,AF,AG,AH,AI,AJ,AK,AL,AM,AN,AO,AP,AQ,AR,AS,AT,AU,AV,AW,AX,AY,AZ,AAA,AAB,AAC,AAD,AAE,AAF,AAG,AAH,AAI,AAJ,AAK,AAL,AAM
}
public class Main {
    public static void main(String[] args) {
        getAlphaEnum(64);
        EnumSet es = EnumSet.allOf(Alpha.class);
        System.out.println(es.getClass().getTypeName()); // RegularEnumSet : 64


        getAlphaEnum(65);
        EnumSet es2 = EnumSet.allOf(Alpha2.class);
        System.out.println(es2.getClass().getTypeName()); // JumboEnumSet : 65
    }

    static void getAlphaEnum(int size){
        int idx=0;
        String s = "";
        for(int i=0; i<size; i++){
            if(i>0 && i%26==0) {
                s = "A"+s;
                idx=0;
            }
            System.out.print((s+(char)(idx+'A'))+",");
            idx++;
        }
        System.out.println();
    }
}

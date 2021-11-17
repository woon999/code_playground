package bst.basic;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST tree = new BST();

        for(int i=0; i<10; i++){
            tree.insert(new Random().nextInt(100));
        }


        System.out.println("### 트리 출력 ");
        tree.print(tree.root);

        System.out.println("\n\n### 트리 데이터 3개 삭제 ");
        int cnt=3;
        for(int i=0; i<100; i++){
            if(cnt == 0) break;
            if(tree.find(i)){
                System.out.println(i + " 삭제");
                tree.delete(i);
                cnt--;
            }
        }
        tree.print(tree.root);

        System.out.println("\n\n### 트리 데이터 [10] 삽입 (중복 X) ");
        // 중복 데이터 삽입 불가
        tree.insert(10);
        tree.insert(10);
        tree.print(tree.root);
    }
}

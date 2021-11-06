package treap;

import java.util.Random;
/*
    ref : https://www.techiedelight.com/implementation-treap-data-structure-cpp-java-insert-search-delete/
*/

class TreapNode{
    int data;  // 이 노드에 저장된 원소
    int priority; // 이 노드의 우선순위(proirity)
    TreapNode left, right;

    // data, left, right 초기화 및 난수 우선순위 생성
    TreapNode(int data){
        this.data = data;
        this.priority = new Random().nextInt(100);
        this.left = this.right = null;
    }
}

public class Treap {
    /* Function to left-rotate a given treap

             r                         R
            / \      Left Rotate      / \
           L   R        ———>         r   Y
              / \                   / \
             X   Y                 L   X
   */
   public static TreapNode rotateLeft(TreapNode root){
       TreapNode R = root.right;
       TreapNode X = root.right.left;
       // rotate
       R.left = root;
       root.right = X;
       return R;
   }
    /* Function to right-rotate a given treap

               r                        L
              / \     Right Rotate     / \
             L   R        ———>        X   r
            / \                          / \
           X   Y                        Y   R
   */
    public static TreapNode rotateRight(TreapNode root){
        TreapNode L = root.left;
        TreapNode Y = root.left.right;
        // rotate
        L.right = root;
        root.left = Y;
        return L;
    }

   public static TreapNode insertNode(TreapNode root, int data){
       if(root == null){
           return new TreapNode(data);
       }

       if(data < root.data){
           root.left = insertNode(root.left, data);
           if(root.left != null && root.left.priority > root.priority){
               root = rotateRight(root);
           }
       }else{
           root.right = insertNode(root.right, data);
           if(root.right != null && root.right.priority > root.priority){
               root = rotateLeft(root);
           }
       }
       return root;
   }

   public static void printTreap(TreapNode root, int space){
        final int h = 10;
        if(root == null) return;

        space += h;
        printTreap(root.right, space);
        System.lineSeparator();

        for(int i=h; i<space; i++){
            System.out.print(' ');
        }

        System.out.println(root.data+"("+root.priority+")");

        System.lineSeparator();
        printTreap(root.left, space);
   }

    public static void main(String[] args) {
        int[] keys = {5,2,1,4,9,8,10};

        TreapNode root = null;
        for(int key : keys){
            root = insertNode(root, key);
            System.out.println("-------------");
            printTreap(root,0);
        }
    }
}

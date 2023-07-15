package com.example.study.livestudy.week4.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    private Node root;

    public BinaryTree(Node root){
        this.root = root;
    }

    public Node getRoot(){
        return root;
    }
    /**
     * BFS
     */
    public void printByBFS(Node root){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            Node next = q.poll();
            System.out.print(next.getValue()+" ");
            if(next.getLeft() != null){
                q.offer(next.getLeft());
            }
            if(next.getRight() != null){
                q.offer(next.getRight());
            }
        }
        System.out.println();
    }

    /**
     * DFS
     * 왼쪽, 루트, 오른쪽 순으로 순회
     */
    public void printByDFS(Node root){
        if(root == null) return;


        printByDFS(root.getLeft());
        System.out.print(root.getValue()+" ");
        printByDFS(root.getRight());
    }


    public static void main(String[] args) {
        Node node10 = new Node(10,null,null);
        Node node9 = new Node(9,null,null);
        Node node8 = new Node(8,node10,null);
        Node node7 = new Node(7,null,node9);
        Node node6 = new Node(6,node8,null);
        Node node5 = new Node(5,null,null);
        Node node4= new Node(4,node7,null);
        Node node3 = new Node(3,node5,node6);
        Node node2 = new Node(2,node4,null);
        Node node1 = new Node(1,node2,node3);

        BinaryTree binaryTree = new BinaryTree(node1);
        Node root = binaryTree.getRoot();

        System.out.println("root : " + root.getValue());
        System.out.println();
        System.out.println("===== BFS =====");
        binaryTree.printByBFS(root);

        System.out.println();
        System.out.println("===== DFS =====");
        binaryTree.printByDFS(root);

    }
}

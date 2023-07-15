package com.example.study.livestudy.week4.BinaryTree;

/**
 * BinaryTree Node
 */
public class Node {

    private int value;
    private Node left;
    private Node right;

    public Node(){
    }

    public Node(int value, Node left, Node right){
        this.left = left;
        this.right = right;
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

}

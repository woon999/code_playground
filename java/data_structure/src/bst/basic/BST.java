package bst.basic;


public class BST {

    public Node root;
    public BST(){
        this.root = null;
    }

    // 탐색 연산
    public boolean find(int key){
        Node cur = root;
        while(cur != null){
            if(cur.getData()==key){
                return true; // 해당 key가 이미 존재하면 true
            }else if(cur.getData()>key){
                cur = cur.getLeft();
            }else{
                cur = cur.getRight();
            }
        }
        return false;
    }

    // 삭제 연산
    public boolean delete(int key){
        Node pa = root;
        Node cur = root;
        boolean isLeft = false;
        while(cur.getData() != key){
            pa = cur;
            if(cur.getData() >key){
                isLeft = true;
                cur = cur.getLeft();
            }else{
                isLeft = false;
                cur = cur.getRight();
            }
            if(cur==null) return false;
        }

        // 1. 삭제할 노드가 단말 노드인 경우
        if(cur.getLeft()==null && cur.getRight()==null){
            if(cur==root) root = null;

            if(isLeft == true){
                pa.setLeft(null);
            }else{
                pa.setRight(null);
            }
        }
        // 2. 삭제할 노드의 한 개의 자식노드를 가진 경우 (차수가 1인 경우)
        else if(cur.getRight() == null || cur.getLeft() ==null){
            // true : 오른쪽, false : 왼쪽
            boolean flag = cur.getRight() == null ? true : false;
            if(cur==root){
                root = flag ? cur.getLeft() : cur.getRight();
            }else if(isLeft){
                if(flag) pa.setLeft(cur.getLeft());
                else pa.setLeft(cur.getRight());
            }else{
                if(flag) pa.setRight(cur.getLeft());
                else pa.setRight(cur.getRight());
            }
        }
        // 3. 삭제할 노드가 두 개의 자식노드를 가진 경우 (차수가 2인 경우)
        else if(cur.getRight() != null && cur.getLeft() !=null){
            Node successor = getSuccessor(cur);
            if(cur == root){
                root = successor;
            }else if(isLeft){
                pa.setLeft(successor);
            }else {
                pa.setRight(successor);
            }
            successor.setLeft(cur.getLeft());
        }
        return true;
    }

    public void insert(int key){
        Node insertNode = new Node(key);
        if(root == null){
            root = insertNode;
            return;
        }

        Node cur = root, pa =null;
        while(true){
            pa = cur;
            if(cur.getData() == key) return; // 이미 데이터가 존재
            if(cur.getData() > key){
                cur = cur.getLeft();
                if(cur==null){
                    pa.setLeft(insertNode);
                    return;
                }
            }else{
                cur = cur.getRight();
                if(cur==null){
                    pa.setRight(insertNode);
                    return;
                }
            }
        }
    }

    public void print(Node root){
        if(root != null){
            print(root.getLeft());
            System.out.print(root.getData()+" ");
            print(root.getRight());
        }
    }



    private Node getSuccessor(Node deleteNode) {
        Node insertNode = null, pa =null;
        Node cur = deleteNode.getRight();
        while(cur!=null){
            pa = insertNode;
            insertNode = cur;
            cur = cur.getLeft();
        }
        if(insertNode != deleteNode.getRight()){
            pa.setLeft(insertNode.getRight());
            pa.setRight(deleteNode.getRight());
        }
        return insertNode;
    }
}

class Node{
    private int data;
    private Node left;
    private Node right;

    public Node(int data){
        this.setData(data);
        setLeft(null); setRight(null);
    }

    public int getData() { return data; }
    public void setData(int data) { this.data = data; }
    public Node getLeft() { return left; }
    public void setLeft(Node left) { this.left = left; }
    public Node getRight() { return right; }
    public void setRight(Node right) { this.right = right; }
}
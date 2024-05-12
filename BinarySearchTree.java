package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    static class implementation {
        class Node {
            int key;
            Node left;
            Node right;

            Node(int k) {
                key = k;
                left = null;
                right = null;
            }

            Node(int k, Node l, Node r) {
                key = k;
                left = l;
                right = r;
            }
        }

        private int count = 0;
        private Node root = null;
        private  boolean flag;
        private  boolean rflag;
        public boolean add(int k){
            flag = true;
            root = addNode(root,k);
            return flag;
        }
        private Node addNode(Node n , int k){
            if (n == null) {
                n = new Node(k);
                count++;
                return n;
            }
            if (k < n.key) {
                n.left = addNode(n.left, k);
            }
            else if (k > n.key) {
                n.right = addNode(n.right,k);
            }
            else {
                flag = false;
            }
            return n;
        }
     /*   public boolean add(int k) {
            if (root == null) {
                root = new Node(k);
                count++;
                return true;
            }
            Node curr = root;
            Node prev = null;
            while (curr != null) {
                if (k < curr.key) {
                    prev = curr;
                    curr = curr.left;
                } else if (k > curr.key) {
                    prev = curr;
                    curr = curr.right;
                } else {
                    return false;
                }
            }
            if (k < prev.key)
                prev.left = new Node(k);
            else
                prev.right = new Node(k);
            count++;
            return true;
        }
      */
        public void levelorder(){
            Queue<Node> q = new LinkedList<>();
            if (root != null)
                q.add(root);
            while (!q.isEmpty()){
                Node n = q.poll();
                System.out.print(n.key + " ");
                if (n.left != null)
                    q.add(n.left);
                if (n.right != null)
                    q.add(n.right);
            }
            System.out.println();
        }
        public void preordertraversal(){
            preorder(root);
            System.out.println();
        }
        private void preorder(Node n){
            if (n == null)
                return;
            System.out.print(n.key + " ");
            preorder(n.left);
            preorder(n.right);
        }
        public void postorder(){
            post(root);
            System.out.println();
        }
        private void post(Node n){
            if (n == null)
                return;
            post(n.left);
            post(n.right);
            System.out.print(n.key + " ");
        }
        public void inorder(){
            inorder(root);
            System.out.println();
        }
        private void inorder(Node n){
            if (n == null)
                return;
            inorder(n.left);
            System.out.print(n.key + " ");
            inorder(n.right);
        }
        public int size(){
            return count;
        }
        public boolean remove(int k){
            rflag = true;
            root = removeNode(root,k);
            if (rflag)
                count--;
            return rflag;
        }
        private Node removeNode(Node n , int k){
            if (n == null){
             rflag = false;
             return null;
            }
            if (k < n.key) {
                n.left = removeNode(n.left, k);
            }
            else if(k > n.key) {
                n.right = removeNode(n.right, k);
            }
            else{
                if (n.left == null && n.right == null) {
                    n = null;
                }
                else if (n.left == null) {
                    n = n.right;
                }
                else if (n.right == null) {
                    n = n.left;
                }
                else{
                    Node bignode = getBiggestNode(n.left);
                    int temp = n.key;
                    n.key = bignode.key;
                    bignode.key = temp;
                    n.left = removeNode(bignode,k);
                }
            }
            return n;
        }
        Node getBiggestNode(Node n){
            if (n.right == null)
                return n;
            return getBiggestNode(n.right);
        }
    }
    public static void main(String[] args) {
        implementation m = new implementation();
        m.add(50);
        m.add(30);
        m.add(70);
        m.add(20);
        m.add(60);
        m.add(80);
        System.out.println("Level order ----->");
        m.levelorder();
        System.out.println("pre order ----->");
        m.preordertraversal();
        System.out.println("Post order ---->");
        m.postorder();
        System.out.println("in order ---->");
        m.inorder();
        System.out.println("size --->");
        System.out.println(m.size());
        m.remove(20);
     }
}

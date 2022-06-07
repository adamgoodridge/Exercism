import java.util.LinkedList;
import java.util.List;

class BinarySearchTree<T extends Comparable<T>> {
    Node<T> root;
    void insert(T value) {
       Node<T> temp = root;
       Node<T> n = new Node<>(value);
       if(root == null) {
           root = n;
           return;
       }
       while (true) {
            if(temp.getData().compareTo(value) >= 0) {
                if(temp.getLeft() != null) {
                    temp = temp.getLeft();
                } else {
                    temp.setLeftNode(n);
                    break;
                }
            } else {
                if(temp.getRight()!= null) {
                    temp = temp.getRight();
                } else {
                    temp.setRightNode(n);
                    break;
                }
            }
       }
    }

    List<T> getAsSortedList() {
        return root == null ? null : root.getAsSortedList();
    }

    List<T> getAsLevelOrderList() {
        if(root == null) {
            return null;
        }
        int height = root.getLevel();
        List<T> output = new LinkedList<T>();
        //0 means the current level
        output.add(root.getData());
        for(int i = 0; i <= height; i++) {
            output.addAll(root.getAsLevelOrderList(i));
        }
        return output;
    }

    Node<T> getRoot() {
        return root;
    }

    static class Node<T> {
        private T data;
        private Node<T> leftNode, rightNode;
        Node(T data) {
            this.data = data;
        }
        Node<T> getLeft() {
            return leftNode;
        }

        Node<T> getRight() {
            return rightNode;
        }

        T getData() {
            return data;
        }

        private void setLeftNode(Node<T> leftNode) {
            this.leftNode = leftNode;
        }

        private void setRightNode(Node<T> rightNode) {
            this.rightNode = rightNode;
        }

        List<T> getAsSortedList() {
            List<T> list = new LinkedList<T>();
            if(leftNode != null)
                list.addAll(leftNode.getAsSortedList());
            list.add(data);
            if(rightNode != null)
                list.addAll(rightNode.getAsSortedList());
            return list;
        }

        List<T> getAsLevelOrderList(int level) {
            List<T> list = new LinkedList<T>();
            if(level == 0) {
                if(leftNode != null)
                    list.add(leftNode.getData());
                if(rightNode != null)
                    list.add(rightNode.getData());
            } else {
                level--;
                if(leftNode != null)
                    list.addAll(leftNode.getAsLevelOrderList(level));
                if(rightNode != null)
                    list.addAll(rightNode.getAsLevelOrderList(level));
            }
            return list;
        }
        private int getLevel() {
            if(leftNode == null & rightNode == null) {
                return 1;
            } else {
                int leftLevel = leftNode != null ? 1 + leftNode.getLevel() : -1;
                int rightLevel = rightNode != null ? 1 + rightNode.getLevel() : -1;
                return leftLevel > rightLevel ? leftLevel : rightLevel;
            }
        }
    }
}

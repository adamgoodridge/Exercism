public class DoublyLinkedList <E>{
    private Node<E> head;
    public DoublyLinkedList(){

    }
    public void push(E data){
        //Inserts the item to the back
        Node<E> node = new Node<>(data);
        if(head == null) {
            head = node;
        } else {
            Node<E> temp = head;
            while (temp.hasNextNode()){
                temp = temp.getNextNode();
            }
            temp.setNextNode(node);
            node.setLastNode(temp);
        }
    }
    public E pop(){
        //Gets from the back
        Node<E> temp = head;
        while(temp.hasNextNode()) {
            temp = temp.getNextNode();
        }
        Node<E> parent = temp.getLastNode();
        if(parent != null)
            parent.setNextNode(null);
        return temp.getData();
    }
    public E shift(){
        //Inserts the item to the front
        Node<E> nextNode = head.getNextNode();
        if(nextNode != null)
            nextNode.setLastNode(null);
        E data = head.getData();
        head = nextNode;
        return data;
    }
    public void unshift(E data){
        //Gets the item from the front item
        Node<E> node = new Node<>(data);
        if(head == null) {
            head = node;
        } else {
            node.setLastNode(head.getLastNode());
            node.setNextNode(head);
            head.setLastNode(node);
            head = node;
        }

    }
}
class Node<E> {
    private E data;
    private Node<E> nextNode, lastNode;
    public Node(E data) {
        this.data = data;
    }
    public E getData() {
        return data;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public Node<E> getLastNode() {
        return lastNode;
    }

    public void setLastNode(Node<E> lastNode) {
        this.lastNode = lastNode;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }
    public boolean hasNextNode() {
        return nextNode != null;
    }
    public boolean hasLastNode() {
        return lastNode != null;
    }
}
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

//empty file given
class SimpleLinkedList<E> {
    public ListItem<E> head;
    SimpleLinkedList(E[] input) {
        for(int i = 0; i < input.length; i++) {
            this.push(input[i]);
        }
    }
    SimpleLinkedList() {

    }
    public void push(E item) {
        if (head == null) {
            head = new ListItem<E>(item);
        } else {
            ListItem currentItem = head;
            while (currentItem.getChild() != null)
                currentItem = currentItem.getChild();
            currentItem.setChild(new ListItem<E>(item));
        }
    }

    public E pop() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("There's current nothing in the list!");
        ListItem currentItem = head;
        E item;
        if(currentItem.getChild() == null){
            item = (E)currentItem.getValue();
            head = null;
        } else {
            while (currentItem.getChild().getChild() != null) {
                currentItem = currentItem.getChild();
            }
            item = (E) currentItem.getChild().getValue();
            currentItem.setChild(null);
        }
        return item;
    }
    public void reverse() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("There's current nothing in the list!");
        //to become the head once transferring everything over
        ListItem<E> tempHead = new ListItem<E>(head.getValue());
        //head was transferred to the new list
        head = head.getChild();
        while (head != null) {
            ListItem<E> temp2 = new ListItem<E>(head.getValue());
            //new item will go in-front of the current item
            temp2.setChild(tempHead);
            tempHead = temp2;
            head = head.getChild();
        }
        head=tempHead;
    }
    public int size() {
        if (head == null) {
            return 0;
        } else {
            ListItem currentItem = head;
            int sum = 1;
            while (currentItem.getChild() != null) {
                currentItem = currentItem.getChild();
                sum++;
            }
            return sum;
        }
    }
    public E[] asArray(Class<E> classType){
        E[] output = (E[]) Array.newInstance(classType, this.size());
        ListItem<E> temp = this.head;
        for(int i = output.length-1; i > -1 ; i--) {
            output[i] = temp.getValue();
            temp = temp.getChild();
        }
        return output;
    }
}

 class ListItem<E>{
    private ListItem child;
    private E value;
    public ListItem(E value) {
        this.value=value;
    }
    public void setChild(ListItem child) {
        this.child = child;
    }
    public Boolean hasChild(){
        return child != null;
    }
    public E getValue(){
        return this.value;
    }
    public ListItem getChild(){
        return this.child;
    }

}
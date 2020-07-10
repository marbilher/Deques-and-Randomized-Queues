import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node front;
    private Node back;
    private int count;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {    //(when incrementing from 0 nodes to 1)
            front = new Node();
            front.item = item;
            back = front;   //single node is front and back
        }
        else {
            Node oldFront = front;
            front = new Node();
            front.item = item;
            oldFront.next = front;
            front.prev = oldFront;
        }
        count++;
    }

    // add the item to the back
    public void addLast(Item item) {

        if (item == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {    //(when incrementing from 0 nodes to 1)
            back = new Node();
            back.item = item;
            front = back;   //single node is front and back
        }
        else {
            Node oldBack = back;
            back = new Node();
            back.item = item;
            oldBack.prev = back;
            back.next = oldBack;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (isEmpty())
            throw new NoSuchElementException();

        Node removedFront = front;
        count--;
        if (isEmpty()) {
            front = null;
        }
        else {
            front = front.prev;
            front.next = null;
        }
        return removedFront.item;
    }

    // remove and return the item from the back
    public Item removeLast() {

        if (isEmpty())
            throw new NoSuchElementException();

        Node removedBack = back;
        count--;

        if (isEmpty()) {
            back = null;
        }
        else {
            back = back.next;
            back.prev = null;
        }
        return removedBack.item;
    }

    // return an iterator over items in order from front to back


    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node curr = front;

        public boolean hasNext() {
            return curr != null;
        }

        public Item next() {
            if (null == curr)
                throw new NoSuchElementException();

            Item item = curr.item;
            curr = curr.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        StdOut.println(dq.isEmpty());
        StdOut.println(dq.size());

        dq.addLast("A");
        dq.addLast("B");
        dq.addLast("C");
        dq.addLast("D");
        dq.addLast("E");
        StdOut.println(dq.size());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.size());

        dq.addFirst("A");
        dq.addFirst("B");
        dq.addFirst("C");
        dq.addFirst("D");
        dq.addFirst("E");
        // StdOut.println(dq.removeLast());
        // StdOut.println(dq.removeLast());
        // StdOut.println(dq.removeLast());
        // StdOut.println(dq.removeLast());
        // StdOut.println(dq.removeLast());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.size());

    }

}

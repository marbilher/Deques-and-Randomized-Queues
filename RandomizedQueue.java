import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    //https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java
    //T[] ts = (T[]) new Object[n];

    private Item[] arr = (Item[]) new Object[1];
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (N == arr.length) {
            Item[] tempArr = (Item[]) new Object[2 * arr.length];
            int j = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] != null)
                    tempArr[j++] = arr[i];
            }

            arr = tempArr;

        }
        arr[N] = item;
        N++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomItem = StdRandom.uniform(N);
        Item temp = arr[randomItem];
        arr[randomItem] = arr[N - 1];
        arr[N - 1] = temp;
        Item returnItem = arr[N - 1];
        N--;
        if (N > 0 && N == arr.length / 4) {
            Item[] tempArr = (Item[]) new Object[arr.length / 2];
            int j = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] != null)
                    tempArr[j++] = arr[i];
            }
            arr = tempArr;
        }
        return returnItem;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int randomItem = StdRandom.uniform(N);
        return arr[randomItem];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }


    private class RandomizedIterator implements Iterator<Item> {
        private int curr;
        private int[] index;

        public RandomizedIterator() {
            index = new int[N];
            for (int i = 0; i < N; i++) {
                index[i] = i;
            }
            StdRandom.shuffle(index);
            curr = N - 1;
        }

        public boolean hasNext() {
            return curr >= 0;
        }

        public Item next() {
            if (curr < 0)
                throw new NoSuchElementException();

            return arr[index[curr--]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

        RandomizedQueue<String> randomizedQ = new RandomizedQueue();

        randomizedQ.enqueue("A");
        randomizedQ.enqueue("B");
        randomizedQ.enqueue("C");
        randomizedQ.enqueue("D");
        randomizedQ.enqueue("E");
        int currentSize = randomizedQ.size();
        StdOut.println(currentSize);
        for (String s : randomizedQ) {
            StdOut.println(s);
        }
        String string = randomizedQ.dequeue();
        currentSize = randomizedQ.size();
        StdOut.println(currentSize);
        StdOut.println(string);

    }

}

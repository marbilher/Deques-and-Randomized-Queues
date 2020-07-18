/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQ = new RandomizedQueue<String>();
        System.out.println("main method" + k);

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            randomizedQ.enqueue(s);
        }

        for (int i = 0; i < k; i++) {
            String string = randomizedQ.dequeue();
            StdOut.println(string);
        }

    }
}


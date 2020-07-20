import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class LRUCache {
    static Deque<Integer> queue;

    static HashMap<Integer, Integer> set;
    static int size = 10;

    public static void main(String args[]) {
        queue = new LinkedList<>();
        set = new HashMap<>();
    }


    static void element(int x) {
        if(!set.containsKey(x)) {
            if(queue.size() == size) {
                int last = queue.removeLast();
                set.remove(last);
            }
            queue.push(x);
            set.put(x, getAddress(x));
        } else {
            int add = set.get(x);
            removeByAddress(add);
            set.put(x, getAddress(x));
            queue.push(x);
        }
    }


    static void removeByAddress(int address) {

    }


    static int getAddress(int x) {
        return x;
    }


}

package standard;

import java.util.*;

class TrieNode {

    char content;
    int isEnd;
    int count;
    LinkedList<TrieNode> childList;

    /* Constructor */
    public TrieNode(char c) {
        childList = new LinkedList<>();
        isEnd = 0;
        content = c;
        count = 0;
    }

    public TrieNode subNode(char c) {
        if (childList != null) {
            for (TrieNode eachChild : childList) {
                if (eachChild.content == c) {
                    return eachChild;
                }
            }
        }
        return null;
    }
}

class Trie {

    private TrieNode root;

    /* Constructor */
    Trie() {
        root = new TrieNode(' ');
    }

    /* Function to insert word */
    public void insert(String word) {
        if (search(word) == 1) {
            return;
        }
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            TrieNode child = current.subNode(ch);
            if (child != null) {
                current = child;
            } else {
                current.childList.add(new TrieNode(ch));
                current = current.subNode(ch);
            }
            current.count++;
        }
        current.isEnd = 1;
    }
    int count = 0;

    /* Function to search for word */
    public int search(String word) {
        count = 0;
        TrieNode current = root;
        for (char ch : word.toCharArray()) {

            if (current.subNode(ch) == null) {
                return 0;
            } else {
                count++;
                current = current.subNode(ch);

            }
        }

        if (current.isEnd == 1) {
            return count;
        }
        return count;
    }
    /* Function to remove a word */

}

/* Class Trie Test */
class TrieTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        /* Creating object of AATree */
        Trie t = new Trie();
        int v = scan.nextInt();  //input strings
        /*  Perform tree operations  */
        while (v > 0) {
            t.insert(scan.next());
            v--;
        }
        int u = scan.nextInt();   //to search strings
        while (u > 0) {
            System.out.println(t.search(scan.next()));
            u--;
        }
    }
}

package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
class trie {

}

class TrieNode {

    TrieNode[] arr;
    boolean isEnd;

    // Initialize data structure here.
    public TrieNode() {
        this.arr = new TrieNode[26];
    }

}

class Trie {
//    this part of code is taken from 
//    http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (p.arr[index] == null) {
                TrieNode temp = new TrieNode();
                p.arr[index] = temp;
                p = temp;
            } else {
                p = p.arr[index];
            }
        }
        p.isEnd = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode p = searchNode(word);
        if (p == null) {
            return false;
        } else if (p.isEnd) {
            return true;
        }

        return false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode p = searchNode(prefix);
        if (p == null) {
            return false;
        } else {
            return true;
        }
    }

    public TrieNode searchNode(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            if (p.arr[index] != null) {
                p = p.arr[index];
            } else {
                return null;
            }
        }

        if (p == root) {
            return null;
        }

        return p;
    }
}
//--------------------------------------------------------------------------------------->
//for binary numbers tries

class TrieNode2 {

    TrieNode2[] arr;
    int count;

    // Initialize your data structure here.
    public TrieNode2() {
        this.arr = new TrieNode2[2];
        count = 0;
    }

}

class Trie2 {

    TrieNode2 root;

    public Trie2() {
        root = new TrieNode2();
    }

    public void insert(int word, int i, TrieNode2 p) {
        if (i == -1) {
            return;
        }
        if ((word & (1 << i)) == 0) {
            if (p.arr[0] == null) {
                p.arr[0] = new TrieNode2();
            }
            p.arr[0].count++;
            insert(word, i - 1, p.arr[0]);
        } else {
            if (p.arr[1] == null) {
                p.arr[1] = new TrieNode2();
            }
            p.arr[1].count++;
            insert(word, i - 1, p.arr[1]);
        }
    }

    public void delete(int word, int i, TrieNode2 p) {
        if (i == -1) {
            return;
        }
        if ((word & (1 << i)) == 0) {
            p.arr[0].count--;
            delete(word, i - 1, p.arr[0]);
        } else {
            p.arr[1].count--;
            delete(word, i - 1, p.arr[1]);
        }
    }

    public int maxXor(int word, int i, TrieNode2 p) {
        if (i == 0) {
            if ((word & 1 << i) == 0) {
                return (p.arr[1] == null || p.arr[1].count == 0) ? 0 : 1;
            } else {
                return (p.arr[0] == null || p.arr[0].count == 0) ? 0 : 1;
            }
        } else if ((word & 1 << i) == 0) {
            return (p.arr[1] == null || p.arr[1].count == 0) ? maxXor(word, i - 1, p.arr[0]) : (maxXor(word, i - 1, p.arr[1]) + (1 << i));
        } else {
            return (p.arr[0] == null || p.arr[0].count == 0) ? maxXor(word, i - 1, p.arr[1]) : (maxXor(word, i - 1, p.arr[0]) + (1 << i));
        }
    }
}

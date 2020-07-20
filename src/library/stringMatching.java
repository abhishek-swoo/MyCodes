package library;

import java.util.Arrays;

/**
 *
 * @author Abhishek Shankhadhar
 */
class stringMatching {
    
    
    
//    ////////////////////////////
    
//    z-Algo code pattern$text
    
    public static int[] Z_algo(char[] str) {
        int n = str.length;
        int[] z = new int[n];
        if (n == 0) return z;
        z[0] = n;
        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i > r) {
                l = r = i;
                while (r < n && str[r - l] == str[r]) r++;
                z[i] = r - l;
                r--;
            } else {
                int k = i - l;
                if (z[k] < r - i + 1) {
                    z[i] = z[k];
                } else {
                    l = i;
                    while (r < n && str[r - l] == str[r]) r++;
                    z[i] = r - l;
                    r--;
                }
            }
        }

        return z;
    }
    
    
    
//   /////////////////////

//    KMP String matching algorithm
//    B is the preprocessing of pattern
    int B[];

    void kmpPreprocess(String pattern) {
        int m = pattern.length();
        int i = 0, j = -1;
        B[0] = -1;
        while (i < m) {
            while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = B[j];
            }
            i++;
            j++;
            if (i < m) {
                B[i] = j;
            }
        }
    }

    void kmpSearch(String text, String pattern) {
        int n = text.length(), m = pattern.length();
        int i = 0, j = 0;
        while (i < n) {
            while (j >= 0 && j < m && text.charAt(i) != pattern.charAt(j)) {
                j = B[j];
            }
            i++;
            j++;
            if (j == m) {
                System.out.println("Match at " + (i - j));
                j = B[j - 1];
            }
        }
    }
/////////////////////////////////////////

//////////////////////////////////////////
//    Suffix Array
    // sort suffixes of S in O(n*log(n))
    public static int[] suffixArray(CharSequence S) {
//        this part of code is taken from https://sites.google.com/site/indy256/algo/suffix_array
        int n = S.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = n - 1 - i;
        }

        // stable sort of characters
        Arrays.sort(order, (a, b) -> Character.compare(S.charAt(a), S.charAt(b)));

        int[] sa = new int[n];
        int[] classes = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
            classes[i] = S.charAt(i);
        }
        // sa[i] - suffix on i'th position after sorting by first len characters
        // classes[i] - equivalence class of the i'th suffix after sorting by first len characters

        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                // condition sa[i - 1] + len < n simulates 0-symbol at the end of the string
                // a separate class is created for each suffix followed by simulated 0-symbol
                classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
            }
            // Suffixes are already sorted by first len characters
            // Now sort suffixes by first len * 2 characters
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++) {
                cnt[i] = i;
            }
            int[] s = sa.clone();
            for (int i = 0; i < n; i++) {
                // s[i] - order of suffixes sorted by first len characters
                // (s[i] - len) - order of suffixes sorted only by second len characters
                int s1 = s[i] - len;
                // sort only suffixes of length > len, others are already sorted
                if (s1 >= 0) {
                    sa[cnt[classes[s1]]++] = s1;
                }
            }
        }
        return sa;
    }


    // longest common prefixes array in O(n)
    public static int[] lcp(int[] sa, CharSequence s) {
        int n = sa.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }
        int[] lcp = new int[n - 1];
        for (int i = 0, h = 0; i < n; i++) {
            if (rank[i] < n - 1) {
                for (int j = sa[rank[i] + 1]; Math.max(i, j) + h < s.length() && s.charAt(i + h) == s.charAt(j + h); ++h);
                lcp[rank[i]] = h;
                if (h > 0) {
                    --h;
                }
            }
        }
        return lcp;
    }
    
    long totalDistinctSubstring(String s) {
        int[] sa = suffixArray(s);
        int[] lcp = lcp(sa, s);
        int string_len = s.length();
        long sum = 0;
        sum = string_len - sa[0];
        for (int i = 0; i < lcp.length; i++) {
            sum += (string_len - sa[i + 1] - lcp[i]);
        }
        return sum;
    }
    
    // sort rotations of S in O(n*log(n))
    public static int[] rotationArray(CharSequence S) {
        int n = S.length();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Character.compare(S.charAt(a), S.charAt(b)));
        int[] sa = new int[n];
        int[] classes = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = order[i];
            classes[i] = S.charAt(i);
        }
        for (int len = 1; len < n; len *= 2) {
            int[] c = classes.clone();
            for (int i = 0; i < n; i++) {
                classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && c[(sa[i - 1] + len / 2) % n] == c[(sa[i] + len / 2) % n] ? classes[sa[i - 1]] : i;
            }
            int[] cnt = new int[n];
            for (int i = 0; i < n; i++) {
                cnt[i] = i;
            }
            int[] s = sa.clone();
            for (int i = 0; i < n; i++) {
                int s1 = (s[i] - len + n) % n;
                sa[cnt[classes[s1]]++] = s1;
            }
        }
        return sa;
    }
    
//    Suffix Array ENDS
/////////////////////////////////////////////

}

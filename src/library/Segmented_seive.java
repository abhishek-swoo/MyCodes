package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class Segmented_seive {

    public static void main(String args[]) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        int Max1 = 100000;
        boolean isPrime[] = new boolean[Max1 + 2];
        for (int i = 0; i <= Max1; i++) {
            isPrime[i] = true;
        }
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= Max1; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j <= Max1; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int t = Integer.parseInt(in.readLine());
        for (int ticker = 0; ticker < t; ticker++) {
            StringTokenizer tokenz = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(tokenz.nextToken());
            int n = Integer.parseInt(tokenz.nextToken());
            if (n <= Max1) {
                for (int i = m; i <= n; i++) {
                    if (isPrime[i]) {
                        out.append(String.valueOf(i));
                        out.newLine();
                    }
                }
                out.newLine();
            } else {
                int d = n - m;
                boolean[] ans = new boolean[d + 1];
                for (int i = 0; i < d + 1; i++) {
                    ans[i] = true;
                }
                for (int i = 2; i <= Max1; i++) {
                    if (isPrime[i]) {
                        int k = m / i;
                        k = k * i;
                        if (k < m) {
                            k += i;
                        }
                        if (k == i) {
                            k += i;
                        }
                        for (; k <= n; k += i) {
                            ans[k - m] = false;
                        }
                    }
                }
                for (int i = 0; i < d + 1; i++) {
                    if (ans[i]) {
                        out.append(String.valueOf(m + i));
                        out.newLine();
                    }
                }
                out.newLine();
            }
        }

        out.flush();
    }
}

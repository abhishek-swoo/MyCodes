package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class Important_fun {

    public static void main(String[] args) {

    }

    //    uwi mod pow function
    public static long pow(long a, long n, long mod) {
//		a %= mod;
        long ret = 1;
        int x = 63 - Long.numberOfLeadingZeros(n);
        for (; x >= 0; x--) {
            ret = ret * ret % mod;
            if (n << 63 - x < 0) {
                ret = ret * a % mod;
            }
        }
        return ret;
    }
    
//    changing date to georgian calander
    long date(int y, int m, int d) {
        m = (m + 9) % 12;
        y = y - m / 10;
        return 365l * y + y / 4 - y / 100 + y / 400 + (m * 306l + 5) / 10 + (d - 1);
    }

//    code to find a inverse % mod for any mod value
    public static long invl(long a, long mod) {
//      this part of code is taken from "uwi" submission
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        return p < 0 ? p + mod : p;
    }

//   gives no. of coprimes with n, gives phi(n) euler totient function
//   array primes[] consist of all primes till n
    public static int totient(int n, int[] primes) {
        int ret = n;
        for (int p : primes) {
            if (p * p > n) {
                break;
            }
            if (n % p == 0) {
                ret /= p;
                ret *= p - 1;
            }
            while (n % p == 0) {
                n /= p;
            }
        }
        if (n != 1) {
            ret /= n;
            ret *= n - 1;
        }
        return ret;
    }

// returns the index of element in array when it is sorted
// return 4 0 3 1 2  for 15 1 10 3 7
    public static int[] shrink(int[] a) {
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = (long) a[i] << 32 | i;
        }
        Arrays.sort(b);
        int[] ret = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (b[i] ^ b[i - 1]) >> 32 != 0) {
                p++;
            }
            ret[(int) b[i]] = p;
        }
        return ret;
    }

//////////////////////////////////
//for finding nCr
    public static long nCr(long n, long r) {
        long nCr = 1;
        long k = n - r;
        for (long j = 1; j <= r; j++) {
            nCr *= (k + j);
            nCr /= j;
        }
        return nCr;
    }

///////////////////
// 0-1 KNAPSACK 
    public int[][] KnapSack(int W, int val[], int wt[], int n) {
        int Knap[][] = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    Knap[i][j] = 0;
                } else if (wt[i - 1] <= j) {
                    Knap[i][j] = Math.max(Knap[i - 1][j], val[i - 1] + Knap[i - 1][j - wt[i - 1]]);
                } else {
                    Knap[i][j] = Knap[i - 1][j];
                }
            }
        }
        return Knap;
    }

//    gives the no. in the array is present on which position
//    return 2D array ,whose row number is the no. in array and col number is the index on which the no. is present in array
//    sup is the length of array
    public static int[][] makeBuckets(int[] a, int sup) {
        int n = a.length;
        int[][] bucket = new int[sup + 1][];
        int[] bp = new int[sup + 1];
        for (int i = 0; i < n; i++) {
            bp[a[i]]++;
        }
        for (int i = 0; i <= sup; i++) {
            bucket[i] = new int[bp[i]];
        }
        for (int i = n - 1; i >= 0; i--) {
            bucket[a[i]][--bp[a[i]]] = i;
        }
        return bucket;
    }

//Binary Search
    public static int BinarySearchLowerBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public static int BinarySearchUpperBound(int[] a, int v) {
        int low = -1, high = a.length;
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a[h] <= v) {
                low = h;
            } else {
                high = h;
            }
        }
        return low;
    }

//    finds nCr % mod
    public static int[][] EnumerateFactorialAndInverseFactorial(int n, int mod) {
        /*  Code picked up from "uwi" submissions */
        int[] f = new int[n + 1];
        int[] invf = new int[n + 1];
        f[0] = 1;
        //factorial % mod
        for (int i = 1; i <= n; i++) {
            f[i] = (int) ((long) f[i - 1] * i % mod);
        }
        long a = f[n];
        long b = mod;
        long p = 1, q = 0;
        while (b > 0) {
            long c = a / b;
            long d;
            d = a;
            a = b;
            b = d % b;
            d = p;
            p = q;
            q = d - c * q;
        }
        invf[n] = (int) (p < 0 ? p + mod : p);
        // inverse factorial % mod
        for (int i = n - 1; i >= 0; i--) {
            invf[i] = (int) ((long) invf[i + 1] * (i + 1) % mod);
        }
        return new int[][]{f, invf};
    }

    public static long C(int n, int r, int mod, int[][] fif) {
        /*  Code picked up from "uwi" submissions */
        if (n < 0 || r < 0 || r > n) {
            return 0;
        }
        return (long) fif[0][n] * fif[1][r] % mod * fif[1][n - r] % mod;
    }

}

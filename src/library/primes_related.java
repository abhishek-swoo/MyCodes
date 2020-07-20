
package library;

import java.io.*;
import java.util.*;

class primes_related {
    public static void main(String[] args) {
        
    }
//    for prime factorisation 
    public static int[][] factorFast(int n, int[] lpf)
    {
	int[][] f = new int[9][];
	int q = 0;
	while(lpf[n] > 0){
            int p = lpf[n];
            if(q == 0 || p != f[q-1][0]){
                f[q++] = new int[]{p, 1};
            }else{
                f[q-1][1]++;
            }
            n /= p;
        }
        if(n > 1){
            // big prime
            return new int[][]{{n, 1}};
        }
        return Arrays.copyOf(f, q);
    }
    
//	gives factors sequentially eg:for 100 seq is 2 2 5 5
    public static int[] factorSeqFast(int n, int[] lpf)
    {
        int[] seq = new int[26];
        int p = 0;
        while(n > 1){
            seq[p++] = lpf[n];
            n /= lpf[n];
        }
        return Arrays.copyOf(seq, p);
    }
    
    
    public static int[] enumLowestPrimeFactors(int n) {
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++)
            lpf[i] = i;
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p)
                primes[tot++] = p;
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p]
                    && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
    }
    
    
    //   seive for prime numbers
public static int[] sieveEratosthenes(int n) { 
//    this part of code is taken from "uwi" submission of codechef problem SEQTOWER 
	if (n <= 32) {
		int[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
		for (int i = 0; i < primes.length; i++) {
		    if (n < primes[i]) {
			return Arrays.copyOf(primes, i);
		    }
		}
	    return primes;
	}

	int u = n + 32;
	double lu = Math.log(u);
	int[] ret = new int[(int) (u / lu + u / lu / lu * 1.5)];
	ret[0] = 2;
	int pos = 1;

	int[] isnp = new int[(n + 1) / 32 / 2 + 1];
	int sup = (n + 1) / 32 / 2 + 1;

	int[] tprimes = { 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
	for (int tp : tprimes) {
	    ret[pos++] = tp;
    	    int[] ptn = new int[tp];
	    for (int i = (tp - 3) / 2; i < tp << 5; i += tp)
	        ptn[i >> 5] |= 1 << (i & 31);
		for (int j = 0; j < sup; j += tp) {
	            for (int i = 0; i < tp && i + j < sup; i++) {
	  	        isnp[j + i] |= ptn[i];
		    }
		}
	}
	// 3,5,7      2x+3=n 
	int[] magic = { 0, 1, 23, 2, 29, 24, 19, 3, 30, 27, 25, 11, 20, 8, 4,
			13, 31, 22, 28, 18, 26, 10, 7, 12, 21, 17, 9, 6, 16, 5, 15, 14 };
	int h = n / 2;
	for (int i = 0; i < sup; i++) {
            for (int j = ~isnp[i]; j != 0; j &= j - 1) {
		int pp = i << 5 | magic[(j & -j) * 0x076be629 >>> 27];
		int p = 2 * pp + 3;
		if (p > n)
	 	    break;
		ret[pos++] = p;
		if ((long) p * p > n)
	    	    continue;
		for (int q = (p * p - 3) / 2; q <= h; q += p)
		    isnp[q >> 5] |= 1 << q;
	}
    }
 	return Arrays.copyOf(ret, pos);
}

}

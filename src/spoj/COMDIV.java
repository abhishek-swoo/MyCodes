

package spoj;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class COMDIV {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution() {
        int primes[]=sieveEratosthenes(1010);
        for (int tn = inti(); tn > 0; tn--) {
            int a=inti(),b=inti();
            int g=gcd(a, b);
            int rt=(int)Math.sqrt(g);
            int i=0;
            int ans=1,count=0;
            while (i<primes.length && primes[i]<=rt) {
                count=0;
                while (g%primes[i]==0 && g>1) {                    
                    count++;
                    g/=primes[i];
                }
                ans*=(count+1);
                i++;
            }
            if (g>1) {
                ans*=2;
            }
            out.println(ans);
        }
    }
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


    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new COMDIV().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("location of file") : new ByteArrayInputStream(check.getBytes());
//        long t1=System.currentTimeMillis();
        solution();
//        long t2=System.currentTimeMillis();
//        out.println(t2-t1);
        out.flush();
        out.close();
    }
    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) {
            throw new InputMismatchException();
        }
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) {
            return -1;
        }
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    String stri() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }

    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long loni() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    float fl() {
        return Float.parseFloat(stri());
    }

    double dou() {
        return Double.parseDouble(stri());
    }

    char chi() {
        return (char) skip();
    }

    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inti();
        }
        return a;
    }

    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = loni();
        }
        return a;
    }

    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = stri();
        }
        return a;
    }

    private static void pa(Object... o) {
        System.out.println(Arrays.deepToString(o));
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

    int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}

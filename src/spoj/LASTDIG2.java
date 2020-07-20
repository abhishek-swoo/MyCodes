
package spoj;
import java.io.*;
class LASTDIG2{ public static void main(String args[]) throws IOException{ 
    BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int i=0;i<t;i++){ String[] s1=obj.readLine().split(" ");
            long a=s1[0].charAt(s1[0].length()-1)-'0',b=Long.parseLong(s1[1]);;
            System.out.println(pow(a, b, 10));
        }
    }
    public static long pow(long a, long n, long mod) {
	long ret = 1;
	int x = 63 - Long.numberOfLeadingZeros(n);
	for (; x >= 0; x--) {
		ret = ret * ret % mod;
		if (n << 63 - x < 0)
			ret = ret * a % mod; }
	return ret;
	}
}
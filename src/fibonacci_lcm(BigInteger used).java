

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
import java.io.*;
import java.math.BigInteger;
class fibonacci_lcm
{
    public static void main(String args[])throws IOException
    {
        BufferedReader obj=new BufferedReader(new FileReader("/home/ankurverma1994/Documents/kmp1.txt"));
        PrintWriter out=new PrintWriter(System.out);
        int mod=1000000007;
        BigInteger M=BigInteger.valueOf(mod);
        int t=Integer.parseInt(obj.readLine());
        long a[]=new long[5001];
        a[1]=a[2]=1;
        BigInteger fib[]=new BigInteger[5001];
        fib[2]=fib[1]=BigInteger.ONE;
        fib[0]=BigInteger.ZERO;
        for(int i=3;i<=5000;i++)
        {
            fib[i]=fib[i-1].add(fib[i-2]);
            a[i]=(a[i-1]+a[i-2])%mod;
        }
        for(int i=0;i<t;i++)
        {
            String s[]=obj.readLine().split(" ");
            int n=Integer.parseInt(s[0]);
            int m=Integer.parseInt(s[1]);
            long ans=(a[n]*a[m])%mod;
            ans=ans * (euclid(fib[n],(fib[m])).modInverse(M).longValue());
            ans=ans%mod;
            out.println(ans);
        }
        out.close();
    }
    static BigInteger euclid(BigInteger p, BigInteger q)
    {
        BigInteger r;
        if(p.compareTo(q)>0)
        {
            r=p;
            p=q;
            q=r;
        }
        while(!p.equals(BigInteger.ZERO))
        {
             r=q.mod(p);
             q=p;
             p=r;
        }
        return(q);
    }
}

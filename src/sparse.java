//package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class sparse 
{
    public static void main(String[] args)throws  IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        String[] tmp1=obj.readLine().split(" ");
        long z,L,R;
        long n=Long.parseLong(tmp1[0]);
        long k=Long.parseLong(tmp1[1]);
        long q=Long.parseLong(tmp1[2]);
        long[] arr =new long[(int)n+1];
        String[] tmp2=obj.readLine().split(" ");
        long a=Long.parseLong(tmp2[0]);
        long b=Long.parseLong(tmp2[1]);
        long c=Long.parseLong(tmp2[2]);
        long d=Long.parseLong(tmp2[3]);
        long e=Long.parseLong(tmp2[4]);
        long f=Long.parseLong(tmp2[5]);
        long r=Long.parseLong(tmp2[6]);
        long s=Long.parseLong(tmp2[7]);
        long m=Long.parseLong(tmp2[8]);
        long t=Long.parseLong(tmp2[9]);
        arr[1]=Long.parseLong(tmp2[10]);
        String[] tmp3=obj.readLine().split(" ");
        long l1=Long.parseLong(tmp3[0]);
        long la=Long.parseLong(tmp3[1]);
        long lc=Long.parseLong(tmp3[2]);
        long lm=Long.parseLong(tmp3[3]);
        long d1=Long.parseLong(tmp3[4]);
        long da=Long.parseLong(tmp3[5]);
        long dc=Long.parseLong(tmp3[6]);
        long dm=Long.parseLong(tmp3[7]);
        for(int x=2;x<=n;x++)
        {
            z=fpow(t,x+1,s);
            if(z%s <= r)
            {
                arr[x]=((((a*arr[x-1])%m*arr[x-1])%m + b*arr[x-1])%m + c)%m;
            }
            else
            {
                arr[x] = ((((d*arr[x-1])%m*arr[x-1])%m + e*arr[x-1])%m + f)%m;
            }
        }
        RmqSparseTable st = new RmqSparseTable(arr);
        long sum=0,pro=1;
        for(int i=1;i<q;i++)
        {
            l1=((la*l1)%lm + m)%lm;
            d1 = ((da * d1)%dm + dc) % dm;
            L = l1 + 1;
            if((L+k-1)+d1>n)
            {
                R=n;
            }
            else
                R=L+k-1;
            long ans=st.minPos((int)L, (int)R);
            sum +=ans;
            pro *=ans;
        }
        System.out.println(sum+" "+pro);
        
        
    }
    static long fpow(long t,int x, long mod)
    {
        //long mod=1000000007,
        long u=1;
        for(int i=1;i<=x;i++)
        {
            u=(u*t)%mod;
        }
        return u;
    }
}
 class RmqSparseTable {

  long[] logTable;
  long[][] rmq;
  long[] a;

  public RmqSparseTable(long [] a) {
    this.a = a;
    int n = a.length;

    logTable = new long[n + 1];
    for (int i = 2; i <= n; i++)
      logTable[i] = logTable[i >> 1] + 1;

    rmq = new long[(int)logTable[n] + 1][n];

    for (int i = 0; i < n; ++i)
      rmq[0][i] = i;

    for (int k = 1; (1 << k) < n; ++k) {
      for (int i = 0; i + (1 << k) <= n; i++) {
        long x = rmq[k - 1][i];
        long y = rmq[k - 1][i + (1 << k - 1)];
        rmq[k][i] = a[(int)x] <= a[(int)y] ? x : y;
      }
    }
  }

  public long minPos(int i, int j) {
    int k = (int)logTable[j - i];
    long x = rmq[k][i];
    long y = rmq[k][j - (1 << k) + 1];
    return a[(int)x] <= a[(int)y] ? x : y;
  }
}

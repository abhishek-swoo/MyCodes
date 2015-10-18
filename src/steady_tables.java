//package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class steady_tables 
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        long[][] ncr=new long[4001][2001];
        long mod=1000000000;
        for(int i=0;i<2001;i++)
        {
            ncr[i][0]=1;
            ncr[i][i]=1;
        }
        for(int i=1;i<4001;i++)
            for(int j=1;j<i;j++)
            {
                if(j<=2000)
                ncr[i][j]=(ncr[i-1][j] + ncr[i-1][j-1])%mod;
            }
        for(int z=0;z<t;z++)
        {
            String[] str=obj.readLine().split(" ");
            int n=Integer.parseInt(str[0]);
            int m=Integer.parseInt(str[1]);
            long[][] a=new long[n+2][m+2];
            long p=0;
            for(int i=1;i<=m;i++)
            {
                a[n+1][0]=1;
                a[n+1][i]=1;
            }
            for(int i=0;i<=n;i++)
            {
                for(int j=0;j<=m;j++)
                {
                    p=ncr[((2*m)-j-1)][m-1];
                    a[n-i][m-j]=(((a[n-i+1][m-j])*p)%mod + a[n-i][m-j+1])%mod;
                    
                }
            }
            System.out.println(a[1][0]);
        }
    }
    
}

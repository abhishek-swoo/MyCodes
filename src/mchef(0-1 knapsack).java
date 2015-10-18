
//package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class mchef 
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(System.out);
        int t=Integer.parseInt(obj.readLine());
        for(int te=0;te<t;te++)
        {
            String[] str1=obj.readLine().split(" ");
            int n=Integer.parseInt(str1[0]);
            int k=Integer.parseInt(str1[1]);
            int m=Integer.parseInt(str1[2]);
            long sum=0;
            int flag=0;
            long[] val=new long[n+1];
            int[] wei=new int[n+1];
            wei[0]=1001;
            String[] str2=obj.readLine().split(" ");
            for(int i=0;i<n;i++)
            {
                val[i+1]=Long.parseLong(str2[i]);
                sum=sum+val[i+1];
                wei[i+1]=1001;
                if(val[i+1]<0)
                    flag++;
            }
            for(int i=1;i<=m;i++)
            {
                String[] str3=obj.readLine().split(" ");
                int l=Integer.parseInt(str3[0]);
                int r=Integer.parseInt(str3[1]);
                int c=Integer.parseInt(str3[2]);
                if(flag>=1)
                for(int j=l;j<=r;j++)
                    if(wei[j]>c)
                        wei[j]=c;
            }
            int count=1;
            long[] value=new long[flag+1];
            int[] weight=new int[flag+1];
            for(int i=1;i<=n;i++)
            {
                if(val[i]<0)
                {
                    weight[count]=wei[i];
                    value[count]=val[i];
                    count++;
                }
            }
            //Applying 0-1 knapsack for negative numbers
            long[][] v=new long[n+1][k+1];
            for(int i=1;i<=flag;i++){
                for(int j=0;j<=k;j++)
                {
                    if(weight[i]<=j )
                    {
                        if(v[i-1][j]<(value[i]+v[i-1][j-(int)weight[i]]))
                            v[i][j]=v[i-1][j];
                        else
                            v[i][j]=value[i]+v[i-1][j-(int)weight[i]];
                    }
                    else
                        v[i][j]=v[i-1][j];
                }
            }
                out.println(sum-v[flag][k]);
            }
        out.close();
    }
    }

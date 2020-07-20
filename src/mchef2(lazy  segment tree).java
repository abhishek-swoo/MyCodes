
//package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class mchef2 
{
    static int[] ST= new int[300002];
    static int[] flag= new int[300002];
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
            int it=0;
            int fla=0;
            for(it=0;it<300002;it++)
            {
                ST[it]=500;
                flag[it]=500;
            }
            int[] val=new int[n+1];
            String[] str2=obj.readLine().split(" ");
            for(int i=0;i<n;i++)
            {
                val[i+1]=Integer.parseInt(str2[i]);
                sum=sum+val[i+1];
                if(val[i+1]<0)
                    fla++;
            }
            for(int i=1;i<=m;i++)
            {
                String[] str3=obj.readLine().split(" ");
                int l=Integer.parseInt(str3[0]);
                int r=Integer.parseInt(str3[1]);
                int c=Integer.parseInt(str3[2]);
                update( 1, 1, n, l, r, c );
//                for(int j=l;j<=r;j++)
//                    if(wei[j]>c)
//                        wei[j]=c;
            }
            int count=1;
            long[] value=new long[fla+1];
            int[] weight=new int[fla+1];
            for(int i=1;i<=n;i++)
            {
                if(val[i]<0)
                {
                    weight[count]=(int)query( 1, 1, n, i, i );
                    value[count]=val[i];
                    count++;
                }
            }
            //Applying 0-1 knapsack for negative numbers
            long[][] v=new long[n+1][k+1];
            for(int i=1;i<=fla;i++){
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
                out.println(sum-v[fla][k]);
            }
        out.close();
    }
    static void update( int node, int i, int j, int b, int e, int v )
    {
	
	int izq = node * 2, der = node * 2 + 1, m = (i + j)/2;
	 
	if( flag[node]!=500 ){
		ST[node] = Math.min(ST[node],flag[node] * (j - i + 1));
		if( i != j ){
			flag[der] = Math.min(flag[der],flag[node]);
			flag[izq] = Math.min(flag[node],flag[izq]);
		}
		flag[node] = 500;
	}
	
	if( i > e || j < b )
		return;
	
	if( i >= b && j <= e ){
		ST[node] = Math.min(v * ( j - i + 1 ),ST[node]);
		if( i != j ){
			flag[der] = Math.min(flag[der],v);
			flag[izq] = Math.min(flag[izq],v);
		} 
		return;
	}
	
	update( izq, i    , m, b, e, v );
	update( der, m + 1, j, b, e, v );
	
	ST[node] = ST[izq] + ST[der];
	
}
    static long query( int node, int i, int j, int b, int e ){
	
	int izq = node * 2, der = node * 2 + 1, m = (i + j)/2;
	 
	if( flag[node] != 500){
		ST[node] = Math.min(flag[node] * (j - i + 1),ST[node]);
		if( i != j ){
			flag[der] = Math.min(flag[der],flag[node]);
			flag[izq] = Math.min(flag[node],flag[izq]);
		}
		flag[node] = 500;
	}
	
	if( i > e || j < b )
		return 0 ;
	
	if( i >= b && j <= e )
		return ST[node];
	
	long p1 = query( izq, i    , m, b, e );
	long p2 = query( der, m + 1, j, b, e );
	ST[node] = ST[izq] + ST[der];
	return p1+p2;
	
}
    }

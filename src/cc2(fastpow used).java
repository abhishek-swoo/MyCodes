import java.io.*;
class cc2
{
    public static void main(String args[])throws IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        long t=Long.parseLong(obj.readLine());
        long i,n,p,m,j,k,var,ans;
        m=1000000007;
        for(i=0;i<t;i++)
        {
            n=Long.parseLong(obj.readLine());
            p=fastpow(3,n,m);
            var=n-2;
            for(j=0;j<n/3;j++)
            {
                k=n-2-3*j;
                if(k<0)
                    break;
                var=var+(k*(k+1))/2;
                
            }
            ans=p-var;
            System.out.println(ans);
                
        }
    }
    private static long fastpow(long base,long pow,long m)
    { 
        long result=1; 
        while(pow>0)
        { 
            if(pow%2==1) 
                result=(result*base)%m; 
            base=(base*base)%m; 
            pow/=2;
        } 
        return result; 
    }
}
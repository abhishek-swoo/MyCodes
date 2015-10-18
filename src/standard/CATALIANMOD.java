package standard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */

class CATALIONMOD {
     long binomialCoeff( int n,  int k, int m)
      {
     long res = 1;
 
    // Since C(n, k) = C(n, n-k)
    if (k > n - k)
        k = n - k;
 
    // Calculate value of [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
    for (int i = 0; i < k; ++i)
    {
        res = ((res%m)*((n - i)%m))%m;
        int d=solve(i+1, m);
            while(d<0)
                d=d+m;
            while(d>=m)
                d=d-m;
        res= (res*d)%m;
    }
 
    return res;
    }
    long catalanno( int n, int m)
    {
    // Calculate value of 2nCn
     long  c = binomialCoeff(2*n, n,m);
 
    // return 2nCn/(n+1)
     int d=solve(n+1, m);
            while(d<0)
                d=d+m;
            while(d>=m)
                d=d-m;
    return (c*d)%m;
     }
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        CATALIONMOD obj1=new CATALIONMOD();
        for(int i=0;i<t;i++)
        {
            int n=Integer.parseInt(obj.readLine());
            int m=Integer.parseInt(obj.readLine());
            System.out.println(obj1.catalanno(n,m));
        }
    }
    static int solve(int a,int b)
    {
        int x=0,y=1,lasty=0,lastx=1,temp;
            while(b!=0)
            {
                int q=a/b;
                int r=a%b;
                a=b;
                b=r;
                temp=x;
                x=lastx-q*x;
                lastx=temp;
                temp=y;
                y=lasty-q*y;
                lasty=temp;
            }
           // System.out.println(lastx+"\t"+lasty);
            return(lastx);
    }
    static int gcd(int a,int b)
    {
        int s;
        for(s=0; ((a|b)&1)==0;s++)
        {
            a>>=1;
            b>>=1;
        }
        while((a&1)==0)
        a>>=1;
        do
        {
            while((b&1)==0)
            b>>=1;
            if(a>b)
            {
                int t=b;
                b=a;
                a=t;
            }
            b=b-a;
        }while(b!=0);
        return(a<<s);
    }
}


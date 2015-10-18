package standard;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 */

public class Extended_Euclid {
    public static void main(String[] args)throws IOException {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int i=0;i<t;i++)
        {    // calculates a^-1 mod b
            int a=Integer.parseInt(obj.readLine());
            int b=Integer.parseInt(obj.readLine());
            int k=gcd(a, b);
//            if(k!=1)
//            {
//                a=a/k;
//                b=b/k;
//            }
            int d=solve(a, b);
            while(d<0)
                d=d+b;
            while(d>=b)
                d=d-b;
            System.out.println(d);
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

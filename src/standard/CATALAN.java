package standard;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class CATALAN {
     long binomialCoeff( int n,  int k)
      {
     long res = 1;
 
    // Since C(n, k) = C(n, n-k)
    if (k > n - k)
        k = n - k;
 
    // Calculate value of [n*(n-1)*---*(n-k+1)] / [k*(k-1)*---*1]
    for (int i = 0; i < k; ++i)
    {
        res *= (n - i);
        res /= (i + 1);
    }
 
    return res;
    }
    long catalanno( int n, int m)
    {
    // Calculate value of 2nCn
     long  c = binomialCoeff(2*n, n);
 
    // return 2nCn/(n+1)
    return c/(n+1);
     }
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        CATALAN obj1=new CATALAN();
        for(int i=0;i<t;i++)
        {
            int n=Integer.parseInt(obj.readLine());
            int m=Integer.parseInt(obj.readLine());
            System.out.println(obj1.catalanno(n,m));
        }
    }
}


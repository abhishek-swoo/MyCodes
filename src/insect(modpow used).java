import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class insect {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new insect().ace();
    }
    //Solution !!
//    BigInteger pow(BigInteger base, BigInteger exponent) 
//    {
//        BigInteger result = BigInteger.ONE;
//  while (exponent.signum() > 0) {
//    if (exponent.testBit(0)) result = result.multiply(base);
//    base = base.multiply(base);
//    exponent = exponent.shiftRight(1);
//  }
//  return result;
//}
    void solution()
    {
        for(int tn=inti();tn>0;tn--)
        {
            int x=inti();
            int y=inti();
            long t=loni();
            int mod=1000000007;
            long u=(long)x+y;
//            BigInteger K=pow(BigInteger.valueOf(3),BigInteger.valueOf(t-1));
            long k=modpow(3, t-1, mod);
            while(k<0)
                k=k+mod;
            k=((k*u)%mod+x)%mod;
          //  long l=(long)k;
           // u=l*u;
//            BigInteger U=BigInteger.valueOf(u);
//            U=U.multiply(K);
//            for(long i=0;i<t-1;i++)
//            {
//                u=u*3;
//            }
//            U=U.add(BigInteger.valueOf(x));
            out.println(k);
        }
    }
    long modpow(long base, long exp, int mod)
    {
        base%=mod;
        long result=1;
        while(exp>0){
            if((exp&1) ==1)
                result=(result*base)%mod;
            base=(base*base)%mod;
            exp>>=1;
        }        
        return result;
    }
    //------->ends !!
    void ace()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.flush();
        out.close();
    }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1)
            throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer)
        {
            ptrbuffer=0;
            try {
                lenbuffer=obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if(lenbuffer<=0)
            return -1;
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c)
    {
        return (!(c>=33 && c<=126));
    }
    
    String stri()
    {
        int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b=readByte();
        }
        return sb.toString();
    }
    int skip()
    {
        int b;
        while((b=readByte())!=-1 && isSpaceChar(b));
        return b;
    }
    int inti()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    long loni()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    float fl()
    {
        return Float.parseFloat(stri());
    }
    double dou()
    {
        return Double.parseDouble(stri());
    }
    char chi()
    {
        return (char)skip();
    }
    int[] arri(int n)
    {
        int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=inti();
        return a;
    }
    long[] arrl(int n)
    {
        long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=loni();
        return a;
    }
    String[] stra(int n)
    {
        String a[]=new String[n];
        for(int i=0;i<n;i++)
            a[i]=stri();
        return a;
    }
}

package spoj;
import java.io.*;
class LASTDIG{public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));int t=Integer.parseInt(obj.readLine());
        while(t>0){String str[]=obj.readLine().split(" ");int a=Integer.parseInt(str[0]);int b=Integer.parseInt(str[1]);if(a==0){System.out.println("0");t--;continue;}if(b==0){System.out.println("1");t--;continue;}
            long p=pow(a, b, 10);System.out.println(p); t--;}}
    public static long pow(long a, long n, long mod) {long ret = 1;
    int x=63- Long.numberOfLeadingZeros(n);
    for (;x>=0;x--){ret=ret*ret%mod;
        if (n<<63-x<0)ret = ret*a%mod;}return ret;}}
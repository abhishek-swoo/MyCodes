
package spoj;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class COINS {
    static long arr[]=new long[10000001];
    static long sum=0;
    public static void main(String[] args)throws IOException{
    
        BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<12;i++){
            arr[i]=i;
        }
        for(int i=12;i<10000001;i++){
            
            arr[i]=arr[i/2]+arr[i/3]+arr[i/4];
        }
        String s;
        while((s=ob.readLine())!=null && s.length()!=0){
//            System.out.println("HELLO");
        Integer n=new Integer(s);
        sum=0;
        long ss=coins(n);
        System.out.println(ss);
        }
    }
    public static long coins(int n){
        if(n<12)
            return n;
        if(n/2<10000001)
            return (arr[n/2]+arr[n/3]+arr[n/4]);
        sum=coins(n/2)+coins(n/3)+coins(n/4);
        return sum;
    }
    //------->ends !!
}
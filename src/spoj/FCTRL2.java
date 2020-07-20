package spoj;
import java.io.*;
import java.math.BigInteger;
import java.util.*;
class FCTRL2 {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new FCTRL2().ace();
    }
    void solution(){
        for(int tn=inti();tn>0;tn--){ int n=inti();
            BigInteger N=BigInteger.valueOf(n-1);
            BigInteger product=BigInteger.valueOf(n);
            n--;
            while (n>0) {                
                product=product.multiply(N);
                N=N.subtract(BigInteger.ONE);
                n--;
            }
            out.println(product);
        }
    }
    void ace()throws IOException{ out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes()); solution();
        out.flush(); }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1) throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer){ ptrbuffer=0;
            try { lenbuffer=obj.read(inbuffer); } catch (IOException e) { throw new InputMismatchException(); } }
        if(lenbuffer<=0) return -1;
        return inbuffer[ptrbuffer++];}
    boolean isSpaceChar(int c){ return (!(c>=33 && c<=126));}
    int inti(){ int num = 0, b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){
            if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; }
            b = readByte(); } } }

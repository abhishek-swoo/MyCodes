package spoj;
import java.io.*;
import java.util.*;
class AP2 {
    InputStream obj; PrintWriter out; String check="";
    public static void main(String[] args)throws IOException{
        new AP2().ace(); }
    void solution()
    {
        for(long tn=loni();tn>0;tn--){
            long term3=loni(),last3=loni(),sum=loni();
            long n=(2*sum)/(term3+last3),d=(last3-term3)/(n-5);
            long a=term3-2*d; out.println(n);
            for(int i=0;i<n;i++) out.print(a+i*d+" ");
            out.println();
        }
    }
    void ace()throws IOException{ out=new PrintWriter(System.out); obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes()); solution(); out.flush(); out.close(); }
    byte inbuffer[]=new byte[1024]; int lenbuffer=0,ptrbuffer=0;
    int readByte(){ if(lenbuffer==-1) throw new InputMismatchException(); if(ptrbuffer>=lenbuffer){ ptrbuffer=0; try { lenbuffer=obj.read(inbuffer); } catch (IOException e) { throw new InputMismatchException(); } } if(lenbuffer<=0) return -1;
        return inbuffer[ptrbuffer++]; }
    boolean isSpaceChar(int c){ return (!(c>=33 && c<=126));}
    long loni(){ long num = 0; int b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){ if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; } b = readByte(); } }
}

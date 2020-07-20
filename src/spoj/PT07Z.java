
package spoj;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.ByteArrayInputStream;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PT07Z {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new PT07Z().ace();
    }
    //Solution !!
    int c=0;
    void solution()
    {
        int n=inti();
        ArrayList<Integer>[] al=new ArrayList[n];
        for(int i=0;i<n;i++)
            al[i]=new ArrayList<>();
        for(int i=0;i<n-1;i++){
            int u=inti()-1,v=inti()-1;
            al[u].add(v);
            al[v].add(u);
        }
        int root=0;
        if(n==1)
            out.println(0);
        else{
            int in=bfs(root, al, n);
            int in2=bfs(in, al, n);
        }
        
    }    
    int bfs(int v, ArrayList<Integer> g[],int n)
    {
        c++;
        int d[]=new int[g.length];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[v]=0;
        Queue<Integer> queue=new LinkedList<>();
        queue.add(v);
        while(!queue.isEmpty())
        {
            int pop=queue.poll();
            for(int var=0;var<=g[pop].size()-1;var++)
            {
                if(d[g[pop].get(var)]==Integer.MAX_VALUE)
                {
                    d[g[pop].get(var)]=d[pop]+1;
                    queue.add(g[pop].get(var));
                }
            }
        }
        int max=0,in=0;
        for(int i=0;i<n;i++){
            if(max<d[i]){
                max=d[i];
                in=i;
            }
        }
//        tr(d);
//        out.println(in+" sd");
        if(c==2){
            Arrays.sort(d);
//            tr(d);
            out.println(d[n-1]);
        }
            
        return in;
//        System.out.println(Arrays.toString(d));//for accessing shortest path from 5th element
//        Arrays.sort(d);
//        out.println(d[n-1]+d[n-2]-1);
    }
    //------->ends !!
    void ace()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("location of file") : new ByteArrayInputStream(check.getBytes());
//        long t1=System.currentTimeMillis();
        solution();
//        long t2=System.currentTimeMillis();
//        out.println(t2-t1);
        out.flush();
        out.close();
    }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1) throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer){ ptrbuffer=0;
            try { lenbuffer=obj.read(inbuffer); } catch (IOException e) { throw new InputMismatchException(); } }
        if(lenbuffer<=0) return -1;
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c){ return (!(c>=33 && c<=126));}
    
    String stri(){int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        { sb.appendCodePoint(b); b=readByte(); }
        return sb.toString();
    }
    int skip(){ int b; while((b=readByte())!=-1 && isSpaceChar(b)); return b;}
    int inti(){
        int num = 0, b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){
            if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; }
            b = readByte();
        }
    }
    long loni(){
        long num = 0; int b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){
            if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; } b = readByte(); } }
    float fl(){return Float.parseFloat(stri());}
    double dou(){return Double.parseDouble(stri());}
    char chi(){return (char)skip();}
    int[] arri(int n){int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=inti();
        return a;}
    long[] arrl(int n){long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=loni();
        return a;}
    String[] stra(int n){String a[]=new String[n];
        for(int i=0;i<n;i++)
            a[i]=stri();
        return a;}
    private static void pa(Object... o) { System.out.println(Arrays.deepToString(o));}
//    uwi mod pow function
        public static long pow(long a, long n, long mod) {
//		a %= mod;
	long ret = 1;
	int x = 63 - Long.numberOfLeadingZeros(n);
	for (; x >= 0; x--) { ret = ret * ret % mod; if (n << 63 - x < 0) ret = ret * a % mod; }
	return ret; }
    int gcd(int a, int b){ if (a == 0) return b; return gcd(b%a, a); }
}

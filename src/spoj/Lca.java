
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
import java.util.InputMismatchException;
import java.util.Arrays;

class Lca {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new Lca().ace();
    }
    //Solution !!
    void solution()
    {
        int tn=inti();
        for(int tt=1;tt<=tn;tt++){
            int n=inti(),count=0;
            int from[]=new int[n-1];
            int to[]=new int[n-1];
            for(int i=0;i<n;i++){
                int m=inti();
                for(int j=0;j<m;j++){
                    from[count]=i;
                    to[count++]=inti()-1;
                }
            }
            int[][] graph=packU(n, from, to);
            int[][] pars=parents3(graph, 0);
            int[] par=pars[0],ord = pars[1], dep = pars[2];
            int[][] spar = logstepParents(par);
            int q=inti();
            out.println("Case "+tt+":");
            for(int i=0;i<q;i++){
                int a=inti()-1,b=inti()-1;
                out.println(lca2(a, b, spar, dep)+1);
            }
        }
    }
    
    public static int lca2(int a, int b, int[][] spar, int[] depth) {
	if (depth[a] < depth[b]) {
  	    b = ancestor(b, depth[b] - depth[a], spar);
	} else if (depth[a] > depth[b]) {
		a = ancestor(a, depth[a] - depth[b], spar);
	}
 
	if (a == b)
	    return a;
	int sa = a, sb = b;
	for (int low = 0, high = depth[a], t = Integer.highestOneBit(high), k = Integer.numberOfTrailingZeros(t); t > 0; t >>>= 1, k--) {
	    if ((low ^ high) >= t) {
		if (spar[k][sa] != spar[k][sb]) {
		    low |= t;
	  	    sa = spar[k][sa];
		    sb = spar[k][sb];
		} else {
			high = low | t - 1;
	 	}
	    }
	}
	return spar[0][sa];
    }
    
    protected static int ancestor(int a, int m, int[][] spar) {
        for (int i = 0; m > 0 && a != -1; m >>>= 1, i++) {
            if ((m & 1) == 1)
                a = spar[i][a];
        }
        return a;
    }
    
    public static int[][] logstepParents(int[] par) {
        int n = par.length;
        int m = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
        int[][] pars = new int[m][n];
        pars[0] = par;
        for (int j = 1; j < m; j++) {
            for (int i = 0; i < n; i++) {
                pars[j][i] = pars[j - 1][i] == -1 ? -1
                        : pars[j - 1][pars[j - 1][i]];
            }
        }
        return pars;
    }
    
    public static int[][] parents3(int[][] g, int root) {
        int n = g.length;
        int[] par = new int[n];
        Arrays.fill(par, -1);
        
        int[] depth = new int[n];
        depth[0] = 0;
        
        int[] q = new int[n];
        q[0] = root;
        for (int p = 0, r = 1; p < r; p++) {
            int cur = q[p];
            for (int nex : g[cur]) {
                if (par[cur] != nex) {
                    q[r++] = nex;
                    par[nex] = cur;
                    depth[nex] = depth[cur] + 1;
                }
            }
        }
        return new int[][] { par, q, depth };
    }
    
    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
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

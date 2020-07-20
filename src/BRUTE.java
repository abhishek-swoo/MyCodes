
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

class BRUTE {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new BRUTE().ace();
    }
    //Solution !!
    void solution()
    {
        int n=inti(),m=inti(),good=0;
        char arr[][]=new char[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                arr[i][j]=chi();
                if(arr[i][j]=='G')
                    good++;
            }
        }
        int s1,s2,count=0;
        char temp[][]=copy(arr, n, m);
//        pa(temp);
//        pa(arr);
        if(good<=1)
            out.println(0);
        else if(good<5)
            out.println(1);
        else{
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(arr[i][j]=='G'){
                    s1=0;
                    while(plus(arr, i, j,s1)){
//                        pa(temp);
//                        pa(arr);
                        temp=change(temp, i, j, s1);
                        for(int k=0;k<n;k++){
                            for(int h=0;h<m;h++){
                                if(temp[k][h]=='G'){
                                    s2=1;
                                    while(plus(temp, k, h, s2)){
                                        s2++;
                                    }
                                    int q=(4*(s2-1)+1),w=(4*(s1)+1);
                                    if(q*w>count){
                                       count=q*w;
                                    }
                                }
                            }
                        }
                        temp=copy(arr, n, m);
                        s1++;
                    }
                    
                }
            }
        }
        out.println(count);
        }
    }
    
    char[][] copy(char arr[][],int n,int m){
        char array[][]=new char[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                array[i][j]=arr[i][j];
            }
        }
        return array;
    }
    
    boolean plus(char arr[][],int i,int j,int u){
        if((i-u)<0 || (i+u)>=arr.length || (j-u)<0 || (j+u)>=arr[0].length)
            return false;
        return !(arr[i-u][j]=='B' || arr[i+u][j]=='B' || arr[i][j+u]=='B' || arr[i][j-u]=='B');
    }
    
    char[][] change(char[][] arr,int i,int j,int size){
        for(int l=i-size;l<=i+size;l++)
            arr[l][j]='B';
        for(int l=j-size;l<=j+size;l++)
            arr[i][l]='B';
        return arr;
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

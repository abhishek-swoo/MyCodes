
//package standard;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class hacko {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new hacko().ace();
    }
    //Solution !!
    void solution()
    {
        
        int n=inti(),m=inti(),index=0,max,count=0;
        int arr[]=arri(n);
        ArrayList<Integer> list[]=new ArrayList[100001];
        for(int i=0;i<=100000;i++)
            list[i]=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            list[arr[i]].add(i);
        }
        RmqSparseTabl st = new RmqSparseTabl(arr);
        for(int i=0;i<m;i++)
        {
            int l=inti()-1,r=inti()-1;
            count=0;
            index=st.minPos(l, r);
            max=arr[index];
//            System.out.println(max +" max");
            for(int j=0;j<list[max].size();j++)
            {
//                System.out.println(list[max].get(j));
                if(list[max].get(j)>=l && list[max].get(j)<=r)
                    count++;
            }
            out.println(count);
        }
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
// this part of code is copied from https://sites.google.com/site/indy256/algo/sparse_table_rmq
class RmqSparseTabl {

  int[] logTable;
  int[][] rmq;
  int[] a;

  public RmqSparseTabl(int[] a) {
    this.a = a;
    int n = a.length;

    logTable = new int[n + 1];
    for (int i = 2; i <= n; i++)
      logTable[i] = logTable[i >> 1] + 1;

    rmq = new int[logTable[n] + 1][n];

    for (int i = 0; i < n; ++i)
      rmq[0][i] = i;

    for (int k = 1; (1 << k) < n; ++k) {
      for (int i = 0; i + (1 << k) <= n; i++) {
        int x = rmq[k - 1][i];
        int y = rmq[k - 1][i + (1 << k - 1)];
        rmq[k][i] = a[x] >= a[y] ? x : y;
      }
    }
  }

  public int minPos(int i, int j) {
    int k = logTable[j - i];
    int x = rmq[k][i];
    int y = rmq[k][j - (1 << k) + 1];
    return a[x] >= a[y] ? x : y;
  }
}
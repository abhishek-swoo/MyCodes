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
class firesc {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new firesc().ace();
    }
    //Solution !!
    void solution()
    {
        for(int tn=inti();tn>0;tn--)
        {
            int n=inti();
            int m=inti();
            int flag=0,count=0,cnt=0;
            int[] visit=new int[n+1];
            ArrayList<Integer>[] arr=new ArrayList[n+1];
            for(int i=0;i<=n;i++)
            {
                arr[i]=new ArrayList<Integer>();
            }
            for(int i=0;i<m;i++)
            {
                int j=inti(),k=inti();
                arr[k].add(j);
                arr[j].add(k);
            }
            for(int i=1;i<=n;i++)
            {
                int a=arr[i].size();
                if(visit[i]==0)
                {
                    for(int j=0;j<a;j++)
                    {
                        int p=arr[i].get(j);
                        if(visit[p]==0)
                        {
                            visit[p]=1;
                            cnt++;
                        }
                        else
                            flag=1;
                    }
                    if(flag==1)
                    {
                        flag=0;
                    }
                    else
                    {
                        count++;
                        cnt++;
                    }
                }
                
            }
            System.out.println(count+" "+cnt);
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


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
class array {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException
    {
        new array().ace();
    }
    //Solution !!
    void solution()
    {
        int n=inti(),q=inti();
        int count=n+1;
        ArrayList<Integer>[] arr=new ArrayList[1000000];
        for(int i=0;i<1000000;i++)
            arr[i]=new ArrayList<Integer>();
        for(int i=0;i<=n;i++)
        {
            arr[i].add(i);
        }
        for(int i=0;i<q;i++)
        {
            String aa=stri();
            int a=inti(),b=inti();
            if(aa.charAt(0)=='U')
            {
                int size1=arr[a].size();
                int size2=arr[b].size();
                //System.out.println(size1+" "+size2);
                int z1=0,z2=0;
                while(size1>0 && size2>0)
                {
                    //System.out.println(arr[b].get(z2)+ " "+z2+" "+z1+" "+arr[a].get(z1)+" "+arr[b].get(z2)+" "+size1+" "+size2);
                    if(arr[a].get(z1)<arr[b].get(z2))
                    {
                        int tp=arr[a].get(z1);
                        arr[count].add(tp);
                        //arr[a].remove(z1);
                        size1--;
                        z1++;
                        
                    }
                    else
                    {
                        int tp=arr[b].get(z2);
                        arr[count].add(tp);
                        //arr[b].remove(z2);
                        size2--;
                        z2++;
                    }
                    
                }
                while(size1>0)
                {
                    
                    arr[count].add(arr[a].get(z1));
                    //arr[a].remove(z1);
                    z1++;
                    size1--;
                }
                while(size2>0)
                {
                    
                    arr[count].add(arr[b].get(z2));
                    //arr[b].remove(z2);
                    z2++;
                    size2--;
                }
                count++;
            }
            else if(aa.charAt(0)=='G')
            {
                int qw=arr[a].get(b-1);
                System.out.println(qw);
            }
//            for(int j=0;j<=15;j++)
//                System.out.println(arr[j]);
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

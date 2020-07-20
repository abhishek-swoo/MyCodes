
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class aug {
    boolean flag=false;
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new aug().ace();
    }
    //Solution !!
    long arr[][];
    long arr1[][];
    long freq[];
    void solution()
    {
        int n=inti(),m=inti();
        long sum=0,count=0;
        arr =new long[n][2];
        arr1 =new long[n][2];
        boolean fl=true;
//        freq=new int[n];
        long freq1[];
        for(int i=0;i<n;i++)
        {
            arr[i][0]=loni();
        }
        search(0,n-1);
//        lambda operator for pairwise sorting
        Arrays.sort(arr,(long a[],long b[]) ->
        {
           if(a[0]<b[0])
               return -1;
           return 1;
        });
        sum=arr[0][1];
        for(int i=1;i<n;i++)
        {
            if(arr[i-1][0]!=arr[i][0])
            {
//                System.out.println("count "+count+"i "+i);
                arr1[(int)count][0]=arr[i-1][0];
                arr1[(int)count][1]=sum;
                count++;
                sum=arr[i][1];
                fl=false;
            }
            if(fl)
                sum += arr[i][1];
            fl=true;
            
        }
        arr1[(int)count][0]=arr[n-1][0];
        arr1[(int)count][1]=sum;
        freq=new long[(int)count+1];
        freq1=new long[(int)count+1];
        freq[0]=arr1[0][1];
        freq1[0]=arr1[0][0];
        for(int i=1;i<=count;i++)
        {
            freq[i]=freq[i-1]+arr1[i][1];
            freq1[i]=arr1[i][0];
        }
//        for(int i=0;i<n;i++)
//        {
//            System.out.println(arr[i][0]+" "+arr[i][1]);
//        }
//        System.out.println();
//        for(int i=0;i<=count;i++)
//        {
//            out.println(freq1[i]+" "+arr1[i][1]+" freq "+freq[i]);
//        }
//        System.out.println("count "+count);
        for(int i=0;i<m;i++)
        {
            char c=chi();
            long d=loni();
            char e=chi();
            long k1=0;
//            out.println(c+" c");
            int m1=Arrays.binarySearch(freq1, d);
//            out.println("m1 "+ m1);
            if(m1>=0)
            {
                if(c=='>')
                {
                    k1=(freq[(int)count]-freq[m1]);
//                    System.out.println("here 1");
//                    System.out.println("k1 "+k1);
                    if(k1%2==1)
                    {
                        out.print(e);
                    }
                    else if(k1%2==0 && e=='C')
                    {
                        out.print("D");
                    }
                    else if(k1%2==0 && e=='D')
                    {
                       out.print("C");
                    }
                }
                else if(c=='<')
                {
                    if(m1>0)
                        k1=(freq[m1-1]);
                    else if(m1==0)
                        k1=0;
//                    System.out.println("here2");
//                    System.out.println("k1 "+k1);
                    if(k1%2==1)
                    {
                        out.print(e);
                    }
                    else if(k1%2==0 && e=='C')
                    {
                        out.print("D");
                    }
                    else if(k1%2==0 && e=='D')
                   {
                        out.print("C");
                    }
                }
                else if(c=='=')
                {
                    k1=arr1[m1][1];
//                    System.out.println("here 3");
//                    System.out.println(k1+" k1");
                    if(k1%2==1)
                    {
                        out.print(e);
                    }
                    else if(k1%2==0 && e=='C')
                    {
                        out.print("D");
                    }
                    else if(k1%2==0 && e=='D')
                    {
                        out.print("C");
                    }
                }
            }
            else if(m1<0)
            {
                if(c=='>')
                {
                    m1=m1-m1*2;
                    m1=m1-1;
                    if(m1>0)
                    k1=(freq[(int)count]-freq[m1-1]);
                    else if(m1==0)
                        k1=freq[(int)count];
//                    System.out.println("here4 ");
//                    System.out.println("k1 "+k1);
                    if(k1%2==1)
                    {
                        out.print(e);
                    }
                    else if(k1%2==0 && e=='C')
                    {
                        out.print("D");
                    }
                    else if(k1%2==0 && e=='D')
                    {
                       out.print("C");
                    }
                }
                else if(c=='<')
                {
                    m1=m1-m1*2;
                    m1=m1-1;
                    if(m1>0)
                        k1=(freq[m1-1]);
                    else if(m1==0)
                        k1=0;
//                System.out.println("here5");
//                    System.out.println("k1 "+k1);
                    if(k1%2==1)
                    {
                        out.print(e);
                    }
                    else if(k1%2==0 && e=='C')
                    {
                        out.print("D");
                    }
                    else if(k1%2==0 && e=='D')
                    {
                        out.print("C");
                    }
                }
                else if(c=='=')
                {
//                    System.out.println("here6 ");
                    if(e =='D')
                       out.print("C");
                   else
                       out.print("D"); 
                }
            }
        }
    }
    long maximum(int l,int r)
    {
        long max=arr[l][0],maxin=l;
        for(int i=l;i<=r;i++)
        {
            if(arr[i][0]>max)
            {
                max=arr[i][0];
                maxin=i;
            }
        }
        return maxin;
    }
    void search(int d,int n)
    {
        if(d>n)
            return;
        int maxin=(int)maximum(d,n);
        arr[maxin][1]=(((long)(n-d+1)*(n-d+2))/2 - ((long)(maxin-d)*(maxin-d+1))/2 - ((long)(n-maxin)*(n-maxin+1))/2);
        search(d, maxin-1);
        search(maxin+1, n);
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

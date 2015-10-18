
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class fibonacci 
{
    public static void main(String args[])throws IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter("D:\\input.txt");
        int n=Integer.parseInt(obj.readLine());
        int i=2;
        long arr[]=new long[n];
        arr[0]=1;
        arr[1]=1;
        while(i<n)
        {
            arr[i]=arr[i-1]+arr[i-2];
            i++;
        }
        for(int k=0;k<i;k++)
        {
            out.print(arr[k]+",");
            
        }
        out.close();
    }
}

package standard;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class rodcutting_DP 
{
    public static void main(String args[])throws IOException
    {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int k=0;k<t;k++)
        {
            System.out.println("Enter the length of rod");
            int length=Integer.parseInt(obj.readLine());
            System.out.println("Enter Space Seprated prices");
            int[] price=new int[length];
            String[] str=obj.readLine().split(" ");
            for(int z=0;z<str.length;z++)
            {
                price[z]=Integer.parseInt(str[z]);
            }
            int max=-9999;
            int array[]=new int[length+1];
            int s[]=new int[length];
            array[0]=0;
            for(int i=1;i<=length;i++)
            {
                max=-9999;
                for(int j=0;j<i;j++)
                {
                    if(max<(price[j]+array[i-j-1]))
                    {
                        max=price[j]+array[i-j-1];
                        s[i-1]=j+1;
                    }
                }
                array[i]=max;
            }
            System.out.println(max);
            while(length>0)
            {
                System.out.print(s[length-1]+" ");
                length=length-s[length-1];
            }
            System.out.println();
        }
    }
}
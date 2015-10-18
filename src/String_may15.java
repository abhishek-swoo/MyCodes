
package cc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author ABHISHEK SHANKHADHAR
 */
class String_may15 
{
    public static void main(String args[])throws IOException
    {
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder s3=new StringBuilder("");
        int t=Integer.parseInt(obj.readLine());
        for(int i=0;i<t;i++)
        {
            String[] str=obj.readLine().split(" ");
            int n=Integer.parseInt(str[0]);
            int k=Integer.parseInt(str[1]);
            int count1=0,count2=0,count=0;
            String s=obj.readLine();
            if(k>1)
            {
                for(int j=0;j<n;j++)
                {
                    if(s.charAt(j)=='0')
                    {
                        count1++;
                        count2=0;
                    }
                    else
                    {
                        count2++;
                        count1=0;
                    }
                    if((j+1)<n)
                        if(count1 == k+1 && s.charAt(j+1)=='0')
                        {
                            s = s.substring(0,j)+'1'+s.substring(j+1);
                            count1=0;
                            count++;
                        }
                    if(count1 == k+1)
                    {
                        if((j+1)>=n)
                            s = s.substring(0,j)+'1';
                        else if((j+1)<n && s.charAt(j+1)=='1')
                            s = s.substring(0,j-1)+'1'+s.substring(j);
                        count1=0;
                        count++;
                    }
                    if((j+1)<n)
                        if(count2 == k+1 && s.charAt(j+1)=='1')
                        {
                            s = s.substring(0,j)+'0'+s.substring(j+1);
                            count2=0;
                            count++;
                        }
                    if(count2 == k+1)
                    {
                        if((j+1)>=n)
                            s = s.substring(0,j)+'0';
                        else if((j+1)<n && s.charAt(j+1)=='0')
                            s = s.substring(0,j-1)+'0'+s.substring(j);
                        count2=0;
                        count++;
                    }
                }
            }
            else if(k==1)
            {
                s3.setLength(0);
                for(int j=0;j<n;j++)
                {
                    if(s.charAt(j)=='0')
                        count1++;
                    if((j+1)<n)
                        if(s.charAt(j+1)=='1')
                            count1++;
                    if(s.charAt(j)=='1')
                        count2++;
                    if((j+1)<n)
                        if(s.charAt(j+1)=='0')
                            count2++;
                    if(count1<count2)
                        count=count1;
                    else
                        count=count2;
                    j++;
                }
                
                if(count1<count2)
                {
                    for(int j=0;j<n;j++)
                    {
                        if(j%2==0)
                            s3.append("1");
                        else
                            s3.append("0");
                    }
                }
                else
                {
                    for(int j=0;j<n;j++)
                    {
                        if(j%2==0)
                            s3.append("0");
                        else
                            s3.append("1");
                    }
                }
            }
            System.out.println(count);
            if(k!=1)
                System.out.println(s);
            if(k==1)
                System.out.println(s3);
        }
    }
}

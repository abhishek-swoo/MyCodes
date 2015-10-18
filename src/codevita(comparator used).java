import java.io.*;
import java.util.*;
class codevita
{
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int tc=0;tc<t;tc++)
        {
            String ss[]=obj.readLine().split(" ");
            int l=ss.length/2;
            int a[][]=new int[l][2];
            String s[][]=new String[l][2];
            int b[]=new int[l];
            String d[]=new String[l];
            int j=0,k=0;
            for(int i=0;i<ss.length;i++)
            {
                s[k][0]=ss[i].toLowerCase(); 
                d[k]=s[k][0];
                s[k][1]=String.valueOf(k);
                i++;
                k++;
                a[j][0]=Integer.parseInt(ss[i]);
                b[j]=a[j][0];
                a[j][1]=j;
                j++;
            }
//            pairwise sort using comparator
                java.util.Arrays.sort(a, new Comparator<int[]>() 
                {
                    public int compare(int[] entry1, int[] entry2) {
                    return entry1[0]-entry2[0];
                }
                });
            System.out.println("Original Array");
            for(int i=0;i<l;i++)
            {
                System.out.println(b[i]+" "+i);
            }
            System.out.println("Sorted array");
            for(int i=0;i<l;i++)
            {
                System.out.println(a[i][0]+" "+a[i][1]);
            }
                java.util.Arrays.sort(s, new Comparator<String[]>() 
                {
                    @Override
                    public int compare(String[] entry1, String[] entry2) {
                    return entry1[0].compareTo(entry2[0]);
                }
                });
            
            System.out.println("Original Array");
            for(int i=0;i<l;i++)
            {
                System.out.println(d[i]+" "+i);
            }
            System.out.println("Sorted array");
            for(int i=0;i<l;i++)
            {
                System.out.println(s[i][0]+" "+a[i][1]);
            }
        }
    }
}
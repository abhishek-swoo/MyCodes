import java.io.*;
class codevita1
{
    public static void main(String[] args)throws IOException{
   BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
   int tt=Integer.parseInt(obj.readLine());
   for(int ii=0;ii<tt;ii++)
   {
       String s[]=obj.readLine().split(" ");
       int f=Integer.parseInt(s[0]);
       int b=Integer.parseInt(s[1]);
       int t=Integer.parseInt(s[2]);
       int fd=Integer.parseInt(s[3]);
       int bd=-Integer.parseInt(s[4]);
       if(fd<=f)
       {
           System.out.println(((long)fd*t)+" F");
       }
       else if(f-b==0)
       {
           System.out.println("No Ditch");
       }
       else if(f-b>0)
       {
           int x=0,T=0,count=0;
           while(x<fd)
           {
               if(count==0)
               {
                   x=x+f;
                   T+=f*t;
                   count++;
               }
               else{
                   count=0;
                   x=x-b;
                   T+=b*t;
               }
           }
           if(x!=fd)
           {
               T=T-(x-fd)*t;
           }
           System.out.println(T+" F");
       }
       else if(f-b<0)
       {
           int x=0,T=0,count=0;
           while(x>bd)
           {
               if(count==0)
               {
                   x=x+f;
                   T+=f*t;
                   count++;
               }
               else{
                   count=0;
                   x=x-b;
                   T+=b*t;
               }
           }
           if(x!=bd)
           {
               T=T-(bd-x)*t;
           }
           System.out.println(T+" B");
       }
   }
}
}


/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class cvaaa {
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
         String c=obj.readLine();
        if(c.charAt(0)=='E'){
            String s=obj.readLine();
            String num=obj.readLine();
            int len=num.length();
            StringBuilder sb=new StringBuilder();
            sb.append(len);
            sb.append('-');
            sb.append(num);
            for(int i=0;i<s.length();i++){
                int ch=s.charAt(i);
                int arr[]=dec(ch, 64, 16);
                StringBuilder sb1=new StringBuilder();
                boolean b=false;
                for(int j=0;j<arr.length;j++){
                    if(arr[j]!=0){
                        b=true;
                    }
                    if(b){
                        if(arr[j]<10)
                            sb1.append(arr[j]);
                        else {
                            switch (arr[j]) {
                                case 10:
                                    sb1.append('A');
                                    break;
                                case 11:
                                    sb1.append('B');
                                    break;
                                case 12:
                                    sb1.append('C');
                                    break;
                                case 13:
                                    sb1.append('D');
                                    break;
                                case 14:
                                    sb1.append('E');
                                    break;
                                case 15:
                                    sb1.append('F');
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                sb1.reverse();
//                String sw=sb1.toString();
                sb.append('-');
                sb.append(sb1);
            }
            System.out.println(sb);
        }
        else {
            String s=obj.readLine();
            String string[]=s.split("-");
            System.out.println(string[1]);
//            for(int i=2;i<string.length;i++){
//                string[i]=rev(string[i]);
//            }
            StringBuilder sb=new StringBuilder();
            for(int i=2;i<string.length;i++){
                int a[]=new int[64];
                int num=63;
                for(int j=0;j<string[i].length();j++){
                    char ch=string[i].charAt(j);
                    int ans=-1;
                    if(ch=='A'){
                        a[num--]=10;
                    }
                    else if(ch=='B'){
                        a[num--]=11;
                    }
                    else if(ch=='C'){
                        a[num--]=12;
                    }
                    else if(ch=='D'){
                        a[num--]=13;
                    }
                    else if(ch=='E'){
                        a[num--]=14;
                    }else if(ch=='F'){
                        a[num--]=15;
                    }
                    else {
                        a[num--]=ch-'0';
                    }
                }
//                pa(a);
                int en=enc(a, 16);
                sb.append((char)en);
            }
            System.out.println(sb);
        }
    }
    static int enc(int[] a, int d) {
        int code = 0;
        for (int i = 0; i < a.length; i++) {
            code = code * d + a[i];
        }
        return code;
    }
    
    StringBuilder fun(int n){
        StringBuilder sb=new StringBuilder();
        while (n!=0) {            
            sb.append(n%10);
            n/=10;
        }
        return sb;
    }
    String rev(String s){
        String s1="";
        for(int i=s.length()-1;i>=0;i--){
            s1+=s.charAt(i);
        }
        return s1;
    }
    static int[] dec(int code, int n, int d) {
        int[] a = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            a[i] = code % d;
            code /= d;
        }
        return a;
    }
}

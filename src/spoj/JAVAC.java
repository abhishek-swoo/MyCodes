
package spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Abhishek Shankhadhar
 */


class JAVAC {
    String check="";
    public static void main(String[] args)throws IOException{
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        while (true) {            
            String s=obj.readLine();
            if(s==null)
                break;
            String string="";
            boolean cpp=false,java=false,tem_cpp=false;
            for(int i=0;i<s.length();i++){
                if(s.charAt(0)=='_' || (s.charAt(0)>='A' && s.charAt(0)<='Z')){
                    java=true;
                    cpp=true;
                }
                if((i>0 && s.charAt(i)=='_' && s.charAt(i-1)=='_') || (s.charAt(s.length()-1)=='_')){
                    cpp=true;
                    java=true;
                }
                if(s.charAt(i)=='_'){
                    cpp=true;   
                }
                else if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
                    java=true;
//                    System.out.println("fuck");
                }
                else if(s.charAt(i)<'A' || (s.charAt(i)>'Z' && s.charAt(i)<'a') || s.charAt(i)>'z'){
                    java=true;
                    cpp=true;
//                    System.out.println("Hello");
                }
            }
            if(cpp && java){
                System.out.println("Error!");
                continue;
            }
            if(cpp){
                for(int i=0;i<s.length();i++){
                    if(tem_cpp){
                        string=string + (char)(s.charAt(i)+'A'-'a');
                        tem_cpp=false;
                        continue;
                    }
                    if(s.charAt(i)=='_'){
                        tem_cpp=true;
                        continue;
                    }
                    string+=s.charAt(i);
                }
            }
            else {
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)>='A' && s.charAt(i)<='Z'){
                        string+='_';
                        string+=(char)(s.charAt(i)+'a'-'A');
                        continue;
                    }
                    string+=s.charAt(i);
                }
            }
            System.out.println(string);
        }
    }
}

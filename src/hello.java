
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 *
 * @author Abhishek Shankhadhar
 */
class hello {
    static PrintWriter out;
    public static void main(String[] args)throws IOException{
        out=new PrintWriter(System.out);
        BufferedReader obj=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(obj.readLine());
        for(int i=0;i<t;i++){
            String[] tmp=obj.readLine().split(" ");
            int n=Integer.parseInt(tmp[0]),m=Integer.parseInt(tmp[1]);
            int arr[][]=new int[n][m];
            for(int k=0;k<n;k++){
                String[] tmp1=obj.readLine().split(" ");
                for(int j=0;j<m;j++){
                    arr[k][j]=Integer.parseInt(tmp1[j]);
                }
            }
            RightdiagCheck(arr, n, m);
            out.println();
        }
        out.close();
    }
    static void RightdiagCheck(int arr[][],int r,int c){
        for(int i=0;i<=r+c-1;i++){
            int u=0;
            int v=i; 
            while(v>=0){
                if(u<r && v<c){
                    out.print(arr[u][v]+" ");
                }
                u++;
                v--;
            }
        }
    }
}
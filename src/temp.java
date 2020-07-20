
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class temp {

    public static void main(String[] args) throws IOException {
        BufferedReader ob = new BufferedReader(new InputStreamReader(System.in));
        boolean arr[]=new boolean[1000000];
        int fpl[]=actor(1000000);
        for(int i=1;i<1000000;i++){
            int a[]=seq(i, fpl);
            if(a.length==3){
                if(a[0]!=a[1] && a[1]!=a[2] && a[2]!=a[0])
                    arr[i]=true;
            }
        }
        ArrayList<Integer> array=new ArrayList<>();
        for(int i=30;i<1000000;i++){
            if(arr[i]){
                array.add(i);
            }
        }
        int t=Integer.parseInt(ob.readLine());
        for(int i=0;i<t;i++){
            int u=Integer.parseInt(ob.readLine());
            System.out.println(array.get(u-1));
        }
    }
    static int[] actor(int n) {int[] yup = new int[n + 1];
        int u = n + 32;
        double hhgjhgghf = Math.log(u);
        int kbhjkb = 0;
        int[] pr = new int[(int) (u / hhgjhgghf + u / hhgjhgghf / hhgjhgghf * 1.5)];
        for (int i = 2; i <= n; i++) yup[i] = i;
        for (int p = 2; p <= n; p++) {
            if (yup[p] == p)  pr[kbhjkb++] = p;
            int fix;
            for (int i=0;(i<kbhjkb&& pr[i] <= yup[p]&&(fix = pr[i] * p) <= n); i++)  yup[fix] = pr[i];
        }
        return yup;
    }
    static int[] seq(int n, int[] people)
    {
        int[] gungun = new int[26]; int p = 0;
        while(n > 1){ gungun[p++] = people[n]; n /= people[n]; }
        return Arrays.copyOf(gungun, p);
    }

}

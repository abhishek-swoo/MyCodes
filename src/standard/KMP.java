package standard;
class KMP
     {
         public static void main(String[] args) throws java.io.IOException
         {
             java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
             int t = Integer.parseInt(br.readLine());
             StringBuilder sb2 = new StringBuilder();
             for (int h = 0; h < t; h++)
             {
                 int n = Integer.parseInt(br.readLine());
                 String s = br.readLine();
                 String pat = br.readLine();
                 int m = pat.length();
                 int arr[] = new int[m];
                 arr[0] = 0;
                 int k = 0;
                 for (int i = 1; i < m; i++)
                 {
                     while (k > 0 && pat.charAt(k) != pat.charAt(i))
                     {
                         k = arr[k - 1];
                     }
                     if (pat.charAt(k) == pat.charAt(i))
                     {
                         k = k + 1;
                     }
                     arr[i] = k;
                 }
                 k = 0;
                 int count = 0;
                 for (int i = 0; i < s.length(); i++)
                 {
                     while (k > 0 && pat.charAt(k) != s.charAt(i))
                     {
                         k = arr[k - 1];
                     }
                     if (pat.charAt(k) == s.charAt(i))
                     {
                         k = k + 1;
                     }
                     if (k == m)
                     {
                         count++;
                         k = arr[k - 1];
                     }
                 }
                 sb2.append(count).append("\n");
             }
             System.out.print(sb2);
         }
     } 
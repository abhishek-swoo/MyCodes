
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class Codevita_chess {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        char s[] = stri().toCharArray();
        char board[][] = new char[8][8];
        int n=inti();
        int x = 0, y = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '/') {
                continue;
            }
            if ('1' <= s[i] && s[i] <= '8') {
                int count = s[i] - '0';
                for (int o = 0; o < count; o++) {
                    board[x][y++] = '-';
                }
            } else {
                board[x][y++] = s[i];
            }
            if (y >= 8) {
                x++;
                y = 0;
            }
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('P', 0);
        hashMap.put('R', 1);
        hashMap.put('B', 2);
        hashMap.put('Q', 3);
        hashMap.put('K', 4);
        HashSet<String> hashSet=new HashSet<>();
        char horizontal[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int vertical[] = {8, 7, 6, 5, 4, 3, 2, 1};
//        System.out.println(possibleMove(board, 0, 2, 1));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(hashMap.containsKey(board[i][j])){
                    ArrayList<String> al=possibleMove(board, i, j, hashMap.get(board[i][j]));
                    for(int k=0;k<al.size();k++){
                        String s1[]=al.get(k).split(" ");
                        int h=Integer.parseInt(s1[0]);
                        int v=Integer.parseInt(s1[1]);
                        String s2="";
                        
                        s2+=board[i][j];
                        s2+=horizontal[i];
                        s2+=vertical[j];
                        s2+="-";
                        s2+=horizontal[h];
                        s2+=vertical[v];
                        hashSet.add(s2);
                    }
                }
            }
        }
        ArrayList<String> arrayList=new ArrayList<>();
        Iterator I=hashSet.iterator();
        while (I.hasNext()) {            
            arrayList.add((String)I.next());
        }
        Collections.sort(arrayList);
        System.out.println(arrayList);
    }

    boolean check(char board[][], int x, int y, int kx, int ky, int p) {
//        p=0 pawn
        if (p == 0) {
            if (x + 1 <= 7 && y + 1 <= 7 && board[x + 1][y + 1] == 'k') {
                return true;
            } else if (x - 1 >= 0 && y + 1 <= 7 && board[x - 1][y + 1] == 'k') {
                return true;
            }
            return false;
        } //        p==1 elephant/rook
        else if (p == 1) {
            int i = -8;
            while (true) {
                if (x + i > 8) {
                    break;
                }
                if (x + i >= 0 && i != 0 && x + i <= 7 && (board[x + i][y] == '-' || board[x + i][y] == 'k')) {
                    if (board[x + i][y] == 'k') {
                        return true;
                    }
                }
                if (x + i >= 0 && i != 0 && x + i <= 7 && (board[x + i][y] != '-' && board[x + i][y] != 'k')) {
                    break;
                }
                i++;
            }
            int j = -8;
            while (true) {
                if (y + j > 8) {
                    break;
                }
                if (y + j >= 0 && j != 0 && y + j <= 7 && board[x][y + j] == 'k') {
                    return true;
                }
                if (y + j >= 0 && j != 0 && y + j <= 7 && (board[x][y + j] != '-' && board[x][y + j] != 'k')) {
                    break;
                }
                j++;
            }
            return false;
        } //        p==2 bishop
        else if (p == 2) {
            int i = -8, j = -8;
            while (true) {
                if (x + i >= 8 || y + j >= 8) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && board[x + i][y + j] == 'k') {
                    return true;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != '-' && board[x + i][y + j] != 'k')) {
                    break;
                }
                i++;
                j++;
            }
            i = 0;
            j = 8;
            while (true) {
                if (x + i >= 8 || y + j < 0) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && board[x + i][y + j] == 'k') {
                    return true;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != '-' && board[x + i][y + j] != 'k')) {
                    break;
                }
                i++;
                j--;
            }
            return false;
        } //        p=3 queen
        else if (p == 3) {
            int i = -8;
            while (true) {
                if (x + i > 8) {
                    break;
                }
                if (x + i >= 0 && i != 0 && x + i <= 7 && (board[x + i][y] == '-' || board[x + i][y] == 'k')) {
                    if (board[x + i][y] == 'k') {
                        return true;
                    }
                }
                if (x + i >= 0 && i != 0 && x + i <= 7 && (board[x + i][y] != '-' && board[x + i][y] != 'k')) {
                    break;
                }
                i++;
            }
            int j = -8;
            while (true) {
                if (y + j > 8) {
                    break;
                }
                if (y + j >= 0 && j != 0 && y + j <= 7 && board[x][y + j] == 'k') {
                    return true;
                }
                if (y + j >= 0 && j != 0 && y + j <= 7 && (board[x][y + j] != '-' && board[x][y + j] != 'k')) {
                    break;
                }
                j++;
            }
            i = -8;
            j = -8;
            while (true) {
                if (x + i >= 8 || y + j >= 8) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && board[x + i][y + j] == 'k') {
                    return true;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != '-' && board[x + i][y + j] != 'k')) {
                    break;
                }
                i++;
                j++;
            }
            i = 0;
            j = 8;
            while (true) {
                if (x + i >= 8 || y + j < 0) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && board[x + i][y + j] == 'k') {
                    return true;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != '-' && board[x + i][y + j] != 'k')) {
                    break;
                }
                i++;
                j--;
            }
            return false;
        } //        p=4 knight
        else if (p == 4) {
            if (x + 2 <= 7) {
                if (y - 1 >= 0 && board[x + 2][y - 1] == 'k') {
                    return true;
                }
                if (y + 1 <= 7 && board[x + 2][y + 1] == 'k') {
                    return true;
                }
            }
            if (x - 2 >= 0) {
                if (y - 1 >= 0 && board[x - 2][y - 1] == 'k') {
                    return true;
                }
                if (y + 1 <= 7 && board[x - 2][y + 1] == 'k') {
                    return true;
                }
            }
            if (x + 1 <= 7) {
                if (y - 2 >= 0 && board[x + 1][y - 2] == 'k') {
                    return true;
                }
                if (y + 2 <= 7 && board[x + 1][y + 2] == 'k') {
                    return true;
                }
            }
            if (x - 1 >= 0) {
                if (y - 2 >= 0 && board[x - 1][y - 2] == 'k') {
                    return true;
                }
                if (y + 2 <= 7 && board[x - 1][y + 2] == 'k') {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    ArrayList<String> possibleMove(char board[][], int x, int y, int p) {
        ArrayList<String> al = new ArrayList<>();
        //        p=0 pawn
        if (p == 0) {
            if (x + 1 <= 7 && (y + 1) <= 7 && board[x + 1][y + 1] != 'k' && board[x + 1][y + 1] != '-') {
                al.add((x + 1) + " " + (y + 1));
            } else if (x - 1 >= 0&& y+1<=7 && board[x - 1][y + 1] != 'k' && board[x + 1][y + 1] != '-') {
                al.add((x - 1) + " " + (y + 1));
            }
            if (x + 1<=7 && board[x+1][y]=='-') {
                al.add((x + 1) + " " + y);
            }
        }
//        p=1 rook
        else if (p == 1) {
            int i = -8;
            while (true) {
                if (x + i > 8) {
                    break;
                }
                if (x + i >= 0 && x + i <= 7 && (board[x + i][y] == '-' || board[x + i][y] != 'k')) {
                    
                        al.add((x+i)+" "+y);
                    
                    if(board[x+i][y]!='-')
                        break;
                }
                i++;
            }
            int j = -8;
            while (true) {
                if (y + j > 8) {
                    break;
                }
                if (j!=0 && y + j >= 0 && y + j <= 7 && (board[x][y + j] != 'k' || board[x][y+j]=='-')) {
                    
                        al.add(x+" "+(y+j));
                    
                    if(board[x][y+j]!='-')
                        break;
                }
                
                j++;
            }
        }
//        p==2 bishop
        else if (p == 2) {
            int i = -8, j = -8;
            while (true) {
                if (x + i >= 8 || y + j >= 8) {
                    break;
                }
                if (x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != 'k' || board[x + i][y + j] == '-')) {
                    al.add((x+i)+" "+(y+j));
                    if(board[x+i][y+j]!='-')
                        break;
                }
                i++;
                j++;
            }
            i = 0;
            j = 8;
            while (true) {
                if (x + i >= 8 || y + j < 0) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != 'k' || board[x + i][y + j] == '-')) {
                    al.add((x+i)+" "+(y+j));
                    if(board[x+i][y+j]!='-')
                        break;
                }
                i++;
                j--;
            }
        }
//        p==3 queen
        else if (p == 3) {
            int i = -8;
            while (true) {
                if (x + i > 8) {
                    break;
                }
                if (x + i >= 0 && x + i <= 7 && (board[x + i][y] == '-' || board[x + i][y] != 'k')) {
                    
                        al.add((x+i)+" "+y);
                    
                    if(board[x+i][y]!='-')
                        break;
                }
                i++;
            }
            int j = -8;
            while (true) {
                if (y + j > 8) {
                    break;
                }
                if (j!=0 && y + j >= 0 && y + j <= 7 && (board[x][y + j] != 'k' || board[x][y+j]=='-')) {
                    
                        al.add(x+" "+(y+j));
                    
                    if(board[x][y+j]!='-')
                        break;
                }
                
                j++;
            }
            i = -8; j = -8;
            while (true) {
                if (x + i >= 8 || y + j >= 8) {
                    break;
                }
                if (i!=0 && j!=0 && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != 'k' || board[x + i][y + j] == '-')) {
                    al.add((x+i)+" "+(y+j));
                    if(board[x+i][y+j]!='-')
                        break;
                }
                i++;
                j++;
            }
            i = 0;
            j = 8;
            while (true) {
                if (x + i >= 8 || y + j < 0) {
                    break;
                }
                if (!(i == 0 && j == 0) && x + i >= 0 && x + i <= 7 && y + j >= 0 && y + j <= 7 && (board[x + i][y + j] != 'k' || board[x + i][y + j] == '-')) {
                    al.add((x+i)+" "+(y+j));
                    if(board[x+i][y+j]!='-')
                        break;
                }
                i++;
                j--;
            }
        }
        else if(p==4){
            if (x + 2 <= 7) {
                if (y - 1 >= 0 && board[x + 2][y - 1] != 'k') {
                    al.add((x+2)+" "+(y-1));
                }
                if (y + 1 <= 7 && board[x + 2][y + 1] != 'k') {
                    al.add((x+2)+" "+(y+1));
                }
            }
            if (x - 2 >= 0) {
                if (y - 1 >= 0 && board[x - 2][y - 1] != 'k') {
                    al.add((x-2)+" "+(y-1));
                }
                if (y + 1 <= 7 && board[x - 2][y + 1] != 'k') {
                    al.add((x-2)+" "+(y+1));
                }
            }
            if (x + 1 <= 7) {
                if (y - 2 >= 0 && board[x + 1][y - 2] != 'k') {
                    al.add((x+1)+" "+(y-2));
                }
                if (y + 2 <= 7 && board[x + 1][y + 2] != 'k') {
                    al.add((x+1)+" "+(y+2));
                }
            }
            if (x - 1 >= 0) {
                if (y - 2 >= 0 && board[x - 1][y - 2] != 'k') {
                    al.add((x-1)+" "+(y-2));
                }
                if (y + 2 <= 7 && board[x - 1][y + 2] != 'k') {
                    al.add((x-1)+" "+(y-2));
                }
            }
        }
        return al;
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Codevita_chess().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }

    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("location of file") : new ByteArrayInputStream(check.getBytes());
//        long t1=System.currentTimeMillis();
        solution();
//        long t2=System.currentTimeMillis();
//        out.println(t2-t1);
        out.flush();
        out.close();
    }
    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) {
            throw new InputMismatchException();
        }
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) {
            return -1;
        }
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    String stri() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }

    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long loni() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    float fl() {
        return Float.parseFloat(stri());
    }

    double dou() {
        return Double.parseDouble(stri());
    }

    char chi() {
        return (char) skip();
    }

    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inti();
        }
        return a;
    }

    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = loni();
        }
        return a;
    }

    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = stri();
        }
        return a;
    }

    private static void pa(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
//    uwi mod pow function

    public static long pow(long a, long n, long mod) {
//		a %= mod;
        long ret = 1;
        int x = 63 - Long.numberOfLeadingZeros(n);
        for (; x >= 0; x--) {
            ret = ret * ret % mod;
            if (n << 63 - x < 0) {
                ret = ret * a % mod;
            }
        }
        return ret;
    }

    int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    long lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}

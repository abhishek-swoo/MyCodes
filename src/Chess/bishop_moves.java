

package Chess;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class bishop_moves {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution
    HashSet<String> hh;

    void solution() {
        char s[] = stri().toCharArray();
        char color = chi();
        char board[][] = new char[8][8];
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
            } else if (s[i] == 'B' && color == 'w') {
                board[x][y++] = s[i];
            } else if (s[i] == 'b' && color == 'b') {
                board[x][y++] = s[i];
            } else if (color == 'w' && s[i] >= 'a' && s[i] <= 'z') {
                board[x][y++] = s[i];
            } else if (color == 'b' && s[i] >= 'A' && s[i] <= 'Z') {
                board[x][y++] = s[i];
            } else {
                board[x][y++] = '*';
            }
            if (y >= 8) {
                x++;
                y = 0;
            }
        }
        System.out.println(Arrays.deepToString(board));
        char horizontal[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        int vertical[] = {8, 7, 6, 5, 4, 3, 2, 1};
        char player;
        if (color == 'w') {
            player = 'B';
        } else {
            player = 'b';
        }
        hh = new HashSet<>();
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) {
                    count++;
                }
            }
        }
        int arr[][] = bishopMoves(board, player);
        out.print("[");
        boolean flag = false;
        for (int l = 0; l < count; l++) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int a = arr[0][l], b = arr[1][l];
                    if (hh.contains(a + "" + b + " " + i + " " + j)) {
                        if (flag) {
                            out.print(", ");
                        }
                        flag = true;
                        out.print(horizontal[b] + "" + vertical[a] + "" + horizontal[j] + "" + vertical[i]);
                    }
                }
            }
        }
        out.print("]");

    }

    boolean valid(int x, int y) {
        return x <= 7 && x >= 0 && y <= 7 && y >= 0;
    }

    int[][] bishopMoves(char board[][], char player) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) {
                    count++;
                }
            }
        }
        int arr[][] = new int[2][count];
        int in = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == player) {
                    arr[0][in] = i;
                    arr[1][in++] = j;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            int a = arr[0][i], b = arr[1][i];
            int tema = a, temb = b;
            while (true) {
                tema++;
                temb--;
                if (valid(tema, temb)) {
                    if (board[tema][temb] == '*') {
                        break;
                    } else {
                        hh.add(a + "" + b + " " + tema + " " + temb);
                        if(board[tema][temb] != '-')
                            break;
                    }
                } else {
                    break;
                }
            }
            tema = a;
            temb = b;
            while (true) {
                tema++;
                temb++;
                if (valid(tema, temb)) {
                    if (board[tema][temb] == '*') {
                        break;
                    } else {
                        hh.add(a + "" + b + " " + tema + " " + temb);
                        if(board[tema][temb] != '-')
                            break;
                    }
                } else {
                    break;
                }
            }
            tema = a;
            temb = b;
            while (true) {
                tema--;
                temb++;
                if (valid(tema, temb)) {
                    if (board[tema][temb] == '*') {
                        break;
                    } else {
                        hh.add(a + "" + b + " " + tema + " " + temb);
                        if(board[tema][temb] != '-')
                            break;
                    }
                } else {
                    break;
                }
            }
            tema = a;
            temb = b;
            while (true) {
                tema--;
                temb--;
                if (valid(tema, temb)) {
                    if (board[tema][temb] == '*') {
                        break;
                    } else {
                        hh.add(a + "" + b + " " + tema + " " + temb);
                        if(board[tema][temb] != '-')
                            break;
                    }
                } else {
                    break;
                }
            }

        }
        return arr;
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new bishop_moves().ace();
    }

    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
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

    char chi() {
        return (char) skip();
    }
}

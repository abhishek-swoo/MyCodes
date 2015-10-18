/**
 *
 * @author ABHISHEK SHANKHADHAR
 * code of dfs is taken from www.geeksforgeeks.org/find-number-of-islands/
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.*;
class connectedcomp 
{
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new connectedcomp().ace();
    }
    //Solution !!
    int ROW,COL,counter=0,u=0;
    int[] element=new int[100];
    void solution()
    {
        for(int tn=inti();tn>0;tn--)
        {
            counter=0;
            ROW=inti();
            COL=inti();
            int M[][]={  {1, 1, 0, 0, 0},
                         {0, 1, 0, 0, 1},
                         {1, 0, 0, 1, 1},
                         {0, 0, 0, 0, 0},
                         {1, 0, 1, 0, 1}
                       };
            out.println(countIslands(M));
            out.println();
            for(int i=0;i<6;i++)
                out.print(element[i]+" ");out.println();
        }
    }
    int countIslands(int M[][])
    {
    // Make a bool array to mark visited cells.
    // Initially all cells are unvisited
        boolean visited[][]=new boolean[ROW][COL];
//        memset(visited, 0, sizeof(visited));
 
    // Initialize count as 0 and travese through the all cells of
    // given matrix
    int count = 0;
    for (int i = 0; i < ROW; ++i)
        for (int j = 0; j < COL; ++j)
            if (M[i][j]==1 && !visited[i][j]) // If a cell with value 1 is not
            {                              // visited yet, then new island found
                
                
                counter=0;
                DFS(M, i, j, visited);  // Visit all cells in this island.
                element[u++]=counter;
                ++count;                   // and increment island count
            }
 
    return count;
    }
    void DFS(int M[][], int row, int col, boolean visited[][])
    {
        int x,y;
        Stack<Integer> stackx=new Stack<>();
        Stack<Integer> stacky=new Stack<>();
    // These arrays are used to get row and column numbers of 8 neighbors 
    // of a given cell
        int rowNbr[] = {-1, -1, -1,  0, 0,  1, 1, 1};
        int colNbr[] = {-1,  0,  1, -1, 1, -1, 0, 1};
        stackx.push(row);
        stacky.push(col);
        while(!stackx.isEmpty() && !stacky.isEmpty())
        {
           x=stackx.pop();
           y=stacky.pop();
    // Mark this cell as visited
           if(!visited[x][y])
           {
                visited[x][y] = true;
                counter++;
           
 
    // Recur for all connected neighbours
        for (int k = 0; k < 8; ++k)
        if (isSafe(M, x + rowNbr[k], y + colNbr[k], visited) )
            //DFS(M, row + rowNbr[k], col + colNbr[k], visited);
        {
            stackx.push(x+rowNbr[k]);
            stacky.push(y+ colNbr[k]);
        }
        }
        }
    }
    boolean isSafe(int M[][], int row, int col, boolean visited[][])
    {
        return (row >= 0) && (row < ROW) &&     // row number is in range
           (col >= 0) && (col < COL) &&     // column number is in range
           (M[row][col]==1 && !visited[row][col]); // value is 1 and not yet visited
    }
    //------->ends !!
    void ace()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.flush();
        out.close();
    }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1)
            throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer)
        {
            ptrbuffer=0;
            try {
                lenbuffer=obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if(lenbuffer<=0)
            return -1;
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c)
    {
        return (!(c>=33 && c<=126));
    }
    
    String stri()
    {
        int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b=readByte();
        }
        return sb.toString();
    }
    int skip()
    {
        int b;
        while((b=readByte())!=-1 && isSpaceChar(b));
        return b;
    }
    int inti()
    {
        int num = 0, b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    long loni()
    {
        long num = 0;
        int b;
        boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-')
        {
            minus = true;
            b = readByte();
        }
        while(true)
        {
            if(b >= '0' && b <= '9')
            {
                num = num * 10 + (b - '0');
            }
            else
            {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    float fl()
    {
        return Float.parseFloat(stri());
    }
    double dou()
    {
        return Double.parseDouble(stri());
    }
    char chi()
    {
        return (char)skip();
    }
    int[] arri(int n)
    {
        int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=inti();
        return a;
    }
    long[] arrl(int n)
    {
        long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=loni();
        return a;
    }
    String[] stra(int n)
    {
        String a[]=new String[n];
        for(int i=0;i<n;i++)
            a[i]=stri();
        return a;
    }
}

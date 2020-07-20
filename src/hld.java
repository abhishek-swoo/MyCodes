
/**
 *
 * @author Abhishek
 */
import java.io.*;
import java.util.*;

class hld {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    int root = 0;
    int N = 10100;
    int LN = 14;
    int cost[];
    ArrayList<Integer> adj[], indexx[];
    int baseArray[], ptr;
    int chainNo, chainInd[], chainHead[], posInBase[];
    int depth[], pa[][], otherEnd[], subsize[];
    int st[], qt[];

    void init(){
        adj = new ArrayList[N];
        indexx = new ArrayList[N];
        baseArray = new int[N];
        chainInd = new int[N];
        chainHead = new int[N];
        posInBase = new int[N];
        depth = new int[N];
        otherEnd = new int[N];
        subsize = new int[N];
        st = new int[6 * N];
        qt = new int[6 * N];
        pa = new int[LN][N];
    }
    /*
     * make_tree:
     * Used to construct the segment tree. It uses the baseArray for construction
     */
    void make_tree(int cur, int s, int e) {
        if (s == e - 1) {
            st[cur] = baseArray[s];
            return;
        }
        int c1 = (cur << 1), c2 = c1 | 1, m = (s + e) >> 1;
        make_tree(c1, s, m);
        make_tree(c2, m, e);
        st[cur] = ops(st[c1], st[c2]);
    }

    /*
     * update_tree:
     * Point update. Update a single element of the segment tree.
     */
    void update_tree(int cur, int s, int e, int x, int val) {
        if (s > x || e <= x) {
            return;
        }
        if (s == x && s == e - 1) {
            st[cur] = val;
            return;
        }
        int c1 = (cur << 1), c2 = c1 | 1, m = (s + e) >> 1;
        update_tree(c1, s, m, x, val);
        update_tree(c2, m, e, x, val);
        st[cur] = ops(st[c1], st[c2]);
    }

    /*
     * query_tree:
     * Given S and E, it will return the maximum value in the range [S,E)
     */
    void query_tree(int cur, int s, int e, int S, int E) {
        if (s >= E || e <= S) {
            qt[cur] = -1;
            return;
        }
        if (s >= S && e <= E) {
            qt[cur] = st[cur];
            return;
        }
        int c1 = (cur << 1), c2 = c1 | 1, m = (s + e) >> 1;
        query_tree(c1, s, m, S, E);
        query_tree(c2, m, e, S, E);
        qt[cur] = ops(qt[c1], qt[c2]);
    }
    
    
    /*
     * query_up:
     * It takes two nodes u and v, condition is that v is an ancestor of u
     * We query the chain in which u is present till chain head, then move to next chain up
     * We do that way till u and v are in the same chain, we query for that part of chain and break
     */
    int query_up(int u, int v) {
        if (u == v) {
            return 0; // Trivial
        }
        int uchain, vchain = chainInd[v], ans = -1;
        // uchain and vchain are chain numbers of u and v
        while (true) {
            uchain = chainInd[u];
            if (uchain == vchain) {
                // Both u and v are in the same chain, so we need to query from u to v, update answer and break.
                // We break because we came from u up till v, we are done
                if (u == v) {
                    break;
                }
                query_tree(1, 0, ptr, posInBase[v] + 1, posInBase[u] + 1);
                // Above is call to segment tree query function
                if (qt[1] > ans) {
                    ans = qt[1]; // Update answer
                }
                break;
            }
            query_tree(1, 0, ptr, posInBase[chainHead[uchain]], posInBase[u] + 1);
            // Above is call to segment tree query function. We do from chainHead of u till u. That is the whole chain from
            // start till head. We then update the answer
            if (qt[1] > ans) {
                ans = qt[1];
            }
            u = chainHead[uchain]; // move u to u's chainHead
            u = pa[0][u]; //Then move to its parent, that means we changed chains
        }
        return ans;
    }

    /*
     * LCA:
     * Takes two nodes u, v and returns Lowest Common Ancestor of u, v
     */
    int LCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = v;
            v = u;
            u = tmp;
        }
        int diff = depth[u] - depth[v];
        for (int i = 0; i < LN; i++) {
            if (((diff >> i) & 1) == 1) {
                u = pa[i][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (int i = LN - 1; i >= 0; i--) {
            if (pa[i][u] != pa[i][v]) {
                u = pa[i][u];
                v = pa[i][v];
            }
        }
        return pa[0][u];
    }

    void query(int u, int v) {
        /*
         * We have a query from u to v, we break it into two queries, u to LCA(u,v) and LCA(u,v) to v
         */
        int lca = LCA(u, v);
        int ans = query_up(u, lca); // One part of path
        int temp = query_up(v, lca); // another part of path
        if (temp > ans) {
            ans = temp; // take the maximum of both paths
        }
        System.out.println(ans);
    }

    /*
     * change:
     * We just need to find its position in segment tree and update it
     */
    void change(int i, int val) {
        int u = otherEnd[i];
        update_tree(1, 0, ptr, posInBase[u], val);
    }

    /*
     * Actual HL-Decomposition part
     * Initially all entries of chainHead[] are set to -1.
     * So when ever a new chain is started, chain head is correctly assigned.
     * As we add a new node to chain, we will note its position in the baseArray.
     * In the first for loop we find the child node which has maximum sub-tree size.
     * The following if condition is failed for leaf nodes.
     * When the if condition passes, we expand the chain to special child.
     * In the second for loop we recursively call the function on all normal nodes.
     * chainNo++ ensures that we are creating a new chain for each normal child.
     */
    void HLD(int curNode, int cost, int prev) {
        if (chainHead[chainNo] == -1) {
            chainHead[chainNo] = curNode; // Assign chain head
        }
        
        chainInd[curNode] = chainNo;
        posInBase[curNode] = ptr; // Position of this node in baseArray which we will use in Segtree
        baseArray[ptr++] = cost;

        int sc = -1, ncost = 0;
        // Loop to find special child
        for (int i = 0; i < adj[curNode].size(); i++) {
            if (adj[curNode].get(i) != prev) {
                if (sc == -1 || subsize[sc] < subsize[adj[curNode].get(i)]) {
                    sc = adj[curNode].get(i);
                    ncost = this.cost[adj[curNode].get(i)];
                }
            }
        }

        if (sc != -1) {
            // Expand the chain
            HLD(sc, ncost, curNode);
        }

        for (int i = 0; i < adj[curNode].size(); i++) {
            if (adj[curNode].get(i) != prev) {
                if (sc != adj[curNode].get(i)) {
                    // New chains at each normal node
                    chainNo++;
                    HLD(adj[curNode].get(i), this.cost[adj[curNode].get(i)], curNode);
                }
            }
        }
    }

    /*
     * dfs used to set parent of a node, depth of a node, subtree size of a node
     */
    void dfs(int cur, int prev, int _depth) {

        pa[0][cur] = prev;
        depth[cur] = _depth;
        subsize[cur] = 1;
        for (int i = 0; i < adj[cur].size(); i++) {
            if (adj[cur].get(i) != prev) {
                otherEnd[indexx[cur].get(i)] = adj[cur].get(i);
                dfs(adj[cur].get(i), cur, _depth + 1);
                subsize[cur] += subsize[adj[cur].get(i)];
            }
        }
    }
    
    int ops(int a,int b){
        return Math.max(a, b);
    }
    
    void solution() {
        int t = inti();
        init();
        while (t-- > 0) {
            ptr = 0;
            int n = inti();
            // Cleaning step, new test case
            for (int i = 0; i < n; i++) {
                adj[i] = new ArrayList<>();
                indexx[i] = new ArrayList<>();

                chainHead[i] = -1;
                for (int j = 0; j < LN; j++) {
                    pa[j][i] = -1;
                }
            }
            for (int i = 1; i < n; i++) {
                int u = inti(), v = inti();
                adj[u].add(v);
                indexx[u].add(i - 1);
                adj[v].add(u);
                indexx[v].add(i - 1);
            }
            cost=arri(n);

            chainNo = 0;
            dfs(root, -1, 0); // We set up subsize, depth and parent for each node
            HLD(root, -1, -1); // We decomposed the tree and created baseArray
            make_tree(1, 0, ptr); // We use baseArray and construct the needed segment tree

            // Below Dynamic programming code is for LCA.
            for (int i = 1; i < LN; i++) {
                for (int j = 0; j < n; j++) {
                    if (pa[i - 1][j] != -1) {
                        pa[i][j] = pa[i - 1][pa[i - 1][j]];
                    }
                }
            }

            while (true) {
                char s[] = stri().toCharArray();
                if (s[0] == 'D') {
                    break;
                }
                int a = inti(), b = inti();
                if (s[0] == 'Q') {
                    query(a , b);
                } else {
                    change(a , b);
//                    cost[a]=b;
                }
            }
        }

    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new hld().ace();

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

    int[][] ar2D(int n, int m) {
        int ark[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ark[i][j] = inti();
            }
        }
        return ark;
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

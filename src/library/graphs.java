package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class graphs {

    //    this code makes graph in 2 D array(undirected) where n is number of vertices & 0 based indexing
    static int[][] packU(int n, int[] from, int[] to) {
//    this part of code is taken from "uwi" submission of codechef problem KNODES    
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int t : to) {
            p[t]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    //////////// for weighted graphs
    public static int[][][] packWU(int n, int[] from, int[] to, int[] w) {
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int t : to) {
            p[t]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]][2];
        }
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
            --p[to[i]];
            g[to[i]][p[to[i]]][0] = from[i];
            g[to[i]][p[to[i]]][1] = w[i];
        }
        return g;
    }
    
    //`````````````````````````````````````````````````````````````````````````````
    // for directed graph, max is length of from or to array
    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) p[from[i]]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    //````````````````````````````````````````````````````````````````````````````
    // for directed weighted graph
    public static int[][][] packWD(int n, int[] from, int[] to, int[] w) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][][] g = new int[n][][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]][2];
        for (int i = 0; i < from.length; i++) {
            --p[from[i]];
            g[from[i]][p[from[i]]][0] = to[i];
            g[from[i]][p[from[i]]][1] = w[i];
        }
        return g;
    }
    
//    BFS code for adjacency list
    int[] bfs(int[][] graph,int source){
        int len=graph.length;
        int shortest_path[]=new int[len];
        int parent[]=new int[len];
        Arrays.fill(shortest_path, Integer.MAX_VALUE/2);
        shortest_path[source]=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {            
            int u=q.poll();
            for(int v:graph[u]){
                if(shortest_path[v]==Integer.MAX_VALUE/2){
                    q.add(v);
                    shortest_path[v]=shortest_path[u]+1;
                    parent[v]=u;
                }
            }
        }
        return shortest_path;
    }
    
    //    BFS code for grid
//    
    char[][] grid;
    int xa[] = {1, -1, 0, 0};
    int yb[] = {0, 0, 1, -1};
    int[][] bfsGrid(int sourceX, int sourceY, int n, int m) {
        int distance[][] = new int[n][m];
        Pair parent[][] = new Pair[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                distance[i][j] = Integer.MAX_VALUE / 2;
            }
        }
        distance[sourceX][sourceY] = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(sourceX, sourceY));

        while (!queue.isEmpty()) {
            Pair v = queue.poll();
            for (int i = 0; i < 4; i++) {
                if (valid(v.x + xa[i], v.y + yb[i], n, m) && distance[v.x + xa[i]][v.y + yb[i]] == Integer.MAX_VALUE / 2 && grid[v.x + xa[i]][v.y + yb[i]] == '.') {
                    distance[v.x + xa[i]][v.y + yb[i]] = distance[v.x][v.y] + 1;
                    parent[v.x + xa[i]][v.y + yb[i]] = new Pair(v.x, v.y);
                    queue.add(new Pair(v.x + xa[i], v.y + yb[i]));
                }
            }
        }

        return distance;
    }

    boolean valid(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    class Pair {
        int x, y;
        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }
//////////////////

///  dijkstra algo
    public static int[] dijk(int[][][] g, int from) {
//        this part of code is taken from uwi submissions
        int n = g.length;
        final int[] td = new int[n];
        Arrays.fill(td, Integer.MAX_VALUE);
        TreeSet<Integer> q;
        q = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                if (td[a] - td[b] != 0) {
                    return td[a] - td[b];
                }
                return a - b;
            }
        });
        q.add(from);
        td[from] = 0;

        while (q.size() > 0) {
            int cur = q.pollFirst();

            for (int[] item : g[cur]) {
                int next = item[0];
                int nd = td[cur] + item[1];
                if (nd < td[next]) {
                    q.remove(next);
                    td[next] = nd;
                    q.add(next);
                }
            }
        }

        return td;
    }

//    dfs
    boolean visit[];
    int size = 0;

    void dfs(int graph[][], int s) {
        Stack<Integer> st = new Stack<>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                size++;
                for (int i = 0; i < graph[v].length; i++) {
                    int w = graph[v][i];
                    st.push(w);
                }
            }
        }
    }

//    return a array of length n elements containing numbers like 1 1 2 2 1 3... , this means 1 1 1 is in a cycle and 2 2 in other 
//    and both are connected by a bridge with other cycle, this applies for all numbers
    public static int[] decomposeToBECC(int[][] g) {
//        this part of code is taken from uwi submissions
        int n = g.length;
        boolean[] visited = new boolean[n];
        int[] ord = new int[n];
        int[] low = new int[n];

        int[] ids = new int[n];
        int[] inds = new int[n];
        int[] parct = new int[n];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ids[0] = i;
                inds[0] = 0;
                int sp = 1;
                while (sp > 0) {
                    int cur = ids[sp - 1];
                    if (inds[sp - 1] == 0) {
                        visited[cur] = true;
                        ord[cur] = low[cur] = pos++;
                        parct[sp - 1] = 0;
                    }
                    if (inds[sp - 1] == g[cur].length) {
                        if (sp - 2 >= 0) {
                            low[ids[sp - 2]] = Math.min(low[ids[sp - 2]], low[cur]);
                        }
                        sp--;
                        continue;
                    }
                    int next = g[cur][inds[sp - 1]];
                    if (!visited[next]) {
                        ids[sp] = next;
                        inds[sp] = 0;
                        inds[sp - 1]++;
                        sp++;
                        continue;
                    } else if (sp - 2 >= 0 && (next != ids[sp - 2] || ++parct[sp - 1] >= 2)) {
                        low[cur] = Math.min(low[cur], ord[next]);
                    }
                    inds[sp - 1]++;
                }
            }
        }

        int[] clus = new int[n];
        Arrays.fill(clus, -1);
        int[] q = new int[n];
        int cnum = 0;
        for (int i = 0; i < n; i++) {
            if (clus[i] == -1) {
                int p = 0;
                q[p++] = i;
                clus[i] = cnum++;
                for (int r = 0; r < p; r++) {
                    int cur = q[r];
                    for (int next : g[cur]) {
                        if (clus[next] == -1) {
                            clus[next] = ord[cur] < low[next] ? cnum++ : clus[cur];
                            q[p++] = next;
                        }
                    }
                }
            }
        }
        return clus;
    }

//    disjoint set union-find
//    DJSet ds = new DJSet(n);
    public static class DJSet {
//        this part of code is taken from uwi submissions

        public int[] upper;

        public DJSet(int n) {
            upper = new int[n];
            Arrays.fill(upper, -1);
        }

        public int root(int x) {
            return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
        }

        public boolean equiv(int x, int y) {
            return root(x) == root(y);
        }

        public boolean union(int x, int y) {
            x = root(x);
            y = root(y);
            if (x != y) {
                if (upper[y] < upper[x]) {
                    int d = x;
                    x = y;
                    y = d;
                }
                upper[x] += upper[y];
                upper[y] = x;
            }
            return x == y;
        }

        public int count() {
            int ct = 0;
            for (int u : upper) {
                if (u < 0) {
                    ct++;
                }
            }
            return ct;
        }
    }

//   Function to find the bridges in the graph
//    num is filled with -1
//      for(int i=0;i<n;i++){
//            if(num[i]==-1)
//                bridgeFinding(g, i);
//        }
    int count = 0;
    int num[];
    int low[];
    int parent[];
    int rootChild = 0;
    int root = -1;

    void bridgeFinding(int g[][], int u) {
        num[u] = count++;
        low[u] = num[u];
        for (int v : g[u]) {
            if (num[v] == -1) {
                parent[v] = u;
                if (u == root) {
                    rootChild++;
                }
                bridgeFinding(g, v);
                if (low[v] > num[u]) {
                    System.out.println(u + " " + v);
                }
                low[u] = Math.min(low[u], low[v]);
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], num[v]);
            }
        }
    }
    
    
//   function to check weather a graph is bipartite or not
    boolean isBipartite(int graph[][],int s){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        boolean bipartite=true;
        int color[]=new int[graph.length];
        for(int i=0;i<color.length;i++){
            color[i]=Integer.MAX_VALUE/2;
        }
        color[s]=0;
        while(!queue.isEmpty() && bipartite){
            int u=queue.poll();
            for(int v:graph[u]){
                if (color[v]==Integer.MAX_VALUE/2) {
                    color[v]=1-color[u];
                    queue.add(v);
                }
                else if (color[v]==color[u]) {
                    bipartite=false;
                    break;
                }
            }
        }
        return bipartite;
    }


}

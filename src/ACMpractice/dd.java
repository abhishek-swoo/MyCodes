package ACMpractice;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 * 
 */
import java.io.*;
import java.util.*;

public class dd {

    
    static class Edge {
        Vertex v;
        int w;
        Edge(Vertex v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
        String[] tem=obj.readLine().split(" ");
        int N = Integer.parseInt(tem[0]);
        int M = Integer.parseInt(tem[1]);

        Vertex[] vv = new Vertex[N + 1]; 
        for (int i = 1; i <= N; i++) {
            vv[i] = new Vertex(i);
        }
        for (int m = 0; m < M; m++) {
            tem=obj.readLine().split(" ");
            int a = Integer.parseInt(tem[0]);
            int b = Integer.parseInt(tem[1]);
            int w = Integer.parseInt(tem[2]);

            vv[a].edgeList.add(new Edge(vv[b], w));
            vv[b].edgeList.add(new Edge(vv[a], w));
        }

        SortedSet<Vertex> pq = new TreeSet<>();
        //Dijkstra's Algorithm
        vv[1].distance = 0; 
        pq.add(vv[1]);
        while (!pq.isEmpty()) {
            Vertex curr = pq.first();
            pq.remove(curr);
            if (curr == vv[N]) {
                LinkedList<Integer> parents = new LinkedList<>();
                StringBuilder sb = new StringBuilder();
                while (curr != null) {
                    parents.addFirst(curr.id);
                    curr = curr.parent;
                }
                parents.stream().forEach((Integer id) -> {
                    StringBuilder append;
                    append = sb.append(id).append(" ");
                });
                System.out.println(sb.toString().trim());
                return;
            }
            for (Edge e : curr.edgeList) {
                if (curr.distance + e.w < e.v.distance) {
                    pq.remove(e.v);
                    e.v.parent = curr;
                    e.v.distance = curr.distance + e.w;
                    pq.add(e.v);
                }
            }
        }
        System.out.println(-1);

    }
    
    
    static class Vertex implements Comparable<Vertex> {

        int id;
        Vertex parent;
        long distance = Long.MAX_VALUE; //inital distance is infinity
        List<Edge> edgeList = new LinkedList<>();

        Vertex(int i) {
            this.id = i;
        }

        @Override
        public int compareTo(Vertex v) {
            int g = 1;
            int l = -1;

            if (this.distance > v.distance) {
                return g;
            }
            if (this.distance < v.distance) {
                return l;
            }
            return this.id - v.id;	
        }
    }

}

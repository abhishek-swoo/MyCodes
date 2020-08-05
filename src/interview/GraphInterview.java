package interview;

import java.util.*;

//    Find the max sum between two nodes of binary tree
class Result {
    public int val;
}

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = right = null;
    }
}

class MaxSumInBinaryTree {
    Node root;

    int findMaxSum(Node node) {
        Result res = new Result();
        res.val = Integer.MIN_VALUE;

        findMaximum(root, res);
        return res.val;
    }

    int findMaximum(Node node, Result res) {

        if (node == null) return 0;

        int l = findMaximum(node.left, res);
        int r = findMaximum(node.right, res);

        int maxOneSide = Math.max(Math.max(l, r) + node.data, node.data);

        int maxIncludeNode = Math.max(l + r + node.data, maxOneSide);

        res.val = Math.max(res.val, maxIncludeNode);

        return maxOneSide;
    }
}
/////////////////////// end of max b/w two nodes


public class GraphInterview {

    //    Print nodes in top view of binary tree

    static class pair {
        int data, height;

        public pair(int data, int height) {
            this.data = data;
            this.height = height;
        }
    }

    static void printTopViewUtil(Node root, int height,
                                 int horizontalDistance, Map<Integer, pair> map) {
        if (root == null)
            return;

        if (!map.containsKey(horizontalDistance)) {
            map.put(horizontalDistance, new pair(root.data, height));
        } else {
            pair pair = map.get(horizontalDistance);

            if (pair.height > height) {
                map.remove(horizontalDistance);
                map.put(horizontalDistance, new pair(root.data, height));
            }
        }

        printTopViewUtil(root.left, height + 1, horizontalDistance - 1, map);
        printTopViewUtil(root.right, height + 1, horizontalDistance + 1, map);
    }

//    End of printing tree in top view

//    traverse tree in O(1) space

    class tNode {
        int data;
        tNode left, right;

        tNode(int item) {
            data = item;
            left = right = null;
        }
    }

    class BinaryTree {
        tNode root;

        void MorrisTraversal(tNode root) {
            tNode current, pre;

            if (root == null)
                return;

            current = root;
            while (current != null) {
                if (current.left == null) {
                    System.out.print(current.data + " ");
                    current = current.right;
                } else {
                    pre = current.left;
                    while (pre.right != null && pre.right != current)
                        pre = pre.right;

                    if (pre.right == null) {
                        pre.right = current;
                        current = current.left;
                    } else {
                        pre.right = null;
                        System.out.print(current.data + " ");
                        current = current.right;
                    }

                }

            }
        }
    }

//    end of tree traversal

    //    Minimum Heights trees: find all the MHTs and return a list of their root labels.

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {

        ArrayList<Integer>[] tree = new ArrayList[n];
        int degree[] = new int[n];

        for(int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            tree[edges[i][0]].add(edges[i][1]);
            tree[edges[i][1]].add(edges[i][0]);
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0 ;i < n; i++) {
            if(tree[i].size() == 1) {
                q.add(i);
            }
        }

        while(n > 2) {
            System.out.println(q);

            int size = q.size();
            for(int i = 0;i < size; i++) {
                int v = q.poll();
                n--;

                for(int child: tree[v]) {
                    degree[child]--;
                    if(degree[child] == 1) {
                        q.add(child);
                    }
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int v = q.poll();
            ans.add(v);
        }
        if(edges.length == 0) {
            ans.add(0);
        }

        return ans;
    }

//    end of Minimum Height trees

    //    Return a deep copy of graph

    class Node1 {
        public int val;
        public List<Node1> neighbors;
        public Node1() {
            val = 0;
            neighbors = new ArrayList<Node1>();
        }
        public Node1(int _val) {
            val = _val;
            neighbors = new ArrayList<Node1>();
        }
    }

    HashMap<Integer, Node1> visited = new HashMap<>();

    public Node1 cloneGraph(Node1 node) {

        if(node == null) return null;

        Node1 n = new Node1(node.val);
        visited.put(node.val, n);

        int size = node.neighbors.size();

        for(int i = 0; i < size ; i++) {

            Node1 neighbor = node.neighbors.get(i);
            if(visited.containsKey(neighbor.val)) {
                n.neighbors.add(visited.get(neighbor.val));
            } else {
                Node1 newNode = cloneGraph(neighbor);
                n.neighbors.add(newNode);
            }
        }
        return n;
    }

//    end of Clone graph

//    Reconstruct itinerary: Based on Eularian path

    HashMap<String, PriorityQueue<String>> graph;
    public List<String> findItinerary(List<List<String>> tickets) {

        graph = new HashMap<>();
        ans = new LinkedList<>();

        for(List<String> ticket: tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            if(!graph.containsKey(from)) graph.put(from, new PriorityQueue<>());

            graph.get(from).add(to);
        }

        dfs("JFK");

        return ans;

    }

    LinkedList<String> ans;
    void dfs(String from) {
        PriorityQueue<String> q = graph.get(from);

        while(q != null && !q.isEmpty()) {
            dfs(q.poll());
        }

        ans.addFirst(from);
    }

//    End of reconstruct itinerary

//    Course Schedule 2: Given the total number of courses and a list of prerequisite pairs,
//    return the ordering of courses you should take to finish all courses.

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];

        for(int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0;i < prerequisites.length;i++) {
            int a = prerequisites[i][0];
            int b = prerequisites[i][1];
            graph[b].add(a);
            indegree[a]++;
        }

        Queue<Integer> zeroQueue = new LinkedList<>();

        for(int i = 0; i < numCourses; i++) {
            if(indegree[i]==0){
                zeroQueue.add(i);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!zeroQueue.isEmpty()) {
            int node = zeroQueue.poll();

            for(int child: graph[node]) {
                indegree[child]--;
                if(indegree[child] == 0) {
                    zeroQueue.add(child);
                }
            }
            graph[node] = new ArrayList<>();
            ans.add(node);
        }
        if(ans.size() != numCourses) {
            return new int[0];
        }
        int res[] = new int[ans.size()];
        for(int i = 0 ;i < ans.size(); i++){
            res[i] = ans.get(i);
        }
        return res;
    }

//    end of Course Schedule2

    //    Course Schedule: Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

    public boolean canFinish(int numCourses, int[][] pre) {

        int[] indegree = new int[numCourses];

        for(int i = 0; i < pre.length; i++) {
            indegree[pre[i][0]]++;
        }

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                st.push(i);
            }
        }
        int count = 0;

        while(!st.isEmpty()) {
            int e = st.pop();
            count++;

            for(int i = 0; i < pre.length; i++) {
                if(pre[i][1] == e) {
                    indegree[pre[i][0]]--;

                    if(indegree[pre[i][0]] == 0) {
                        st.push(pre[i][0]);
                    }
                }
            }
        }

        return count == numCourses;
    }

//    End of Course Schedule

}

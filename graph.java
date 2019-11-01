/**
 * graph
 */
import java.util.*;

public class graph {

    // List Graph 생성 Class
    public static class Graph {
        private ArrayList<ArrayList<Integer>> graph;

        public Graph(int n) {
            this.graph = new ArrayList<>();
            for(int i=0;i<n+1;i++) this.graph.add(new ArrayList<>()); 
        }

        public void put(int x, int y) {
            this.graph.get(x).add(y);
            this.graph.get(y).add(x);
        }

        public void putSingle(int x, int y) {
            this.graph.get(x).add(y);
        }

        public ArrayList<Integer> getNode(int x) {
            return this.graph.get(x);
        }

        public ArrayList<ArrayList<Integer>> getGraph() {
            return this.graph;
        }
    }

    // Kruskal 알고리즘 간선 Class
    public static class Edge implements Comparable<Edge> {
        private int s;
        private int e;
        private int v;

        public Edge(int s, int e, int v) {
            this.s = s;
            this.e = e;
            this.v = v;
        }

        public int getStart() {
            return this.s;
        }

        public int getEnd() {
            return this.e;
        }

        public int getValue() {
            return this.v;
        }

        public int compareTo(Edge e) {
            return this.v - e.getValue();
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph(7);

        graph.put(1,2);
        graph.put(1,3);
        graph.put(2,3);
        graph.put(2,4);
        graph.put(2,5);
        graph.put(4,5);
        graph.put(3,6);
        graph.put(3,7);
        graph.put(6,7);

        // bfs test
        bfs(graph,1);

        System.out.println("\n--------------------------------------------------------");

        // dfs test
        dfs(graph, new boolean[graph.getGraph().size()],1);

        System.out.println("\n--------------------------------------------------------");

        // Union-Find test
        int[] parent = new int[11];
        for(int i=1;i<11;i++) parent[i] = i;

        unionParent(parent, 1, 2);
        unionParent(parent, 2, 3);
        unionParent(parent, 3, 4);
        unionParent(parent, 5, 6);
        unionParent(parent, 6, 7);
        unionParent(parent, 7, 8);
        unionParent(parent, 2, 9);
        unionParent(parent, 9, 10);

        System.out.println((findParent(parent, 1, 4) ? "1 & 4 connected" : "1 & 4 not connected"));
        System.out.println((findParent(parent, 5, 6) ? "5 & 6 connected" : "5 & 6 not connected"));
        System.out.println((findParent(parent, 1, 10) ? "1 & 10 connected" : "1 & 10 not connected"));
        System.out.println((findParent(parent, 3, 8) ? "3 & 8 connected" : "3 & 8 not connected"));
        
        System.out.println("\n--------------------------------------------------------");

        // kruskal test
        Edge[] array = new Edge[11];

        array[0] = new Edge(1,7,12);
        array[1] = new Edge(1,4,28);
        array[2] = new Edge(1,2,67);
        array[3] = new Edge(1,5,17);
        array[4] = new Edge(2,4,24);
        array[5] = new Edge(2,5,62);
        array[6] = new Edge(3,5,20);
        array[7] = new Edge(3,6,37);
        array[8] = new Edge(4,7,13);
        array[9] = new Edge(5,6,45);
        array[10] = new Edge(5,7,73);

        Arrays.sort(array);

        int[] parent_kruskal = new int[8];
        for(int i=1;i<8;i++) parent_kruskal[i] = i;

        int sum = kruskal(array,parent_kruskal);
        System.out.println("Kruskal Sum : " + sum);

    }

    // BFS : 너비우선탐색
    public static void bfs(Graph graph, int start) {
        Queue<Integer> q = new LinkedList<> ();
        boolean[] check = new boolean[graph.getGraph().size()];

        q.offer(start);
        check[start] = true;

        while(!q.isEmpty()) {
            int x = q.poll();
            System.out.print(x + " ");
            ArrayList<Integer> node = graph.getNode(x);

            for(int i=0;i<node.size();i++) {
                int connect = node.get(i);

                if(!check[connect]) {
                    q.offer(connect);
                    check[connect] = true;
                }
            }
        }
    }

    // DFS : 깊이우선탐색
    public static void dfs(Graph graph, boolean[] check, int n) {
        check[n] = true;
        System.out.print(n + " ");

        ArrayList<Integer> node = graph.getNode(n);

        for(int i=0;i<node.size();i++) {
            int connect = node.get(i);
            if(!check[connect]) {
                dfs(graph, check, connect);
            }
        }

    }

    // Union-Find 관련함수 : getParent, unionParent, findParent

    // 부모 노드 찾기
    public static int getParent(int[] parent, int x) {
        if(x == parent[x]) return x;
        else return getParent(parent, parent[x]);
    }

    // 부모 노드 합치기
    public static void unionParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a < b) parent[b] = a;
        else parent[a] = b;
    }

    // 부모 노드가 같은지(연결되어 있는지) 확인
    public static boolean findParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);

        if(a == b) return true;
        else return false;
    }

    // Kruskal 알고리즘
    public static int kruskal(Edge[] array, int[] parent) {
        int sum = 0;
        for(int i=0;i<array.length;i++) {
            int s = array[i].getStart();
            int e = array[i].getEnd();

            if(!findParent(parent, s, e)) {
                unionParent(parent, s, e);
                System.out.print(s + " - > " + e + ", ");
                sum += array[i].getValue();
            }
        }

        System.out.println();
        return sum;
    }
}
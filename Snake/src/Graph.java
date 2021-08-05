import java.util.ArrayList;
import java.util.Queue;

public class Graph {
    private final int V;
    private int E;
    private ArrayList<Edge>[] adj;

    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj =  new ArrayList[V]; //(ArrayList<Edge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++){
            adj[v] = new ArrayList<Edge>();
        }
    }

    public int getV(){
        return V;
    }

    public int getE(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public ArrayList<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        ArrayList<Edge> list = new ArrayList<>();
        for (int v = 0; v < V; v++){
            for (Edge e : adj[v]){
                if (e.other(v) > v){
                    list.add(e);
                }
            }
        }
        return list;
    }
}

import java.util.*;

public class MST {
    private Queue<Edge> mst;

    public MST(Graph G, int N, int V){
        mst = new LinkedList<>();
        MinPQ pq = new MinPQ(N);

        for (Edge e : G.edges()){
            pq.insert(e);
        }

        UnionFind uf = new UnionFind(G.getV());

        //while (!pq.isEmpty() && mst.size() < G.getV() - 1){
        while (!uf.isConnected(0, V-1)){
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.isConnected(v, w)){
                continue;
            }
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}

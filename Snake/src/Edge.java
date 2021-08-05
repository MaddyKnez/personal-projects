public class Edge {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if (vertex == v){
            return w;
        }
        return v;
    }

    public double getWeight(){
        return weight;
    }

    public int compareTo(Edge that){
        if (this.getWeight() < that.getWeight()){
            return -1;
        }
        if (this.getWeight() > that.getWeight()){
            return 1;
        }
        return 0;
    }
}

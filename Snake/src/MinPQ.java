import java.security.Key;

public class MinPQ{
    private Edge[] pq;
    private int n = 0;

    public MinPQ(int maxN){
        pq = new Edge[maxN + 1];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void insert(Edge v){
        pq[++n] = v;
        swim(n);
    }

    public Edge delMin(){
        Edge min = pq[1];
        swap(1, n--);
        pq[n+1] = null;
        sink(1);
        return min;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void swap(int i, int j){
        Edge t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k){
        while (k > 1 && less(k/2, k)){
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= n){
            int j = 2*k;
            if (j < n && less(j, j+1))
                j++;
            if (!less(k, j))
                break;
            swap(k, j);
            k = j;
        }
    }
}

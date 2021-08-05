public class UnionFind {
    private int[] id;
    private int[] size;
    private int count;

    public UnionFind(int num){
        count = num;
        id = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++){
            id[i] = i;
            size[i] = 1;
        }
    }

    public int getCount(){
        return count;
    }

    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    public int find(int p){
        while (p != id[p]){
            p = id[p];
        }
        return p;
    }

    public void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j){
            return;
        }

        if (size[i] < size[j]){
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
    }
}

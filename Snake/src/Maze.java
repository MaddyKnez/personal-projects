import java.util.ArrayList;
import java.util.Random;

public class Maze {
    private int width;
    private int height;
    private int difficulty;

    private ArrayList<int[]> nodes;
    private Graph G;
    private MST mst;

    private int[][] maze = new int[width][height];

    public Maze(int width, int height, int difficulty){
        this.width = width;
        this.height = height;
        this.difficulty = difficulty;

        nodes = new ArrayList<>();


        initialBoardState();

        initialNodes();
        randomGraph();
        System.out.println(G.getE());
        mst = new MST(G, G.getE(), G.getV());
        makeMaze(mst);

        display();
    }


    private void makeMaze(MST m){
        for (Edge e : m.edges()){
            int v = e.either();
            int w = e.other(v);

            int[] V = nodes.get(v);
            int[] W = nodes.get(w);

            maze[V[0]][V[1]] = 1;
            maze[W[0]][W[1]] = 1;
        }
    }
    

    private void randomGraph(){
        Random rand = new Random();
        G = new Graph(nodes.size());

        for (int i = 0; i < nodes.size(); i++){
            for (int neighbor : getNeighbors(i)){
                if (neighbor != -1){
                    Edge e = new Edge(i, neighbor, rand.nextDouble());
                    G.addEdge(e);
                }
            }
        }
    }

    private void initialNodes(){
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                nodes.add(new int[] {i, j});
                visited[i][j] = false;
            }
        }
    }

    private int[] getNeighbors(int node){
        int[] neighbors = new int[4];
        for (int i = 0; i < 4; i++){
            neighbors[i] = -1;
        }

        if (node + width < nodes.size()){
            neighbors[0] = node + width;
        }
        if (node - width >= 0){
            neighbors[1] = node - width;
        }
        if (node - 1 >= 0){
            if (nodes.get(node)[0] == nodes.get(node - 1)[0]){
                neighbors[2] = node - 1;
            }
        }
        if (node + 1 < nodes.size()){
            if (nodes.get(node)[0] == nodes.get(node + 1)[0]){
                neighbors[3] = node + 1;
            }
        }
        return neighbors;
    }

    private int[][] getNeighbors(int[] node){
        int[][] neighbors = new int[4][2];

        for (int i = 0; i < 4; i++){
            neighbors[i] = null;
        }

        if(node[0] - 1 >= 0)
            neighbors[0] = new int[] {node[0] - 1, node[1]};
        if (node[0] + 1 < height)
            neighbors[1] = new int[] {node[0] + 1, node[1]};
        if (node[1] - 1 >= 0)
            neighbors[2] = new int[] {node[0], node[1] - 1};
        if (node[1] + 1 < width)
            neighbors[3] = new int[] {node[0], node[1] + 1};
        return neighbors;
    }

    private void initialBoardState(){
        maze = new int[height][width];
        for(int[] row: maze){
            for(int i = 0; i < width; i++){
                row[i] = 0;
            }
        }
    }

    public void display(){
        System.out.print("+");
        for(int i = 0; i < width; i++){
            System.out.print("-");
        }
        System.out.println("+");

        for(int[] row: maze){
            System.out.print("|");
            for(int item: row){
                if(item == 0)
                    System.out.print("#");
                else if (item == 3)
                    System.out.print("P");
                else
                    System.out.print(" ");
            }
            System.out.println("|");
        }

        System.out.print("+");
        for(int i = 0; i < width; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public boolean isWallAbove(int[] vertex){
        return false;
    }

    public boolean isWallBellow(int[] vertex){
        return false;
    }

    public boolean isWallRight(int[] vertex){
        return false;
    }

    public boolean isWallLeft(int[] vertex){
        return false;
    }

    public static void main(String args[]){
        Maze m = new Maze(60, 10, 10);
        //m.display();
    }
}

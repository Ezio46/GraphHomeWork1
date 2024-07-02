import java.util.ArrayList;
import java.util.List;

public class Graph {

    int numVerticesX;
    int numVerticesY;

    List<List<Integer>> adjacencyList;
    int[][] adjacencyMatrix;
    int[][] incidenceMatrix;
    List<int[]> edgeList;
    int[][] adjacencyVectors;
    int[][] adjacencyArrays;

    public Graph(int numVerticesX, int numVerticesY, int numEdges) {
        this.numVerticesX = numVerticesX;
        this.numVerticesY = numVerticesY;
        this.adjacencyList = new ArrayList<>(numVerticesX + numVerticesY);
        for (int i = 0; i < numVerticesX + numVerticesY; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        this.adjacencyMatrix = new int[numVerticesX + numVerticesY][numVerticesX + numVerticesY];
        this.incidenceMatrix = new int[numVerticesX + numVerticesY][numEdges];
        this.edgeList = new ArrayList<>();
        this.adjacencyVectors = new int[numVerticesX + numVerticesY][numVerticesY];
        this.adjacencyArrays = new int[numVerticesX + numVerticesY][];
    }

    public void addEdges(int x, int y, int edgeIndex) {
        adjacencyList.get(x - 1).add(y);
        adjacencyList.get(y - 1).add(x);

        adjacencyMatrix[x - 1][y - 1] = 1;
        adjacencyMatrix[y - 1][x - 1] = 1;

        incidenceMatrix[x - 1][edgeIndex] = 1;
        incidenceMatrix[y - 1][edgeIndex] = 1;

        edgeList.add(new int[]{x, y});

        adjacencyVectors[x - 1][adjacencyList.get(x - 1).size() - 1] = y;
        adjacencyVectors[y - 1][adjacencyList.get(y - 1).size() - 1] = x;
    }

    public void finalizeAdjacencyArrays() {
        for (int i = 0; i < numVerticesX + numVerticesY; i++) {
            adjacencyArrays[i] = new int[adjacencyList.get(i).size()];
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                adjacencyArrays[i][j] = adjacencyList.get(i).get(j);
            }
        }
    }

    public void printStructures() {
        System.out.println("Список смежности:");
        for (int i = 1; i <= numVerticesX + numVerticesY; i++) {
            System.out.print(i + ": ");
            for (int neighbour : adjacencyList.get(i - 1)) {
                System.out.print(neighbour + " ");
            }
            System.out.println();
        }

        System.out.println("\nМатрица смежности:");
        printMatrix(adjacencyMatrix);
        System.out.println("\nМатрица инцидентности:");
        printMatrix(incidenceMatrix);

        System.out.println("\nПеречень рёбер:");
        for (int[] edge : edgeList) {
            System.out.println(edge[0] + " - " + edge[1]);
        }

        System.out.println("\nВекторы смежности:");
        printMatrix(adjacencyVectors);

        System.out.println("\nМассивы смежности:");
        for (int i = 0; i < adjacencyArrays.length; i++) {
            System.out.print((i + 1) + ": ");
            for (int j = 0; j < adjacencyArrays[i].length; j++) {
                System.out.print(adjacencyArrays[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int numVerticesX = 3;
        int numVerticesY = 4;
        int numEdges = numVerticesX * numVerticesY;

        Graph graph = new Graph(numVerticesX, numVerticesY, numEdges);

        int edgeIndex = 0;
        for (int i = 1; i <= numVerticesX; i++) {
            for (int j = numVerticesX + 1; j <= numVerticesX + numVerticesY; j++) {
                graph.addEdges(i, j, edgeIndex);
                edgeIndex++;
            }
        }

        graph.finalizeAdjacencyArrays();
        graph.printStructures();
    }
}
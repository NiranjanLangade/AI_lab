import java.util.*;

public class Assignment2 {
    static class PathInfo {
        int cost;
        List<Integer> path;

        PathInfo() {
            cost = 0;
            path = new ArrayList<>();
        }
    }

    static PathInfo findMinPathDFS(int current, int target, List<List<Pair<Integer, Integer>>> graph, int[] visited, PathInfo[] memo) {
        if (current == target) {
            PathInfo result = new PathInfo();
            result.cost = 0;
            result.path.add(target);
            return result;
        }

        if (visited[current] == 1) {
            if (memo[current] != null) {
                return memo[current];
            }
        }

        visited[current] = 1;
        PathInfo result = new PathInfo();
        result.cost = Integer.MAX_VALUE;

        for (Pair<Integer, Integer> neighbor : graph.get(current)) {
            int nextNode = neighbor.first;
            int edgeCost = neighbor.second;

            if (visited[nextNode] == 0) {
                PathInfo subResult = findMinPathDFS(nextNode, target, graph, visited, memo);
                if (subResult.cost != Integer.MAX_VALUE && result.cost > edgeCost + subResult.cost) {
                    result.path = new ArrayList<>(subResult.path);
                    result.cost = edgeCost + subResult.cost;
                }
            }
        }

        result.path.add(0, current);
        visited[current] = 0; // Reset visited status for backtracking
        memo[current] = result;
        return result;
    }

    static PathInfo findMinPathBFS(List<List<Pair<Integer, Integer>>> graph, int source, int target) {
        Queue<PathInfo> queue = new LinkedList<>();
        int[] visited = new int[graph.size()];
        PathInfo[] memo = new PathInfo[graph.size()];

        queue.add(new PathInfo());
        queue.peek().path.add(source);

        while (!queue.isEmpty()) {
            PathInfo current = queue.remove();
            int currentNode = current.path.get(current.path.size() - 1);

            if (currentNode == target) {
                return current;
            }

            visited[currentNode] = 1;
            for (Pair<Integer, Integer> neighbor : graph.get(currentNode)) {
                int neighborNode = neighbor.first;

                if (visited[neighborNode] == 0) {
                    PathInfo newPath = new PathInfo();
                    newPath.cost = current.cost + neighbor.second;
                    newPath.path.addAll(current.path);
                    newPath.path.add(neighborNode);
                    queue.add(newPath);
                }
            }
        }

        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNodes = scanner.nextInt();
        int numEdges = scanner.nextInt();

        List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
        for (int i = 0; i <= numNodes; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= numEdges; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();
            int edgeCost = scanner.nextInt();
            graph.get(node1).add(new Pair<>(node2, edgeCost));
            graph.get(node2).add(new Pair<>(node1, edgeCost));
        }

        int source = scanner.nextInt();
        int target = scanner.nextInt();

        int[] visited = new int[numNodes + 1];
        PathInfo[] memo = new PathInfo[numNodes + 1];

        PathInfo dfsResult = findMinPathDFS(1, numNodes, graph, visited, memo);
        PathInfo bfsResult = findMinPathBFS(graph, source, target);


        System.out.println("DFS - Shortest Path Cost: " + dfsResult.cost);
        System.out.print("DFS - Path: ");
        for (int node : dfsResult.path) {
            System.out.print(node + " ");
        }
        System.out.println();

        if (bfsResult != null) {
            System.out.println("BFS - Shortest Path Cost: " + bfsResult.cost);
            System.out.print("BFS - Path: ");
            for (int node : bfsResult.path) {
                System.out.print(node + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path found from " + source + " to " + target + " using BFS.");
        }


    }

    static class Pair<F, S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
    }
}
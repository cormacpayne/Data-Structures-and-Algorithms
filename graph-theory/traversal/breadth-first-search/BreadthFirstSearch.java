import java.util.*;

class BreadthFirstSearch {
	public static void BFS(ArrayList<ArrayList<Integer>> graph, int vertices, int root) {
		// visited keeps track of which nodes we have already visited
		boolean[] visited = new boolean[vertices + 1];
		// queue will keep track of which nodes we are visiting next
		Queue<Integer> queue = new LinkedList<Integer>();
		// add the root node to the queue and mark it as visited
		queue.add(root);
		visited[root] = true;

		// while the queue is not empty, continue to search the graph
		while (!queue.isEmpty()) {
			// current keeps track of the node we are currently on
			int current = queue.remove();
			// neighbors give us a list of adjacent nodes
			List<Integer> neighbors = graph.get(current);

			// iterate over the neighbors and see if we have visited the node, and if not, add it to the queue
			for (Integer neighbor : neighbors) {
				// if we haven't visited the neighbor, mark it as visited and add it to the queue
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
	}
}
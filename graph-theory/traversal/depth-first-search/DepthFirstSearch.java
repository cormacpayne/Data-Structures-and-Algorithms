import java.util.*;

class DepthFirstSearch {
	public static void BFS(ArrayList<ArrayList<Integer>> graph, int vertices, int root) {
		// visited keeps track of which nodes we have already visited
		boolean[] visited = new boolean[vertices + 1];
		// stack will keep track of which nodes we are visiting next
		Stack<Integer> stack = new Stack<Integer>();
		// add the root node to the stack and mark it as visited
		stack.push(root);
		visited[root] = true;

		// while the stack is not empty, continue to search the graph
		while (!stack.isEmpty()) {
			// current keeps track of the node we are currently on
			int current = stack.pop();
			// neighbors give us a list of adjacent nodes
			List<Integer> neighbors = graph.get(current);

			// iterate over the neighbors and see if we have visited the node, and if not, add it to the stack
			for (Integer neighbor : neighbors) {
				// if we haven't visited the neighbor, mark it as visited and add it to the stack
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					stack.push(neighbor);
				}
			}
		}
	}
}
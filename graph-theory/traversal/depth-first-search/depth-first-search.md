## Depth-first Search

_Depth-first search_ (or DFS) is another form of graph traversal, but rather than visiting vertices "_level-by-level_", DFS aims to go as deep as possible in the graph before backtracking.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Depth-first-tree.svg/300px-Depth-first-tree.svg.png)

Imagine a "family tree", like one shown in the picture above. DFS will go as deep as it can in the graph before backtracking and repeating this process.

Starting at vertex 1, the graph will travel down the left side of the graph until it hits vertex 4, where it can no longer visit any unvisited vertices.

From vertex 4, it backtrack to vertex 3 and visits vertex 5, and since it can no longer visit any unvisited vertices, it backtracks to vertex 2, where it visits vertex 6.

This process repeats until the entire graph has been traversed.

![img](https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif)

Above is another visual representation of the DFS process starting from the top vertex.

### Coding DFS

The algorithm for DFS is very similar to that of BFS, except instead of using a _Queue_, we will be using a _Stack_.

Since a _Stack_ follows the "_last in, first out_" ordering, when we are adding neighbors of a vertex to the _Stack_, the last one we push will be the next one we visit, allowing us to constantly go deeper into the graph rather than traversing level-by-level.

```java
 // Initialize the stack
 Stack<Integer> stack = new Stack<Integer>(); 
 
 // This array will tell us if we have visited a vertex
 boolean[] visited = new boolean[ N ];               
 
 // Push the root vertex onto the stack
 stack.push( rootVertex );
 
 // While there is a vertex still in the stack...
 while ( !stack.isEmpty() )
 {
     // Get the current vertex
     Integer current = stack.pop();     
     // Get the current vertex's neighbors
     ArrayList<Integer> neighbors = graph[ current ];
     
     // For each of the current vertex's neighbors...
     foreach ( Integer neighbor : neighbors )
     {
         // If we haven't visited the neighbor...
         if ( !visited[ neighbor ] )
         {
             // Add the neighbor to the stack
             stack.push( neighbor );
             // Mark the neighbor as visited
             visited[ neighbor ] = true;
         }
     }
 }
```
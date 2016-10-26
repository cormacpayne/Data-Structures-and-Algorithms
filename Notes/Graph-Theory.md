# Graph Theory

## What is a Graph?

A _graph_ consists of _vertices_ (or nodes) and _edges_ (or paths)

Edges
- are connections between vertices
    - _e.g._, roads between cities
- can have one-way direction or can be bidirectional
- can have weights
    - _e.g._, time to travel between two cities

A _directional graph_ has edges that are all directional (_i.e._, if there's an edge from _A_ to _B_, there might not be one from _B_ to _A_)

An _acyclic graph_ contains no cycles (in a directed graph, there's no path starting from some vertex _v_ that will take you back to _v_)

A _weighted graph_ contains edges that hold a numerical value (_e.g._, cost, time, etc.)

### Unweighted, undirected graph

![img](https://upload.wikimedia.org/wikipedia/commons/b/bf/Undirected.svg)

### Unweighted, directed cyclic graph

![img](https://upload.wikimedia.org/wikipedia/commons/a/a2/Directed.svg)

### Unweighted, directed acyclic graph

![img](https://upload.wikimedia.org/wikipedia/commons/f/fe/Tred-G.svg)

### Weighted, undirected graph

![img](https://cdn.filestackcontent.com/NFPJSZO5SyoWO3VBMSdN)

### Complete graph

A _complete graph_ is a graph where each vertex has an edge to all other vertices in the graph 

![img](https://upload.wikimedia.org/wikipedia/commons/2/2d/4-simplex_graph.svg)

## Representing a Graph

There are two main ways to represent a graph:
1. _Adjacency matrix_
2. _Adjacency list_

### Adjacency Matrix

An adjacency matrix uses a 2-dimensional array to represent edges between two vertices

There are many ways to use an adjacency matrix to represent a matrix, but we will look at two variations

#### Connection matrix

```java
boolean[][] conn = new boolean[ N ][ N ];
```

The matrix `conn` tells us if two vertices are connected

```java
if ( conn[ i ][ j ] )
    // there is an edge from vertex i to vertex j

else
    // there is no edge from vertex i to vertex j
```

#### Cost matrix

```java
int[][] cost = new int[ N ][ N ];
```

The matrix `cost` tells us the cost (or edge weight) between two vertices

```java
cost[ i ][ j ] = 7; // this means it costs 7 units to travel from vertex i to vertex j
cost[ i ][ j ] = 0; // this usually means there is no edge from vertiex i to vertex j
```

#### Pros and Cons

_Pros_
- Easy to check if there is an edge between _i_ and _j_
    - calling `matrix[ i ][ j ]` will tell us if there is a connection

_Cons_
- To find all neighbors of vertex _i_, you would need to check the value of `matrix[ i ][ j ]` for all _j_
- Need to construct a 2-dimensional array of size _N_ x _N_

### Adjacency List

Rather than making space for all _N_ x _N_ possible edge connections, an _adjacency list_ keeps track of the vertices that a vertex has an edge to.

We are able to do this by creating an array that contains `ArrayLists` holding the values of the vertices that a vertex is connected to.

```java
ArrayList<Integer>[] graph = new ArrayList<Integer>[ N + 1 ];

// For each vertex, we need to initialize the list of vertices the vertex has a connection to
for ( int i = 0; i <= N; i++ )
{
    graph[ i ] = new ArrayList<Integer>();
}

graph[ i ].add( j ); // get the list of vertices for vertex i and add a connection to vertex j

ArrayList<Integer> neighbors = graph[ k ]; // get the list of vertices that vertex k is connected to
```

#### Pros and  Cons

_Pros_
- Saves memory by only keeping track of edges that a vertex has
- Efficient to iterate over the edges of a vertex
    - Doesn't need to go through all _N_ vertices and check if there is a connection

_Cons_
- Difficult to quickly determine if there is an edge between two vertices

## Breadth-first Search

_Breadth-first search_ (or BFS) is a form of graph traversal that starts at some vertex _i_ and visits all of _i_'s neighbors. It then visits all of the neighbors of _i_'s neighbors. This process keeps going until there are no more vertices left to visit.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Breadth-first-tree.svg/300px-Breadth-first-tree.svg.png)

Imagine a "family tree", like one shown in the picture above. BFS will visit all vertices of the same level before moving on to the next level.

We start by visiting vertex 1.

Then, we visit all of 1's neighbors: 2, 3, 4

Then, we visit all of 1's neighbors' neighbors: 5, 6, 7, 8

Finally, we visit all of their neighbors: 9, 10, 11, 12

![img](https://upload.wikimedia.org/wikipedia/commons/5/5d/Breadth-First-Search-Algorithm.gif)

Above is another visual representation of the BFS process starting from the top vertex.

### Coding BFS

We need to use some data structure that will allow us to visit vertices "_level-by-level_", that is, visit every vertex at level _j_ before we visit any vertex at level _j_+1.

In order to do this, we will be using a _Queue_ since it follows the "_first in, first out_" ordering; this means if we put all the vertices at level _j_ into the queue before the vertices at level _j_+1, we are guaranteed to visit the lower level vertices first.

 Below are the steps to follow for BFS:
 1. Push the root vertex onto the queue
 2. Pop the queue to get the current vertex
 3. For each unvisited neighbor of the current vertex, mark them as visited and push them onto the queue
 4. Go back to step 2 until the queue is empty
 
 ```java
 // Initialize the queue
 Queue<Integer> queue = new LinkedList<Integer>(); 
 
 // This array will tell us if we have visited a vertex
 boolean[] visited = new boolean[ N ];               
 
 // Push the root vertex onto the queue
 queue.add( rootVertex );
 
 // While there is a vertex still in the queue...
 while ( !queue.isEmpty() )
 {
     // Get the current vertex
     Integer current = queue.remove();     
     // Get the current vertex's neighbors
     ArrayList<Integer> neighbors = graph[ current ];
     
     // For each of the current vertex's neighbors...
     foreach ( Integer neighbor : neighbors )
     {
         // If we haven't visited the neighbor...
         if ( !visited[ neighbor ] )
         {
             // Add the neighbor to the queue
             queue.add( neighbor );
             // Mark the neighbor as visited
             visited[ neighbor ] = true;
         }
     }
 }
 ```

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

## Trees

A _tree_ is a special kind of graph that exhibits the following properties:
1. Acyclic graph
2. _N_ vertices with _N_-1 edges

The graphs above that we used for BFS and DFS were both trees.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Tree_graph.svg/180px-Tree_graph.svg.png)

Above is another example of a tree.

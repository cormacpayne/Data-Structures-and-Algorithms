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
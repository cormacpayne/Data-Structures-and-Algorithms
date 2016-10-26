# Dijkstra's Algorithm

## Motivating Problem

Imagine that we have a graph of flights across the country, and each flight has a given cost and time.

We want to find a series of flights from city _A_ to city _B_ such that the cost (or time) is minimized.

We can generalize this problem to the following:

Given a graph _G_ and a starting vertex _s_, what is the _shortest path_ from _s_ to any other vertex of _G_?

This problem is called the _Single-Source Shortest Paths_ (SSSP) problem.

## Single=Source Shortest Path

#### SSSP on an unweighted graph

If the graph is unweighted (or if all of the edges have equal or constant weight), we can use the BFS algorithm in order to solve this problem.

BFS goes "level-by-level", so it would visit all vertices that are one unit away first, and then two units away, and then three units away, etc.

The fact that BFS visits vertices of a graph "level-by-level" from a source vertex turns BFS into a natural choice to solve the SSSP problem on unweighted graphs.

### SSSP on a weighted graph

If the given graph is weighted, BFS no longer works. This is because there can be "longer" paths from the source to vertex, but has a smaller cost than the "shorter" path found by BFS.

To solve the SSSP problem on weighted graphs, we use a greedy algorithm: _Dijkstra's Algorithm_

## Dijkstra's Algorithm

### Procedure

1. Give every vertex a tentative distance
    - the initial vertex will have a distance of zero
    - every other vertex will have a distance of infinity
2. Set the initial vertex as the current vertex; mark all other vertices unvisited
3. For the current vertex, consider all of its unvisited neighbors and calculate the tentative distance to each vertex; compare the newly calculated distance to the current assigned value and set the tentative distance to the smaller distance
4. When we are done considering all of the neighbors of the current vertex, mark the current vertex as visited
5. If the destination vertex has been marked visit, or if the smallest tentative distance among unvisited vertices is infinity, stop the algorithm
6. Select the unvisited vertex that is marked with the smallest tentative distance, and set it as the new current vertex, then go back to step 3

![img](https://upload.wikimedia.org/wikipedia/commons/5/57/Dijkstra_Animation.gif)

Above is a visual representation of Dijkstra's Algorithm

### Coding Dijkstra's Algorithm

There are several ways to implement this algorithm
- `int[] distance`
- `PriorityQueue<Vertex>`

#### Using an array

```java
// This array will keep track of the tentative distance up to each vertex
int[] distance = new int[ N ];
// This array will tell us if we have visited a vertex or not
boolean[] visited = new boolean[ N ];

// Mark the tentative distance of the starting vertex as zero
distance[ start ] = 0;
// Set the current vertex as the starting vertex
int current = start;

// While we have not reached our destination...
while ( !visited[ destination ] )
{
    // Mark the current vertex as visited
    visited[ current ] = true;
    // Get the neighboring vertices of the current vertex
    ArrayList<Integer> neighbors = graph[ current ];
    
    // For each of the neighbors of the current vertex...
    for ( int neighbor : neighbors )
    {
        // Calculate the new distance to the neighbor
        // This can be calculated by taking the tentative distance up to the current vertex and adding the
        // cost to go from the current vertex to the neighbor
        int newDistance = distances[ current ] + costs[ current ][ neighbor ];
        
        // If this new distance we calculated is smaller than the current tentative distance of the neighbor...
        if ( newDistance < distances[ neighbor ] )
        {
            // Update the tentative distance of the neighbor with this new distance
            distances[ neighbor ] = newDistance;
        }
    }
    
    // We now need to calculate the new current vertex, which will be the vertex with the smallest tentative
    // distance that we have yet to visit in the graph
    
    // This will keep track of the next vertex that we will set as the current vertex
    int index = -1;
    // This will keep track of the minimum tentative distance of the next current vertex
    int min = Integer.MAX_VALUE;
    
    // For each of the vertices in the graph...
    for ( int j = 0; j < N; j++ )
    {
        // If we have not visited the vertex and the tentative distance of the vertex is smaller than our current min...
        if ( !visited[ j ] && distances[ j ] < min )
        {
            // Update the index with the vertex
            index = j;
            // Update the min distance value
            min = distances[ j ];
        }
    }
    

// If we were unable to find another vertex to visit...
    if ( index == -1 )
    {
        // Break out of the loop
        break;
    }
    
    // Update the current vertex
    current = index;
}
```

#### Using a priority queue

Remember that as you add items to a priority queue, they are automatically sorted within the queue, so that you are given the "smallest" item when you remove it from the queue.

First, we need to define our `Vertex` class and what attributes it will contain.

```java
// In order for the Vertex class to be sortable, we need to implement the Comparable interface
class Vertex implements Comparable<Vertex>
{
    // id   - the integer representing the vertex
    // dist - the tenetative distance to the vertex
    int id, dist;
    
    // neighbors - the integer value of the neighboring vertices
    ArrayList<Integer> neighbors;
    
    public Vertex( int id )
    {
        this.id = id;
        this.dist = Integer.MAX_VALUE;
        this.neighbors = new ArrayList<Integer>();
    }
    
    // This method needs to be overridden to determine how vertices are sorted
    public int compareTo( Vertex v )
    {
        // The vertex that has a smaller tentative distance should be removed from the queue first
        if ( this.dist < v.dist )
        {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
```

Remember that when we used `dist[]` to keep track of distances, we had to look through _all_ tentative distances to see which vertex to visit; with this method, we just remove the next vertex from the priority queue and it will be guaranteed to have the smallest distance.

Now that we have our `Vertex` class setup, how do we use it to implement Dijkstra's algorithm?

Let's start by creating all of the vertices of a graph and storing them into an array.

```java
Vertex[] graph = new Vertex[ N ];

// For each vertex in the graph...
for ( int i = 0; i < N; i++ )
{
    // Initialize the Vertex object corresponding to the vertex
    graph[ i ] = new Vertex( i );
}

graph[ i ].neighbors.add( j ); // Add vertex j as a neighbor of vertex i
```

We know the tentative distance of our source vertex is zero, so we can assign that value and add the vertex to the priority queue.

```java
vertex[ source ].dist = 0;
priorityQueue.add( vertex[ source ] );
```

Just like BFS, we are repeating the steps of the algorithm until the queue is empty. This makes sense for Dijkstra's algorithm since we want to keep repeating the process until we either
- run out of vertices (queue is empty), or
- reach our destination vertex

Now that we have our stopping condition, we can implement the rest of the algorithm

```java
// While the queue still has vertices left to visit...
while ( !priorityQueue.isEmpty() )
{
    // Get the current vertex
    Vertex current = priorityQueue.remove();
    
    // If the current vertex is our destination...
    if ( current.id == destination )
    {
        // Break from the loop
        break;    
    }
    
    // Get the neighbors of the current vertex
    ArrayList<Integer> neighbors = current.neighbors;
    
    // For each of the neighbors...
    for ( Integer j : neighbor )
    {
        // Get the Vertex representation of the neighbor
        Vertex neighbor = graph[ j ];
        
        // Calculate the new distance to the neighbor
        int newDistance = current.dist + cost[ current.id ][ neighbor.id ];
        
        // If the new distance we calculated is smaller than the tentative distance of the neighbor...
        if ( newDistance < neighbor.dist )
        {
            // Remove the neighbor from the priority queue so we can update the value
            priorityQueue.remove( neighbor );
            // Update the tentative distance of the neighbor
            neighbor.dist = newDistance;
            // Add the neighbor into the priority queue so that it can be sorted among the other vertices
            priorityQueue.add( neighbor );
        }
    }
}
```

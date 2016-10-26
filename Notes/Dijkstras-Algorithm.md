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

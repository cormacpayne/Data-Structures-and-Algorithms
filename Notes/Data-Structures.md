# Data Structures

## What is a data structure?

A data structure is a particular way of organizing data in a computer so that it can be used efficiently.

When a problem becomes very complicated, it becomes useful to have some structure to store data in memory.

## Array

A collection of elements, each identitifed by an array index or key

### Example

```java
int[] array = new int[5];

int array = {2, 3, 5, 7, 11};

System.out.print(array[2]); // output is '5'

array[2] = 13; // changes the '5' to '13'
```

## Linked Lists

A list can hold an arbitrary number of items, and can easily change size to add or remove items.

Each element in the list is called a *node*, and contains two pieces of information:
- **data** - information that the node is holding
- **nextNode** - a reference to the node that follows the current one

An example class for the **nodes** would look like the following:

```java
class Node
{
  String data;
  Node nextNode;
}
```

### Example

The following code shows how to create a basic linked list (no data)

```java
Node start = new Node();

for (int i = 1; i <= 10; i++)
{
  start.nextNode = new Node();
  start = start.nextNode;
}
```

The following code shows how to iterate over a linked list

```java
Node start = ...

while (start != null)
{
  // do something with data from the node
  start = start.nextNode;
}
```

## Lists

If you don't want to worry about creating your own data structure to utilize a list with an unbounded size, use *ArrayLists*.

Very similar to an array, but does not need initial size (resizes itself).

Primitive types (*int, double, char*, etc.) must be changed to their **object** counterpart (*Integer, Double, Character*, etc.).

### Example

```java
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(2);
list.add(3);
System.out.print(list.get(1)); // outputs '3'
list.set(0, 5); // changes '2' to '5'
```

## Sets

A list of elements that **does not contain duplicates**.

Does not preserve the ordering of the elements as you add them, and doesn't have a `get(x)` method like *ArrayList*.

Usually sets are used just to iterate over once you add and remove elements, and can check for uniqure elements in one pass.

`Set` is an interface in Java, so you will need to instantiate it using the `HashSet` class.

### Example

```java
Set<Integer> set = new HashSet<Integer>();
set.add(7); // {7}
set.add(3); // {7, 3}
set.add(3); // {7, 3}
set.add(4); // {7, 3, 4}
```

The following is how you can iterate over each element in a set:

```java
for (Integer i : set)
{
  // do something with the element
}
```

## Queue

Imagine a movie queue where the first one in line is the first one out. In data structures, a queue operates the same one: the first element pushed into the queue is the first element popped out.

In Java, `Queue` is an interface, so it cannot be instantiated; instead, you need to instantiate a `LinkedList` object (which already implements `Queue`)]

A queue has three main operations:
- **Push** - add an element to the queue
- **Pop** - remove the first element of the queue
- **Peek** - see what the first element of the queue is without removing it

```java
Queue<Integer> qyeye = new LinkedList<Integer>();
queue.add(2); // {2}
queue.add(4); // {2. 4}
queue.add(8); // {2, 4, 8}
System.out.print(queue.peek()); // output is '2'
queue.remove(); // {4, 8}
```

## Priority Queue

First in, first out (FIFO) like a queue, but stores element in a *heap* (specialized tree-based structure).

As elements are pushed to the `PriorityQueue`, they are add to the heap, which sorts the elements based on a pre-defined priority.

### Example

```java
PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
pq.add(6);   // {6}
pq.add(3);   // {3, 6}, '3' has a higher priority than '6'
pq.add(10);  // {3, 6, 10}
pq.remove(); // removes '3'
```

## Stack

Imagine a stack of plates, the last plate that you put on the stack is the first plate that you take off.

A stack has the same operations as a queue: push, pop, peek.

### Example

```java
Stack<Integer> stack = new Stack<Integer>();
stack.push(2); // {2}
stack.push(3); // {2, 3}
stack.push(5); // {2, 3, 5}
System.out.print(stack.peek()); // output is '5'
stack.pop(); // {2, 3}
```

## Map

An object that maps keys to values; a map cannot contain duplicate keys.

The keys of the map are sent through a hash function, which tells us which bucket to place the corresponding value in.

We can map any `Object` to any other `Object`:
- number --> color (`int` to `String`)
- phone number --> address (`String` to `String`)
- name --> friends (`String` to `ArrayList<String>`)

Like `Queue`, `Map` is an interface, so it cannot be instantiated; instead, you need to instantiate a `HashMap` object (which already implements `Map`).

### Example

```java
Map<Integer, String> map = new HashMap<Integer, String>();
map.put(2, "blue");
map.put(5, "red");
System.out.print(map.get(2)); // output is 'blue'
System.out.print(map.get(4)); // output is null
```

## TreeMap

Same functionality as a map, but the map is sorted according to the natural ordering of its keys.

### Example

```java
TreeMap<Integer, String> map = new TreeMap<Integer, String>();
map.put(17, "red");
map.put(28, "blue");
map.put(14, "green");

for (Integer key : map.keySet())
{
  System.out.print(key);  // output is '14, 17, 28'
}
```

## Problems

- [Seinfeld](http://www.spoj.com/problems/ANARC09A/)
- [Friends of Friends](http://www.spoj.com/problems/FACEFRND/)
- [Homo or Hetero](http://www.spoj.com/problems/HOMO/)
- [Street Parade](http://www.spoj.com/problems/STPAR/)
- [Lowest Common Ancestor](http://www.spoj.com/problems/LCA/)

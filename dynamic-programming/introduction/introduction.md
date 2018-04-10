# Dynamic Programming

## What is Dynamic Programming?

Calculate the answer to the following expression: 3<sup>4</sup> + 5! + (50 * 9)

Now calculate the answer to the following expression: 3<sup>4</sup> + 5! + (50 * 9) + 17

It was much easier to calculate the second expression than it was the first, but why?

You didn't need to recalculate the entire second expression because you _memorized_ the value of the first expression and used it for a later time.

That's exactly what dynamic programming is: _remembering information in order to save some time later on_.

## What is Dynamic Programming (Mathematical definition)

Dynamic programming is a method for solving complex problems by breaking it down into a collection of simpler subproblems.

Dynamic programming problems exhibit two properties:
- Overlapping subproblems
- Optimal substructure

So what exactly does this mean? Let's take a look at some examples.

## Fibonacci Sequence

The Fibonacci sequence is one in which a term in the sequence is determined by the sum of the two terms before it.

1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...

We say that the _n_<sup>_th_</sup> Fibonacci number can be found by F<sub>n</sub> = F<sub>n-1</sub> + F<sub>n-2</sub>, where F<sub>0</sub> = 1 and F<sub>1</sub> = 1

Let's say we wanted to write a recursive function for this sequence to determine the n<sup>th</sup> Fibonacci number.

```java
// This function will return the nth Fibonacci number
public int fib( int n )
{
    // Base case: the first two Fibonacci numbers are 1
    if ( n == 0 || n == 1 )
        return 1;
    return fib( n - 1 ) + fib( n - 2 );
}
```

What will be the function calls if we wanted to determine `fib( 5 )`?

```
fib( 5 )
fib( 4 ) + fib( 3 )
fib( 3 ) + fib( 2 ) + fib( 3 )
fib( 2 ) + fib( 1 ) + fib( 2 ) + fib( 3 )
fib( 1 ) + fib( 0 ) + 1 + fib( 2 ) + fib( 3 )
1 + 1 + 1 + fib( 2 ) + fib( 3 )
```

Is it necessary to compute `fib( 2 )` and `fib( 3 )`?

![img](https://harryrschwartz.com/assets/images/posts/fib-call-tree.png)

The above tree represents the function calls for `fib( 5 )`

Notice that when we calculate `fib( 4 )` we also calculate the value of `fib( 3 )`

When we call `fib( 5 )`, it calls `fib( 3 )`, but if we already have the value of that function call from before, there's no need to recompute it.

Since `fib( 5 )` asks us to call the function `fib( 3 )` _multiple times_, we have found an _overlapping subproblem_ - a subproblem that is reused numerous times, but given the same result each time it's solved.

`fib( 3 )` will give us the same result no matter how many times we call it.

Rather than solving these overlapping subproblems _every time_, we can do the following:
- solve the subproblem once and save its value
- when you need to solve the subproblem _again_, rather than going through all the computation to do the solving, return the _memorized_ value

```java
int[] dp = new int[ SIZE ]; // all values initialized to 0
public int fib( int n )
{
    if ( n == 0 || n == 1 )
        return 1;
    if ( dp[ n ] != 0 )
        return dp[ n ];
    return dp[ n ] = fib( n - 1 ) + fib( n - 2 );
}
```

## Pascal's Triangle

In mathematics, Pascal's triangle is a triangular array of the binomial coefficients.

Binomial coefficients can be read as "_n_ choose _k_" - how many ways are there to choose _k_ elements from a set of _n_ elements?

The entries in each row are numbered with row _n_ = 0 at the top, and the entries in each row are numbered from the left beginning with _k_ = 0

The _k_<sup>_th_</sup> element in the _n_<sup>_th_</sup> row represents "_n_ choose _k_"

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Pascal's_triangle_5.svg/250px-Pascal's_triangle_5.svg.png)

Elements in each row of Pascal's triangle can be found by summing the two numbers in the row above and to the left and right of the element.

This property can be seen as a closed formula:

![img](https://wikimedia.org/api/rest_v1/media/math/render/svg/203b128a098e18cbb8cf36d004bd7282b28461bf)

Let's try and create a recursive method that will find us "_n_ choose _k_" using the above formula.

What are the base cases?

We know that there is only _one_ way to choose zero elements from an _n_-element set, as well as _one_way to choose _n_ elements from an _n_-element set.

```java
// This function will return "n choose k"
public int pascal( int n, int k )
{
    if ( k == 0 || k == n )
        return 1;
    return pascal( n - 1, k - 1 ) + pascal( n - 1, k );
}
```

This function leaves us with the same problem we hads with the Fibonacci function: we are going to be recalculating subproblems that we have already solved before.

We can fix this like we did with the Fibonacci function: memorizing the value of the subproblems so that we don't have to recalculate them later.

```java
int[][] dp = new int[ N_SIZE ][ K_SIZE ];
public int pascal( int n, int k )
{
    if ( k == 0 || k == n )
        return 1;
    if ( dp[ n ][ k ] != 0 )
        return dp[ n ][ k ];
    return dp[ n ][ k ] = pascal( n - 1, k - 1 ) + pascal ( n - 1, k );
}
```

## Knapsack Problem

### Problem Statement

You have a knapsack that can hold up to a certain weight _W_, and you have _N_ items that you can pack for a trip; however, all of these items might not fit in your knapsack.

Each item you can pack has a weight _w_ and a value _v_.

Your goal is to pack some items into your knapsack such that the total weight of all of the items doesn't exceed the maximum weight limit _W_, and the sum of all the values of the items in your knapsack is _maximized_.

### Approaches

1. Calculate all subsets of items and see which subset has the highest value, but also stays under the knapsack's weight limit
2. Create some ratio for each item _v_ / _w_ and take the items with the highest ratio until you can't pack anymore
3. Use dynamic programming

### Solving Knapsack: Take I - Subset Solution

Let's calculate all subsets of the _N_ items that we have.

This means that there are 2<sup>_N_</sup> possible subsets that we will need to go through.

However, if _N_ is sufficiently large (larger than 25), this approach will be too slow

### Solving Knapsack: Take II - Ratio Solution

Imagine we have a knapsack with _W_ = 6 and are given the following set of items:  
_w_ = {2, 2, 2, 5}  
_v_ = {7, 7, 7, 20}

Let's find the _v_ / _w_ ratio of each item  
_v_ / _W_ = {3.5, 3.5, 3.5, 4}

Now that we have the ratios, let's take the items with the greatest ratio until we have no more space left in the knapsack.

We will take the item with _w_ = 5, _v_ = 20 first and put it into oiur knapsack.

We now have 1 weight left in our knapsack, so we cannot fit any of the other items in our knapsack, so we end up with a value of 20.

However, if we would've taken the other three items, we would've had no weight left and a value of 21 instead.

### Solving Knapsack: Take III - Dynamic Programming

There are two approaches to dynamic programming:
- _Top-down_: define the overall problem first and what subproblems you will need to solve in order to get your answer
- _Bottom-up_: start by solving subproblems and build up to the subproblems

We will look at how to solve the knapsack problem using both approaches

#### Bottom-Up Solution

Using the bottom-up approach, we need to first solve all of the possible subproblems, and then we can use those to solve our overall problem.

We will create a table that will hold the solutions to these subproblems, and keep building on it until we are done and have our answer.

Let's say we have a knapsack with capacity _W_ = 6 and the following weights and values for _N_ = 5 items:
_w_ = {3, 2, 1, 4, 5}  
_v_ = {25, 20, 15, 40, 50}

Let's figure out for all capacities, _j_, less than _W_ = 6, what is the maximum value we can get if we only use the first _i_ items

`dp[ i ][ j ]` will be the solution for each subproblem

Let's create our table; each cell will represent `dp[ i ][ j ]`

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 |   |   |   |   |   |   |   |
| w = 3, v = 25 | 1 |   |   |   |   |   |   |   |
| w = 2, v = 20 | 2 |   |   |   |   |   |   |   |
| w = 1, v = 15 | 3 |   |   |   |   |   |   |   |
| w = 4, v = 40 | 4 |   |   |   |   |   |   |   |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

If we can't use any of the items (_i_ = 0), the value at any capacity will be 0

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 |   |   |   |   |   |   |   |
| w = 2, v = 20 | 2 |   |   |   |   |   |   |   |
| w = 1, v = 15 | 3 |   |   |   |   |   |   |   |
| w = 4, v = 40 | 4 |   |   |   |   |   |   |   |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

For _i_ = 1, we can only take the first item in our set, so we want to take it whenever we have the capacity for it

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 | 0 | 0 | 0 | 25 | 25 | 25 | 25 |
| w = 2, v = 20 | 2 |   |   |   |   |   |   |   |
| w = 1, v = 15 | 3 |   |   |   |   |   |   |   |
| w = 4, v = 40 | 4 |   |   |   |   |   |   |   |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

For _i_ = 2, we can now take the second item in our set in addition to the first item, but only when taking the item at a given capacity will give us greater value than not taking it.

For example, at capacity _j_ = 2, previously when we didn't have the item, we could get at most 0 value, but if we now take the item, we can get at most 20 value.

At capacity _j_ = 3, previously when we didn't have the item, we could get at most 25 value, but if we now take the item, we can get 20 value AND the maximum value we could get previously at capacity _j_ = 1, which is 0 (since we are looking at a capacity of _j_ = 3, and we took the second item, the capacity we have left is _j_ = 1, so we look at the maximum value we could get at this weight with all of the previous items). In this case, we do not want to take the item, because doing so will give us a lower value (20 < 25).

At capacity _j_ = 5, previously when we didn't have the item, we could get at most 25 value, but if we now take the item, we can get 20 value AND the maximum value we could get previously at capacity _j_ = 3, which is 25 (since we are looking at a capacity of _j_ = 5, and we took the second item, the capacity we have left is _j_ = 3, so we look at the maximum value we could get at this weight with all of the previous items). In this case, we do want to take the item, because doing so will give us more value (45 > 25).

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 | 0 | 0 | 0 | 25 | 25 | 25 | 25 |
| w = 2, v = 20 | 2 | 0 | 0 | 20 | 25 | 25 | 45 | 45 |
| w = 1, v = 15 | 3 |   |   |   |   |   |   |   |
| w = 4, v = 40 | 4 |   |   |   |   |   |   |   |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

For _i_ = 3, we repeat the same process that we did for _i_ = 2.

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 | 0 | 0 | 0 | 25 | 25 | 25 | 25 |
| w = 2, v = 20 | 2 | 0 | 0 | 20 | 25 | 25 | 25 | 25 |
| w = 1, v = 15 | 3 | 0 | 15 | 20 | 35 | 40 | 45 | 60 |
| w = 4, v = 40 | 4 |   |   |   |   |   |   |   |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

For _i_ = 4, we repeat the same process as before

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 | 0 | 0 | 0 | 25 | 25 | 25 | 25 |
| w = 2, v = 20 | 2 | 0 | 0 | 20 | 25 | 25 | 45 | 45 |
| w = 1, v = 15 | 3 | 0 | 15 | 20 | 35 | 40 | 45 | 60 |
| w = 4, v = 40 | 4 | 0 | 15 | 20 | 35 | 40 | 55 | 60 |
| w = 5, v = 50 | 5 |   |   |   |   |   |   |   |

For _i_ = 5, we repeat the same process as before

|   |   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|---|---|
|   | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
| w = 3, v = 25 | 1 | 0 | 0 | 0 | 25 | 25 | 25 | 25 |
| w = 2, v = 20 | 2 | 0 | 0 | 20 | 25 | 25 | 45 | 45 |
| w = 1, v = 15 | 3 | 0 | 15 | 20 | 35 | 40 | 45 | 60 |
| w = 4, v = 40 | 4 | 0 | 15 | 20 | 35 | 40 | 55 | 60 |
| w = 5, v = 50 | 5 | 0 | 15 | 20 | 35 | 40 | 55 | 60 |

Now that the table is filled out, we can solve our original problem, where _i_ = 5 and _j_ = 6, by looking up the answer from the table

So our answer is: `dp[ 5 ][ 6 ]` = 60

```java
for ( int i = 1; i <= N; i++ )
{
    for ( int j = 0; j <= W; j++)
    {
        if ( weight[ i ] <= j )
        {
            if ( value[ i ] + dp[ i - 1 ][ j - weight[ i ] ] > dp[ i - 1 ][ j ] )
            {
                dp[ i ][ j ] = value[ i ] + dp[ i - 1 ][ j - weight[ i ] ];
            }
            else
            {
                dp[ i ][ j ] = dp[ i - 1 ][ j ];
            }
        }
        else
        {
            dp[ i ][ j ] = dp[ i - 1][ j ];
        }
    }
}
```

### Top-Down Solution

To solve the problem using the top-down approach, we will try to come up with some recursive function that will give us the answer.

As we did with the bottom-up approach, we will go item by item and see if we want to take it or not.

We will also keep track of how much weight we have left in the knapsack as we go through the items (this will help us determine if we can take an item or not).

_Note_: when we're looking at an item, we only have two options: _take_ or _ignore_

This is why this problem is called the "_0/1 Knapsack Problem_"

```java
public int solve( int i, int j )
{
    // Determines the max value you can get when starting at the ith item with j remaining weight left to use
}
```

Let's try and figure out some base cases for this function.

What happens if _j_ == 0?
- the max value we get by starting at _any_ index with _no weight left_ will be zero

What happens if _i_ == _N_?
- the max value we get by starting at an index _outside of our item range_ will be zero

Let's look at every other case where _i_ != _N_ and _j_ > 0

What happens if the weight of the _i_<sup>_th_</sup> item is greater than the remaining weight, _j_?

If the weight of the item we are currently looking at is _greater_ than the amount of weight we have left, there's no way that we can take this item, so we _ignore_ it

```java
solve( i + 1, j )
```

The maximum value we get by starting at an index _where we can't take the item_ will be determined by the maximum value we get by starting at the _next_ item with the _same_ remaining weight to use.

What happens if the weight of the _i_<sup>_th_</sup> item is less than or equal to the remaining weight, _j_?

_Remember_, we only have two options: _take_ or _ignore_

We know what ignoring an item looks like:

```java
solve( i + 1, j )
```

But what does _taking_ an item look like?

Well, we know that the value of the remaining weight will be different
- we took an item, so we need to subtract out it's weight from the remaining weight

We also know that since we _took_ the item, we can add its value to our result

Our resulting function call for _taking_ an item would be

```java
value[ i ] + solve( i + 1, j - weight[ i ] )
```

The maximum value we get by starting at an index _where we take the item_ can be determined by the sum of
- the value of the item, and
- the maximum value we get by starting at the _next_ item with the new remaining weight

However, we just saw two separate function calls where we have enough weight to take an item, so which one do we use?

```java
int ignore = solve( i + 1, j );
int take = value[ i ] + solve( i + 1, j - weight[ i ] );
Math.max( ignore, take );
```

We now know our solution handles every case for the Knapsack problem, giving us the optimal solution when we call `solve( 0, W )`, and it shows the problem has an _optimal substructure_ - that is, an optimal solution can be constructed from the optimal solutions of its subproblems.

If we solve any subproblem `solve( i, j )`, we are _guaranteed_ to get the optimal answer, and we can use these optimal subproblems to solve _other_ subproblems.

We know that the problem has optimal substructure, but does it have any _overlapping subproblems_?

Let's look at an example:  
_v_ = {100, 70, 50, 10}  
_w_ = {10, 4, 6, 12}  
_W_ = 12

Imagine that we take the first item and ignore the next two
- we will be at `solve( i = 3, j = 2 )`

Imagine that we ignore the first item and take the next two
- we will be at `solve( i = 3, j = 2 )`

We have now determined we have _overlapping subproblems_, so we can save the value of a specific _index_ / _remaining weight_ pair, so we don't have to recompute the solution to the subproblem each time

```java
int[][] dp = new int[ N ][ W ];
public int solve( int i, int j )
{
    // If there's no more space or we run out of items, we can't get anymore value
    if ( j == 0 || n == N )
        return 0;
    // If we have already solved this subproblem, return the value
    if ( dp[ i ][ j ] != 0 )
        return dp[ i ][ j ];
        
    // If the weight of the current item is greater than the weight we have left in the knapsack, ignore it
    if ( weight[ i ] > j )
        return solve( i + 1, j );
        
    // For every other case, determine if ignoring or taking the current item will yield more value
    int ignore = solve( i + 1, j );
    int take = value[ i ] + solve( i + 1, j - weight[ i ] );
    return dp[ i ][ j ] = Math.max( ignore, take );
}
```

## Problems

- [The Knapsack Problem](http://www.spoj.com/problems/KNAPSACK/)
- [Party Schedule](http://www.spoj.com/problems/PARTY/)
- [Tri graphs](http://www.spoj.com/problems/ACPC10D/)
- [Bytelandian gold coins](http://www.spoj.com/problems/COINS/)
- [Coins Game](http://www.spoj.com/problems/MCOINS/)
- [Feline Olympics - Mouseball](http://www.spoj.com/problems/MBALL/)
- [Piggy-Bank](http://www.spoj.com/problems/PIGBANK/)
- [Mixtures](http://www.spoj.com/problems/MIXTURES/)
- [TV game](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=851)
- [Fire! Fire!! Fire!!!](https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1184)
- [Sweet and Sour Rock](http://www.spoj.com/problems/ROCK/)
- [Rent your airplane and make money](http://www.spoj.com/problems/RENT/)
- [Scuba diver](http://www.spoj.com/problems/SCUBADIV/)
- [ACM (ACronymMaker)](http://www.spoj.com/problems/ACMAKER/)
- [Two Ends](http://www.spoj.com/problems/TWENDS/)

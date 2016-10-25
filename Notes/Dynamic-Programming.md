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

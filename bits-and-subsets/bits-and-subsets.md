# Bits and Subsets

## What is a bit?

A _bit_ is the basic unit of information in computing.

A bit can only have two values: **0** or **1**

The two values for a bit can also be referred as _true_ or _false_, _on_ or _off_, etc.

Numbers can be represented using bits: **base-2 (or binary)**

## Binary Numbers

Numbers that we see on a daily basis are said to be represented in _base 10_, called their _decimal representation_ (_e.g._, 32, -3949, 10000, 0)

_Binary numbers_, which are expressed in _base-2_, are numbers that are composed of only **0s** and **1s**.

16<sub>10</sub> == 10000<sub>2</sub>

54<sub>10</sub> == 110110<sub>2</sub>

### Converting from Binary to Decimal

1. List out the powers of two above each bit, going right to left
2. For each bit in the binary number, if the value of the bit is a one, add its corresponding power of two to a running sum
3. After going through all of the bits, the running sum will be the _base-10_ representation of the number

For example, let's convert 1001101<sub>2</sub> to a _base-10_ number:

| 64 | 32 | 16 | 8 | 4 | 2 | 1 |
|---|---|---|---|---|---|---|
| 1 | 0 | 0 | 1 | 1 | 0 | 1 |

For each bit that is a one, we will add their corresponding power of two to a running sum.

**64** + 0 + 0 + **8** + **4** + 0 + **1** = 77

### Converting from Decimal to Binary

1. Divide the number by two
2. Write down the remainder (either **0** or **1**)
3. If the number is non-zero, go back to step 1
4. The last remainder is the first bit in the _base-2_ representation of the number, the second to last remainder is the second bit, etc.

For example, let's convert 81<sub>10</sub> to a _base-2_ number:

81 mod 2 = 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 81 / 2 = 40  
40 mod 2 = 0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 40 / 2 = 20  
20 mod 2 = 0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 20 / 2 = 10  
10 mod 2 = 0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 10 / 2 = 5  
5 mod 2 = 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 5 / 2 = 2  
2 mod 2 = 0 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 2 / 2 = 1  
1 mod 2 = 1 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 1 / 2 = 0  

We then read the remainders in reverse order to get the _base-2_ representation of 81: **1010001<sub>2</sub>**

## Bitwise Operations

### NOT

The bitwise _NOT_, or complement, is an operation that performs _negation_, which means bits that are zero become one, and those that are one become zero.

For example,  
1011001 --> 0100110  
1111111 --> 0000000  
1000000 --> 0111111  

In Java, the bitwise _NOT_ is represented by the '~' character.

```java
int result = ~32; // 31
```

### AND

The bitwise _AND_ is an operation between two binary numbers that performs the _logical AND_ operation on each pair of the corresponding bits, which means that if both bits are one, the resulting bit is one, and any other combination of bits results in a zero bit.

|   | 1 | 0 |
|---|---|---|
| **1** | 1 | 0 |
| **0** | 0 | 0 |

In Java, the bitwise _AND_ is represented by the '&' character.

```java
int result = 102 & 53; // 36
```

For example, let's look at 102 & 53

### OR

The bitwise _OR_ is an operation between two binary numbers that performs the _logical OR_ operation on each pair of corresponding bits, which means that if both bits are zero, the resulting bit is zero, and any other combination results of bits results in a one bit.

|   | 1 | 0 |
|---|---|---|
| **1** | 1 | 1 |
| **0** | 1 | 0 |

In Java, the bitwise _OR_ is represented by the '|' character.

```java
int result = 102 | 53; // 119
```

### XOR

The bitwise _XOR_ is an operation between two binary numbers that performs the _logical XOR_ operation on each pair of corresponding bits, which means that if both bits are _different_, the resulting bit is one, and if the bits are the same, the resulting bit is zero.

|   | 1 | 0 |
|---|---|---|
| **1** | 0 | 1 |
| **0** | 1 | 0 |

In Java, the bitwise _XOR_ is represented by the '^' character.

```java
int result = 102 ^ 53; // 83
```

## Bit Shifting

The _bit shift_ operation moves (or shifts) bits to the left or right in a binary number.

In Java, a _left-shift_ is denoted by '<<', and a _right-shift_ is denoted by '>>'.

You will also need to declare how many bits you want to shift a number by.

For example:  
101<sub>2</sub> << 2 == 10100<sub>2</sub>  
100101<sub>2</sub> >> 3 == 100<sub>2</sub>  
1<sub>2</sub> << 5 == 100000<sub>2</sub>  

Let's look at the following pattern of bit shifts:  
1<sub>2</sub> << 0 == 1<sub>2</sub> == 1<sub>10</sub>  
1<sub>2</sub> << 1 == 10<sub>2</sub> == 2<sub>10</sub>  
1<sub>2</sub> << 2 == 100<sub>2</sub> == 4<sub>10</sub>  
1<sub>2</sub> << 3 == 1000<sub>2</sub> == 8<sub>10</sub>  

Based on this pattern, we can come up with the following rule:

1<sub>2</sub> << k == 2<sup>k</sup>

## Subsets

Let _A_ = {2, 3, 5, 7}, and let _B_ be a subset of _A_.

_B_ is a subset of _A_ if all elements in _B_ are also elements in _A_.

The following are possible values for _B_:  
_B_ = {2, 3, 5}  
_B_ = {5, 7}  
_B_ = {2, 3, 5, 7}  
_B_ = { &nbsp; }  

### Number of Subsets

For each element in _S_, we have two choices:  
- put the element in our subset
- don't put the element in our subset

Because we have two choices per element, the total number of subsets that we can have can be calculated with 2<sup>_N_</sup>, where _N_ is the number of elements in _S_.

In order to create a subset, we either _take_ or _ignore_ an element in the set. Because there are two choices for each element, we can model a subset using a _binary string_ of length _N_.

For a bit at some index _k_ in our binary string
- if the bit at index _k_ is zero, then our subset _does not_ contain the _k_<sup>_th_</sup> element
- if the bit at index _k_ is one, thjen our subset _does_ contain the _k_<sup>_th_</sup> element

If we have a set _S_ = {2, 3, 5, 8}, there are four elements in this set, so we will need to create a binary string containing _N_ = 4 bits.

| 0 | 1 | 2 | 3 |
|---|---|---|---|
| 2 | 3 | 5 | 8 |

Let's look at different subsets and their binary string representations

| Subset | Binary | Decimal |
|---|---|---|
| {3, 8} | 1010 | 10 |
| {2, 3} | 0011 | 3 |
| {2, 3, 5, 8} | 1111 | 15 |
| { &nbsp; } | 0000 | 0 |

### Subset Iteration

How can we easily iterate over _all_ subsets of a set _S_ with _N_ elements?

We know that an _empty_ subset has a binary string of 000...000<sub>2</sub> which is 0<sub>10</sub>

We also know that a _full_ subset has a binary string of 111...111<sub>2</sub> which is 2<sup>_N_</sup> - 1

Now that we know all subsets fal in the range of [0, 2<sup>_N_</sup> - 1], we can iterate over all numbers in this range to get all of tyhe subsets of our set _S_.

Recall that 1 << _N_ == 2<sup>_N_</sup>

```java
for ( int subset = 0; subset < ( 1 << N ); subset++ )
{
    // This for-loop will find every subset of a set
}
```

Given a binary string, how can we check if the bit at index _k_ is a zero or one?

| 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |
|---|---|---|---|---|---|---|---|
| 1 | 0 | 0 | X | 0 | 1 | 1 | 1 |

Let's say we want to see if the bit at _k_ = 4 is zero or one, we will use the bitwise _AND_ and left-shifting to get our answer.

Since we are only worried about the bit at _k_ = 4, we can make a separate binary string that only has the bit at _k_ = 4 turned on.

| 7 | 6 | 5 | 4 | 3 | 2 | 1 | 0 |
|---|---|---|---|---|---|---|---|
| 1 | 0 | 0 | X | 0 | 1 | 1 | 1 |
| 0 | 0 | 0 | 1 | 0 | 0 | 0 | 0 |

We will then _AND_ these binary strings together, which will result in the binary string 000X0000

This will result in one of two things:
- if X is zero, then the decimal representation of the binary string will be zero
- if X is one, then the decimal representation of the binary string will be non-zero

```java
int leftShift = 1 << k;
int result = subset & leftShift;
if ( result == 0)
    // the bit at index k is zero
else
    // the bit at index k is one
```

We now know how to use the current value of subset to find which elements are in our subset:

```java
for ( int subset = 0; subset < ( 1 << N ); subset++ )
{
    for ( int k = 0; k < N; k++ )
    {
        int result = ( 1 << k ) & subset;
        if ( result != 0 )
            // the item at index k is in the current subset
    }
}
```

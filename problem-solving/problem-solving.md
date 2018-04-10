# Problem Solving

## What is problem solving?

Problem solving is the ability to reach a solution for a defined problem by, potentially, using mathematical or systematic operations (algorithms).

Not only an important skill to have in competitive programming, but also in the real world where problems arise everyday at jobs; being able to attack a problem quickly and efficiently is a skill desired by all companies.

The following steps should serve as a general timeline for solving a problem:
1. Define the problem
2. Come up with all possible cases
3. Implement the solution

In competitive programming, having a strong problem solving ability is just as important as having a strong programming background.
- Even if you’ve been programming your entire life, if you don’t know how to attack a problem, coding isn’t going to help you

Being able to identify edge cases and worst case scenarios for a problem are also important in competitive programming.
- If you just rely on the input/output given to you in the problem statement, your code might not handle ALL cases (which is necessary for an accepted solution)

## How do we break a problem down?

If we are given a problem to work on, what are the steps we must take in order to get an accepted solution? Here are the basic steps to follow:

1. Read the problem
2. Manually solve the problem
3. Take note of the constraints
4. Implement a solution
5. Optimize your solution
6. Handle edge cases

### Step 1: Read the problem

Yes, this is an obvious step in solving a problem, but it is a step that can cause turmoil in the long run.

Even if the background story behind a problem is silly or boring, you must read the problem all the way through to make sure that you didn’t miss any important details that are critical to solving the problem.

If you spent 45 minutes solving a problem with some algorithm, but realize later that the problem asked you to do something completely different, you don’t get that time back.

### Step 2: Manually solve the problem

You will be given input and output for a problem, so you should take a few minutes to grab pen and paper and figure out how they got their answer.

Writing out how they derived their output from the given input will give you a general algorithm for your solution; start by taking the steps you took and turning those into lines of code.

This will also stop you from jumping into the problem straight away and saying “how did they get that answer?” when debugging your code down the road.

### Step 3: Take note of the constraints

Step 3: Take note of the constraints
These are the size of the input variables that are given to you by the problem (e.g., `X <= 1,000,000`).

Knowing the sizes of the variables that are given can eliminate possible algorithms that you chose to use.

In order to know what is a good algorithm for a given constraint, you will need to understand **Big O Notation**.

For most problems, you can assume that your program will be able to process ~100,000,000 operations per second.

Additionally, most problems will have a time limit between 0.5 and 3 seconds, so the number of "steps" your algorithm takes cannot exceed ~300M.

### Step 4: Implement a solution

Use the algorithm/pseudocode you came up with when manually solving the test cases to implement a basic solution.

**Remember:** this algorithm you came up with does not need to be optimal; as long as you can come up with something that will get you the correct answer, you can worry about optimizing afterwards.

### Step 5: Optimize your solution

Making sure that your code can handle the constraints of the input is key to getting AC on a problem; if you don’t take this step into account, you won’t be able to get anything but **Time Limit Exceeded** on a problem.

Thinks of ways to eliminate unnecessary loops and operations within your code in order to trim off some time so that you can efficiently solve the problem.

### Step 6: Handle edge cases

Being able to come up with *tricky* test cases that the judge possibly came up with can save you trouble in the long run (as well as avoid a **Wrong Answer**). Here are some things to keep in mind:
- Did you handle the minimum and maximum cases?
- Did you handle potential negative numbers?
- Is the problem using **longs** instead of **ints**?
- Is there potential for your code to throw a **runtime error**? (e.g., array out of bounds, stack overflow, etc.)

## Conclusion

If you followed all of the previous steps, you can go ahead and submit your solution to the judge and see if you get AC.

If you happen to get **Wrong Answer**, **Time Limit Exceeded**, or **Runtime Error**, make sure that you read the problem correctly, handled all cases, have a fast enough algorithm, and that your program isn't crashing when being tested.

## Problems - Easy

- [Life, the Universe, and Everything](http://www.spoj.com/problems/TEST/)
- [To and Fro](http://www.spoj.com/problems/TOANDFRO/)
- [Adding Reversed Numbers](http://www.spoj.com/problems/ADDREV/)
- [Traversing Grid](http://www.spoj.com/problems/TRGRID/)
- [Anti-Blot System](http://www.spoj.com/problems/ABSYS/)
- [What's Next](http://www.spoj.com/problems/ACPC10A/)
- [Alpha Centauri Tennis](http://www.spoj.com/problems/ACT/)
- [Rectangles](http://www.spoj.com/problems/AE00/)
- [Christmas Play](http://www.spoj.com/problems/AMR10G/)
- [Complete the Series (Easy)](http://www.spoj.com/problems/AP2/)
- [Simple Arithmetics II](http://www.spoj.com/problems/ARITH2/)
- [Common Permutation](http://www.spoj.com/problems/CPRMT/)
- [A Very Easy Problem!](http://www.spoj.com/problems/EASYPROB/)
- [Pizza](http://www.spoj.com/problems/EGYPIZZA/)
- [Traveling Salesman](http://www.spoj.com/problems/FAKETSP/)
- [Happy Numbers I](http://www.spoj.com/problems/HPYNOS/)
- [Java vs C++](http://www.spoj.com/problems/JAVAC/)
- [Not a Triangle](http://www.spoj.com/problems/NOTATRI/)
- [Ambiguous Permutations](http://www.spoj.com/problems/PERMUT2/)

## Problems - Medium

- [Colours A, B, C, D](http://www.spoj.com/problems/ABCD/)
- [Aliens at the train](http://www.spoj.com/problems/ALIEN/)
- [Bye Bye Cakes](http://www.spoj.com/problems/BYECAKES/)
- [The Great Ball](http://www.spoj.com/problems/BYTESE2/)
- [Headshot](http://www.spoj.com/problems/HEADSHOT/)
- [Hotels Along the Croatian Coast](http://www.spoj.com/problems/HOTELS/)
- [Just Next !!!](http://www.spoj.com/problems/JNEXT/)
- [The Next Palindrome](http://www.spoj.com/problems/PALIN/)
- [Rat Attack](https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1301)
- [Do it Wrong, Get it Right](http://www.spoj.com/problems/DWRONG/)

## Problems - Hard

- [Hexagram](http://www.spoj.com/problems/HEXGRAM/)
- [Mickey Mouse Magic Trick v3](http://www.spoj.com/problems/MMMAGIC3/)
- [Beautiful Mountains](http://www.spoj.com/problems/MOUNTAIN/)
- [Nested Palindromes](http://www.spoj.com/problems/NESPALIN/)
- [Shuffles](http://www.spoj.com/problems/SHUFFLES/)
- [Walls](http://www.spoj.com/problems/WALLSPRO/)
- [Unhappy Numbers](http://www.spoj.com/problems/UNHAPPY/)

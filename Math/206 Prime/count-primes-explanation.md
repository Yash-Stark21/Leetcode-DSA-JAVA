# Count Primes — Sieve of Eratosthenes

## Problem

Given an integer `n`, return the number of prime numbers that are strictly less than `n`.

A prime number is a number greater than `1` that has no positive divisors other than `1` and itself.

### Example

```text
Input: n = 10
Output: 4
```

The prime numbers less than `10` are:

```text
2, 3, 5, 7
```

So the answer is `4`.

---

## Core Idea

The naive approach checks every number from `2` to `n - 1` and tests whether it is prime. That is too slow for large values of `n`.

Instead, we use the **Sieve of Eratosthenes**.

The first-principles idea is simple:

> A composite number must have at least one prime factor smaller than or equal to its square root.

So instead of checking each number independently, we mark numbers that are definitely composite.

---

## Java Solution

```java
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }

        boolean[] isComposite = new boolean[n];
        int count = 0;

        for (int i = 2; i < n; i++) {
            if (!isComposite[i]) {
                count++;

                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isComposite[j] = true;
                    }
                }
            }
        }

        return count;
    }
}
```

---

## Explanation

### Step 1: Handle small inputs

```java
if (n <= 2) {
    return 0;
}
```

There are no prime numbers strictly less than `2`.

For example:

```text
n = 0 → 0 primes
n = 1 → 0 primes
n = 2 → 0 primes less than 2
```

---

### Step 2: Track composite numbers

```java
boolean[] isComposite = new boolean[n];
```

The array `isComposite` tells us whether a number has already been proven to be composite.

By default, all values in a Java boolean array are `false`.

So initially, every number is treated as not composite.

---

### Step 3: Iterate through numbers from `2` to `n - 1`

```java
for (int i = 2; i < n; i++) {
```

We only count primes strictly less than `n`, so the loop stops at `i < n`.

---

### Step 4: If a number is not composite, it is prime

```java
if (!isComposite[i]) {
    count++;
```

If `isComposite[i]` is still `false`, no smaller prime has marked it as composite.

Therefore, `i` is prime.

So we increment the count.

---

### Step 5: Mark multiples of the prime as composite

```java
if ((long) i * i < n) {
    for (int j = i * i; j < n; j += i) {
        isComposite[j] = true;
    }
}
```

Once `i` is known to be prime, all multiples of `i` are composite.

We start marking from:

```text
i * i
```

not from:

```text
2 * i
```

Why?

Because smaller multiples have already been marked by smaller prime factors.

For example, when `i = 5`:

```text
2 × 5 = 10  already marked by 2
3 × 5 = 15  already marked by 3
4 × 5 = 20  already marked by 2
5 × 5 = 25  first new multiple to mark
```

So starting from `i * i` avoids redundant work.

---

## Why Use `(long) i * i`?

```java
if ((long) i * i < n)
```

This avoids integer overflow.

In Java, `int * int` produces an `int`. If `i` is large enough, `i * i` can overflow.

Casting `i` to `long` before multiplication makes the calculation safer.

---

## Dry Run

For `n = 10`:

Initial state:

```text
count = 0
isComposite = all false
```

### `i = 2`

`2` is not composite, so it is prime.

```text
count = 1
```

Mark multiples of `2`:

```text
4, 6, 8
```

### `i = 3`

`3` is not composite, so it is prime.

```text
count = 2
```

Mark multiples of `3` starting from `9`:

```text
9
```

### `i = 4`

`4` is composite, skip it.

### `i = 5`

`5` is not composite, so it is prime.

```text
count = 3
```

No need to mark multiples because `5 * 5 >= 10`.

### `i = 6`

`6` is composite, skip it.

### `i = 7`

`7` is not composite, so it is prime.

```text
count = 4
```

### `i = 8`

`8` is composite, skip it.

### `i = 9`

`9` is composite, skip it.

Final answer:

```text
4
```

---

## Complexity Analysis

### Time Complexity

```text
O(n log log n)
```

This is the standard time complexity of the Sieve of Eratosthenes.

The algorithm does not check divisibility for every number. Instead, it marks composite numbers efficiently using prime bases.

### Space Complexity

```text
O(n)
```

The boolean array stores one value for each number from `0` to `n - 1`.

---

## Key Takeaway

The high-leverage optimization is this:

> Do not test every number for primality. Mark what cannot be prime.

That is what makes the sieve fast.


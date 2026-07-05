# Prime Number Checker in Java

This document explains the Java program `PrimeNum`, which checks whether a given number is prime.

## What Is a Prime Number?

A **prime number** is a number that has exactly two positive divisors:

1. `1`
2. The number itself

Examples:

- `2` is prime because it is divisible only by `1` and `2`
- `7` is prime because it is divisible only by `1` and `7`
- `9` is not prime because it is divisible by `1`, `3`, and `9`

The important idea is this:

> We do not need to check whether a number is divisible by `1` and itself, because every number is automatically divisible by those two.

Instead, we check whether the number has any **extra divisor**.

---

## Correct Java Code

```java
import java.util.Scanner;

public class PrimeNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the num:");
        int n = sc.nextInt();

        boolean res = checkPrime(n);
        System.out.println(res);

        sc.close();
    }

    static boolean checkPrime(int n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        if (n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
```

---

## Program Structure

The program has two main parts:

1. `main()` method
2. `checkPrime()` method

---

## 1. The `main()` Method

```java
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.println("Enter the num:");
    int n = sc.nextInt();

    boolean res = checkPrime(n);
    System.out.println(res);

    sc.close();
}
```

### Explanation

```java
Scanner sc = new Scanner(System.in);
```

This creates a `Scanner` object to take input from the user.

```java
int n = sc.nextInt();
```

This reads an integer entered by the user.

```java
boolean res = checkPrime(n);
```

This calls the `checkPrime()` method and stores the result in `res`.

```java
System.out.println(res);
```

This prints:

- `true` if the number is prime
- `false` if the number is not prime

---

## 2. The `checkPrime()` Method

```java
static boolean checkPrime(int n)
```

This method receives an integer `n` and returns a boolean value:

- `true` if `n` is prime
- `false` if `n` is not prime

---

## Step-by-Step Logic

### Step 1: Numbers Less Than or Equal to 1 Are Not Prime

```java
if (n <= 1) {
    return false;
}
```

Prime numbers must be greater than `1`.

So these are not prime:

- `-5`
- `0`
- `1`

---

### Step 2: `2` Is Prime

```java
if (n == 2) {
    return true;
}
```

`2` is the smallest prime number.

It is also the only even prime number.

---

### Step 3: Even Numbers Greater Than 2 Are Not Prime

```java
if (n % 2 == 0) {
    return false;
}
```

If a number is divisible by `2` and is greater than `2`, it cannot be prime.

Examples:

- `4` is divisible by `2`
- `6` is divisible by `2`
- `10` is divisible by `2`

So all of them are not prime.

---

## Why the Loop Starts From 3

```java
for (int i = 3; i * i <= n; i += 2)
```

The loop starts from `3` because:

- `1` does not need to be checked
- `2` was already checked
- even numbers were already removed

So now we only check odd numbers:

```text
3, 5, 7, 9, 11, ...
```

That is why the loop uses:

```java
i += 2
```

This skips even numbers.

---

## Why We Use `i * i <= n`

```java
i * i <= n
```

This is the same idea as checking up to the square root of `n`.

For example, for `n = 49`:

```text
√49 = 7
```

So we only need to check divisors up to `7`.

Why?

Because if a number has a divisor larger than its square root, it must also have a matching divisor smaller than its square root.

Example:

```text
49 = 7 × 7
36 = 6 × 6
100 = 10 × 10
```

So checking beyond the square root is unnecessary.

---

## Example: Checking `7`

For `n = 7`:

```java
if (n <= 1)
```

`7 <= 1` is false.

```java
if (n == 2)
```

`7 == 2` is false.

```java
if (n % 2 == 0)
```

`7 % 2 == 0` is false because `7` is odd.

Now the loop starts:

```java
for (int i = 3; i * i <= n; i += 2)
```

For `n = 7`:

```text
i = 3
3 * 3 <= 7
9 <= 7
false
```

So the loop does not run.

The program reaches:

```java
return true;
```

So `7` is prime.

### Important Point

The code does not directly check:

```java
7 % 1 == 0
7 % 7 == 0
```

because those are already guaranteed.

Every number is divisible by `1` and itself.

The code only checks whether there is any extra divisor.

For `7`, there is no extra divisor, so it is prime.

---

## Example: Checking `9`

For `n = 9`:

The number is:

- greater than `1`
- not equal to `2`
- not even

So the loop runs:

```java
for (int i = 3; i * i <= 9; i += 2)
```

First value:

```text
i = 3
3 * 3 <= 9
9 <= 9
true
```

Now the condition is checked:

```java
if (9 % 3 == 0)
```

This is true because:

```text
9 ÷ 3 = 3
```

So the method returns:

```java
return false;
```

Therefore, `9` is not prime.

---

## Why We Return `false` Immediately

```java
if (n % i == 0) {
    return false;
}
```

A prime number must not have any extra divisor.

So the moment we find one extra divisor, we already know the number is not prime.

There is no need to continue checking.

---

## Why We Return `true` at the End

```java
return true;
```

This line runs only if:

- the number is greater than `1`
- the number is not `2`
- the number is not even
- no divisor was found in the loop

That means the number has no extra divisor.

So the number is prime.

---

## Common Mistake: Using `count == 1`

This logic is wrong:

```java
return count == 1;
```

Why?

Because a prime number should have **zero extra divisors**.

Example:

For `7`, there are no extra divisors:

```text
count = 0
```

So if we use:

```java
return count == 1;
```

`7` would incorrectly return `false`.

For `9`, the extra divisor is `3`:

```text
count = 1
```

So `9` would incorrectly return `true`.

That is why we should not use `count == 1`.

The better approach is:

```java
return false;
```

as soon as we find a divisor.

If no divisor is found, return:

```java
return true;
```

---

## Final Logic in Simple Words

A number is prime if:

1. It is greater than `1`
2. It has no divisor other than `1` and itself

The code checks for extra divisors.

If an extra divisor exists, the number is not prime.

If no extra divisor exists, the number is prime.

---

## Sample Outputs

### Input

```text
7
```

### Output

```text
true
```

---

### Input

```text
9
```

### Output

```text
false
```

---

### Input

```text
2
```

### Output

```text
true
```

---

### Input

```text
1
```

### Output

```text
false
```

---

## Time Complexity

The loop checks numbers only up to the square root of `n`.

So the time complexity is:

```text
O(√n)
```

This is much faster than checking all numbers from `2` to `n - 1`.

---

## First Principle

A prime number has exactly two positive divisors:

```text
1 and itself
```

Since every number is already divisible by `1` and itself, the only real question is:

> Does it have any extra divisor?

If yes, it is not prime.

If no, it is prime.

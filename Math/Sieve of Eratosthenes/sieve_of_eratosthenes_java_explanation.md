# Sieve of Eratosthenes in Java

## 1. What is the Sieve of Eratosthenes?

The **Sieve of Eratosthenes** is an efficient algorithm used to find all prime numbers from `2` to `n`.

Instead of checking every number one by one, it starts by assuming every number is prime and then removes the multiples of each prime number.

---

## 2. What is a Prime Number?

A **prime number** is a number greater than `1` that has exactly two factors:

- `1`
- itself

Examples:

```text
2, 3, 5, 7, 11, 13, 17, 19
```

Numbers like `4`, `6`, `8`, `9`, and `10` are not prime because they have more than two factors.

---

## 3. Core Idea of the Algorithm

The sieve works like this:

1. Create a boolean array `isPrime`.
2. Mark all numbers from `2` to `n` as `true`.
3. Start from `2`.
4. If the current number is still marked as prime, mark all of its multiples as not prime.
5. Repeat this process until `sqrt(n)`.
6. All numbers still marked as prime are prime numbers.

---

## 4. Optimized Java Implementation

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SieveOfEratosthenes {

    /*
     * Returns all prime numbers from 2 to n.
     *
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static List<Integer> findPrimes(int n) {

        // This list stores the final prime numbers
        List<Integer> primes = new ArrayList<>();

        // Numbers less than 2 are not prime
        if (n < 2) {
            return primes;
        }

        /*
         * isPrime[i] tells whether number i is prime.
         *
         * true  -> prime
         * false -> not prime
         */
        boolean[] isPrime = new boolean[n + 1];

        // Initially assume every number from 2 to n is prime
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        /*
         * We only need to check up to sqrt(n).
         *
         * Instead of writing i <= Math.sqrt(n),
         * we write i * i <= n to avoid repeated square root calculation.
         */
        for (int i = 2; i * i <= n; i++) {

            /*
             * If i is still marked as prime,
             * then mark all multiples of i as non-prime.
             */
            if (isPrime[i]) {

                /*
                 * Start from i * i instead of 2 * i.
                 *
                 * Reason:
                 * Smaller multiples like 2*i, 3*i, 4*i
                 * would already have been marked by smaller numbers.
                 */
                for (int multiple = i * i; multiple <= n; multiple += i) {
                    isPrime[multiple] = false;
                }
            }
        }

        // Collect all numbers still marked as prime
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();

        List<Integer> primes = findPrimes(n);

        System.out.println("Prime numbers from 2 to " + n + ":");

        for (int prime : primes) {
            System.out.print(prime + " ");
        }

        scanner.close();
    }
}
```

---

## 5. Dry Run Example

Let:

```text
n = 20
```

Initially, mark all numbers from `2` to `20` as prime:

```text
2  3  4  5  6  7  8  9  10  11  12  13  14  15  16  17  18  19  20
```

### Step 1: Start with `2`

`2` is prime.

Mark multiples of `2` as non-prime:

```text
4, 6, 8, 10, 12, 14, 16, 18, 20
```

Remaining possible primes:

```text
2, 3, 5, 7, 9, 11, 13, 15, 17, 19
```

---

### Step 2: Move to `3`

`3` is prime.

Mark multiples of `3` as non-prime.

Start from:

```text
3 * 3 = 9
```

Mark:

```text
9, 12, 15, 18
```

Some of them may already be marked, and that is fine.

Remaining possible primes:

```text
2, 3, 5, 7, 11, 13, 17, 19
```

---

### Step 3: Move to `4`

`4` is already marked as non-prime, so skip it.

---

### Step 4: Stop condition

The loop runs while:

```java
i * i <= n
```

For `n = 20`:

```text
4 * 4 = 16 <= 20
5 * 5 = 25 > 20
```

So we stop before processing `5`.

Final prime numbers:

```text
2, 3, 5, 7, 11, 13, 17, 19
```

---

## 6. Why Do We Check Only Until `sqrt(n)`?

A composite number always has at least one factor less than or equal to its square root.

Example:

```text
36 = 6 * 6
40 = 5 * 8
49 = 7 * 7
```

If a number has a factor greater than `sqrt(n)`, it must also have another factor smaller than `sqrt(n)`.

So after checking up to `sqrt(n)`, all composite numbers have already been marked.

That is why this condition is enough:

```java
i * i <= n
```

---

## 7. Why Start Marking from `i * i`?

Suppose `i = 5`.

Normally, multiples of `5` are:

```text
10, 15, 20, 25, 30, 35, ...
```

But:

```text
10 = 2 * 5
15 = 3 * 5
20 = 4 * 5
```

These smaller multiples were already handled by `2`, `3`, or `4`.

So we do not need to mark them again.

We can safely start from:

```text
5 * 5 = 25
```

This reduces repeated work and improves efficiency.

---

## 8. Time Complexity

The time complexity of the Sieve of Eratosthenes is:

```text
O(n log log n)
```

This looks complicated, but the idea is simple.

The algorithm marks multiples of prime numbers:

```text
Multiples of 2
Multiples of 3
Multiples of 5
Multiples of 7
Multiples of 11
...
```

For each prime `p`, it marks about:

```text
n / p
```

numbers.

So the total work is roughly:

```text
n/2 + n/3 + n/5 + n/7 + n/11 + ...
```

Factor out `n`:

```text
n * (1/2 + 1/3 + 1/5 + 1/7 + 1/11 + ...)
```

The sum of reciprocals of prime numbers grows like:

```text
log log n
```

So the final complexity becomes:

```text
O(n log log n)
```

---

## 9. Space Complexity

The space complexity is:

```text
O(n)
```

Reason:

We create a boolean array of size:

```java
n + 1
```

So the memory grows directly with `n`.

---

## 10. Key Interview Points

Remember these points clearly:

- Sieve is used to find all primes from `2` to `n`.
- It is faster than checking each number individually.
- Start marking multiples from `i * i`, not `2 * i`.
- Loop only while `i * i <= n`.
- Time complexity is `O(n log log n)`.
- Space complexity is `O(n)`.

---

## 11. Final Mental Model

Think of the sieve like this:

```text
Do not ask every number, "Are you prime?"

Instead:

Let prime numbers remove their multiples.
Whatever survives is prime.
```

That is the whole logic.

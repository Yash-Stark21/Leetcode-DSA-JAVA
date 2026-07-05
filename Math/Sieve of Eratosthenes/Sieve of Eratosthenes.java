import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SieveOfEratosthenes {

    /*
     * Returns all prime numbers from 2 to n using Sieve of Eratosthenes.
     *
     * Time Complexity: O(n log log n)
     * Space Complexity: O(n)
     */
    public static List<Integer> findPrimes(int n) {

        // List to store all prime numbers
        List<Integer> primes = new ArrayList<>();

        // Numbers less than 2 are not prime
        if (n < 2) {
            return primes;
        }

        /*
         * isPrime[i] tells whether number i is prime or not.
         *
         * true  -> assume prime initially
         * false -> not prime
         */
        boolean[] isPrime = new boolean[n + 1];

        // Initially mark every number from 2 to n as prime
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        /*
         * We only need to check numbers up to sqrt(n).
         *
         * Reason:
         * If a number has a factor greater than sqrt(n),
         * then it must also have a smaller factor less than sqrt(n).
         */
        for (int i = 2; i * i <= n; i++) {

            /*
             * If isPrime[i] is still true,
             * then i is a prime number.
             */
            if (isPrime[i]) {

                /*
                 * Mark all multiples of i as non-prime.
                 *
                 * Start from i * i instead of 2 * i because:
                 * smaller multiples like 2*i, 3*i, ..., (i-1)*i
                 * would have already been marked by smaller prime numbers.
                 */
                for (int multiple = i * i; multiple <= n; multiple += i) {
                    isPrime[multiple] = false;
                }
            }
        }

        // Collect all numbers that are still marked as prime
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
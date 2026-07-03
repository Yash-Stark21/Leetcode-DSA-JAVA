# 1. Two Sum

## Problem

Given an integer array `nums` and an integer `target`, return the indices of the two numbers such that they add up to `target`.

Each input has exactly one solution, and the same element cannot be used twice.

The answer can be returned in any order.

## Examples

### Example 1

```text
Input: nums = [2, 7, 11, 15], target = 9
Output: [0, 1]
```

Explanation:

```text
nums[0] + nums[1] = 2 + 7 = 9
```

### Example 2

```text
Input: nums = [3, 2, 4], target = 6
Output: [1, 2]
```

Explanation:

```text
nums[1] + nums[2] = 2 + 4 = 6
```

### Example 3

```text
Input: nums = [3, 3], target = 6
Output: [0, 1]
```

Explanation:

```text
nums[0] + nums[1] = 3 + 3 = 6
```

## Constraints

```text
2 <= nums.length <= 10^4
-10^9 <= nums[i] <= 10^9
-10^9 <= target <= 10^9
Only one valid answer exists.
```

## Approach: HashMap

### Core Idea

For every number, calculate the number needed to reach the target.

```text
needed = target - currentNumber
```

If `needed` already exists in the HashMap, then we have found the answer.

Otherwise, store the current number with its index.

## Why This Works

For two numbers to form the target:

```text
a + b = target
```

If the current number is `a`, then the other number must be:

```text
b = target - a
```

So instead of checking every pair, we remember the numbers we have already seen.

This allows us to find the answer in one pass.

## Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];

            if (seen.containsKey(needed)) {
                return new int[] { seen.get(needed), i };
            }

            seen.put(nums[i], i);
        }

        return new int[] {};
    }
}
```

## Dry Run

Input:

```text
nums = [2, 7, 11, 15], target = 9
```

Initial state:

```text
seen = {}
```

### Step 1

```text
i = 0
nums[i] = 2
needed = 9 - 2 = 7
```

`7` is not in the map.

Store `2` with index `0`.

```text
seen = {2: 0}
```

### Step 2

```text
i = 1
nums[i] = 7
needed = 9 - 7 = 2
```

`2` exists in the map at index `0`.

Return:

```text
[0, 1]
```

## Complexity Analysis

### Time Complexity

```text
O(n)
```

We scan the array once.

### Space Complexity

```text
O(n)
```

In the worst case, we store almost all numbers in the HashMap.

## Why Not Brute Force?

A brute force solution checks every possible pair.

```text
nums[i] + nums[j] == target
```

That takes:

```text
O(n^2)
```

The HashMap approach reduces this to:

```text
O(n)
```

## Key Pattern

Use this pattern when the problem asks for:

- Finding two values that form a target
- Returning indices instead of values
- Checking whether a complement exists
- Reducing nested loops using a HashMap

## GitHub Path Recommendation

Recommended notes file path:

```text
leetcode-solutions/java/hash-map/0001-two-sum.md
```

Recommended solution file path:

```text
leetcode-solutions/java/hash-map/0001-two-sum.java
```

Alternative topic folder:

```text
leetcode-solutions/java/arrays/0001-two-sum.java
```

The better folder is `hash-map` because the optimized solution depends on fast complement lookup.

## README Entry

```markdown
| # | Problem | Difficulty | Topic | Solution | Notes |
|---|---------|------------|-------|----------|-------|
| 1 | Two Sum | Easy | HashMap, Array | [Java](java/hash-map/0001-two-sum.java) | [Notes](java/hash-map/0001-two-sum.md) |
```

## First Principle

The problem is not about comparing every pair.

The irreducible truth is:

```text
currentNumber + neededNumber = target
```

So for every current number, the only useful question is:

```text
Have I already seen target - currentNumber?
```

That single question turns the problem from pair-search into lookup.

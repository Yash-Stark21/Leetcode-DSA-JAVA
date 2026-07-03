# 560. Subarray Sum Equals K

## Problem

Given an integer array `nums` and an integer `k`, return the total number of contiguous subarrays whose sum equals `k`.

A subarray is a contiguous non-empty sequence of elements within an array.

## Examples

### Example 1

```text
Input: nums = [1, 1, 1], k = 2
Output: 2
```

Explanation:

```text
[1, 1] at index 0 to 1
[1, 1] at index 1 to 2
```

### Example 2

```text
Input: nums = [1, 2, 3], k = 3
Output: 2
```

Explanation:

```text
[1, 2]
[3]
```

## Constraints

```text
1 <= nums.length <= 2 * 10^4
-1000 <= nums[i] <= 1000
-10^7 <= k <= 10^7
```

## Approach: Prefix Sum + HashMap

### Core Idea

For every index, calculate the current prefix sum.

If:

```text
currentPrefixSum - previousPrefixSum = k
```

Then:

```text
previousPrefixSum = currentPrefixSum - k
```

So, for every current prefix sum, we check how many previous prefix sums equal:

```text
currentPrefixSum - k
```

Each such previous prefix sum represents one valid subarray ending at the current index.

## Why This Works

Assume:

```text
prefixSum[j] = sum of nums[0...j]
prefixSum[i] = sum of nums[0...i]
```

The sum of subarray `nums[j + 1...i]` is:

```text
prefixSum[i] - prefixSum[j]
```

We want this to equal `k`:

```text
prefixSum[i] - prefixSum[j] = k
```

Rearranging:

```text
prefixSum[j] = prefixSum[i] - k
```

Therefore, while scanning the array, we store the frequency of prefix sums seen so far.

## Java Solution

```java
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {
            prefixSum += num;

            count += prefixCount.getOrDefault(prefixSum - k, 0);

            prefixCount.put(
                prefixSum,
                prefixCount.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }
}
```

## Dry Run

Input:

```text
nums = [1, 1, 1], k = 2
```

Initial state:

```text
prefixCount = {0: 1}
prefixSum = 0
count = 0
```

### Step 1

```text
num = 1
prefixSum = 1
required = prefixSum - k = 1 - 2 = -1
```

`-1` is not present in the map.

```text
count = 0
prefixCount = {0: 1, 1: 1}
```

### Step 2

```text
num = 1
prefixSum = 2
required = prefixSum - k = 2 - 2 = 0
```

`0` is present once in the map.

```text
count = 1
prefixCount = {0: 1, 1: 1, 2: 1}
```

### Step 3

```text
num = 1
prefixSum = 3
required = prefixSum - k = 3 - 2 = 1
```

`1` is present once in the map.

```text
count = 2
prefixCount = {0: 1, 1: 1, 2: 1, 3: 1}
```

Final answer:

```text
2
```

## Complexity Analysis

### Time Complexity

```text
O(n)
```

We iterate through the array once.

### Space Complexity

```text
O(n)
```

In the worst case, all prefix sums are different, so the HashMap stores up to `n + 1` prefix sums.

## Why Not Sliding Window?

Sliding window does not work reliably here because the array can contain negative numbers.

Example:

```text
nums = [1, -1, 1, 1], k = 2
```

With negative numbers, expanding or shrinking the window does not guarantee that the sum moves in only one direction.

So the correct approach is prefix sum with a frequency map.

## Key Pattern

Use this pattern when the problem asks for:

- Number of subarrays with sum equal to `k`
- Contiguous subarray sum problems
- Arrays containing negative numbers
- Counting how many previous states can form the target

## GitHub Path Recommendation

Recommended file path:

```text
leetcode-solutions/java/prefix-sum/0560-subarray-sum-equals-k.md
```

Solution file path:

```text
leetcode-solutions/java/prefix-sum/0560-subarray-sum-equals-k.java
```

## README Entry

```markdown
| # | Problem | Difficulty | Topic | Solution | Notes |
|---|---------|------------|-------|----------|-------|
| 560 | Subarray Sum Equals K | Medium | Prefix Sum, HashMap | [Java](java/prefix-sum/0560-subarray-sum-equals-k.java) | [Notes](java/prefix-sum/0560-subarray-sum-equals-k.md) |
```

## First Principle

A subarray sum can be transformed into the difference between two prefix sums.

The problem is not really about checking every subarray.  
It is about counting how many earlier prefix sums can complete the current prefix sum into the target `k`.

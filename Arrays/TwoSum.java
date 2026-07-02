import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int required = target - nums[i];

            if (seen.containsKey(required)) {
                return new int[] { seen.get(required), i };
            }

            seen.put(nums[i], i);
        }

        return new int[] {};
    }
}
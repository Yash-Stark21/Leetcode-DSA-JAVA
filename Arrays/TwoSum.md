# Two Sum

## Pattern
HashMap / Complement Lookup

## Problem Idea
Given an array and a target, find two indices whose values add up to the target.

## Core Logic
For every number, calculate the required complement:
target - currentNumber

If the complement already exists in the map, we found the answer.

## Complexity
Time: O(n)
Space: O(n)

## Mistakes to Avoid
- Do not use the same element twice.
- Store index, not just value.
- Check complement before inserting current number.
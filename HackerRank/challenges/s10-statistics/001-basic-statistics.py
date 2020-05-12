"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 0)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-basic-statistics
"""

import math


def calculate_mean(arr):
    return sum(arr) / len(arr)


def calculate_median(arr):
    n = len(arr) - 1
    if n <= 0:
        return 0
    return (arr[math.floor(n / 2)] + arr[math.ceil(n / 2)]) / 2


def calculate_mode(arr):
    occur = 1
    max_occur = occur
    current_mode = arr[0]
    mode = current_mode
    for n in range(1, len(arr)):
        if arr[n] == current_mode:
            occur += 1
        else:
            if occur > max_occur:
                mode = current_mode
                max_occur = occur
            current_mode = arr[n]
            occur = 1

    if occur > max_occur:
        mode = current_mode
    return mode


length = int(input())
arr = [int(a) for a in input().split()]

if not length or length == 0:
    mean = median = mode = 0
else:
    arr.sort()
    mean = calculate_mean(arr)
    median = calculate_median(arr)
    mode = calculate_mode(arr)

print('{:.1f}'.format(mean))
print('{:.1f}'.format(median))
print('{}'.format(mode))


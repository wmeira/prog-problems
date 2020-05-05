"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 1)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-standard-deviation/
"""

import os
import math

def calc_mean(arr):
    return sum(arr) / len(arr)

def calc_std(arr):
    mean = calc_mean(arr)
    return math.sqrt(sum([(n - mean)**2 for n in arr]) / len(arr))


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())
    arr = list(map(int, input().rstrip().split()))

    std = calc_std(arr)

    fptr.write('{:.1f}\n'.format(std))
    fptr.close()

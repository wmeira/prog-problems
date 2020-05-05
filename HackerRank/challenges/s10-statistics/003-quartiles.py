"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 1)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-quartiles/
"""

import os
import math

def median(arr):
    n = len(arr) - 1
    if n <= 0:
        return 0
    return (arr[math.floor(n/2)] + arr[math.ceil(n/2)])/2

def quartiles(n, arr):
    q1 = median(arr[:n//2])
    q2 = median(arr)
    q3 = median(arr[math.ceil(n/2):])
    return q1, q2, q3


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())
    arr = list(map(int, input().rstrip().split()))

    arr.sort()

    q1, q2, q3 = quartiles(n, arr)

    fptr.write(str(int(q1)) + '\n')
    fptr.write(str(int(q2)) + '\n')
    fptr.write(str(int(q3)) + '\n')

    fptr.close()

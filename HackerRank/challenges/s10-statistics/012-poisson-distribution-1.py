"""
Author: William Meira
Date: 2020-05-09
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 5)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-poisson-distribution-1
"""

# Enter your code here. Read input from STDIN. Print output to STDOUT

import math


def poisson(k, mean):
    p = (mean ** k) * (math.e ** -mean) / math.factorial(k)
    return p


mean = float(input())
k = int(input())

ans = poisson(k, mean)

print(round(ans, 3))
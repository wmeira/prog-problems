"""
Author: William Meira
Date: 2020-05-10
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 6)
Link: https://www.hackerrank.com/challenges/s10-the-central-limit-theorem-1
"""

import math


def cumul_normal_dist(x, mean, std):
    z = (x - mean) / (std * math.sqrt(2))
    return 0.5 * (1 + math.erf(z))  # built-in erf function


def clt(sample_sum, n, mean, std):
    return cumul_normal_dist(sample_sum, mean * n, math.sqrt(n) * std)


# max_weight = int(input())
# boxes = int(input())
# mean = float(input())
# std = float(input())

max_weight, boxes, mean, std = 9800, 49, 205, 15

ans = clt(max_weight, boxes, mean, std)

print(round(ans, 4))
"""
Author: William Meira
Date: 2020-05-10
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 6)
Link: https://www.hackerrank.com/challenges/s10-the-central-limit-theorem-2
"""

import math


def cumul_normal_dist(x, mean, std):
    z = (x - mean) / (std * math.sqrt(2))
    return 0.5 * (1 + math.erf(z))  # built-in erf function


def clt(sample_sum, n, mean, std):
    return cumul_normal_dist(sample_sum, mean * n, math.sqrt(n) * std)


# tickets = int(input())
# students = int(input())
# mean = float(input())
# std = float(input())

tickets, students, mean, std = 250, 100, 2.4, 2.0

ans = clt(tickets, students, mean, std)

print(round(ans, 4))
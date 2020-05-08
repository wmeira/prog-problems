"""
Author: William Meira
Date: 2020-05-08
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 4)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-geometric-distribution-1
"""


def geometric_dist(n, p):
    return (1 - p)**(n - 1) * p


num, den = list(map(int, input().split(" ")))
n = int(input())

p = num / den

print(round(geometric_dist(n, p), 3))
"""
Author: William Meira
Date: 2020-05-09
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 5)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-normal-distribution-1/problem
"""

import math


def integrate(func, a, b, n=10000):
    if a >= b:
        sign, a, b = - 1, b, a
    else:
        sign = 1
    step = 1 / n
    n = int(abs(b - a) * n)
    sq = 0.0
    for _ in range(0, n):
        sq += step * func(a)
        a += step
    return sign * sq


def erf(z):
    return (2 / math.sqrt(math.pi)) * integrate(lambda x: math.exp(-x**2), 0, z)


def cumul_normal_dist(x, mean, std):
    z = (x - mean) / (std * math.sqrt(2))
    # return 0.5 * (1 + math.erf(z))  # built-in erf function
    return 0.5 * (1 + erf(z))


mean, std = list(map(float, input().split(" ")))
a = float(input())
b, c = list(map(float, input().split(" ")))  # between b and c

# mean = 20; std = 2; a = 19.5; b = 20; c = 22

ans_a = cumul_normal_dist(a, mean, std)
ans_b = cumul_normal_dist(c, mean, std) - cumul_normal_dist(b, mean, std)
print(round(ans_a, 3))
print(round(ans_b, 3))
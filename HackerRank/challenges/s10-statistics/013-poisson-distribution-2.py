"""
Author: William Meira
Date: 2020-05-09
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 5)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-poisson-distribution-2
"""

a_mean, b_mean = list(map(float, input().rstrip().split(" ")))

# expectation of X^2 is
e_a = a_mean + a_mean * a_mean
cost_a = 160 + 40 * e_a

e_b = b_mean + b_mean * b_mean
cost_b = 128 + 40 * e_b

print(round(cost_a, 3))
print(round(cost_b, 3))
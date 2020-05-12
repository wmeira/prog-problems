"""
Author: William Meira
Date: 2020-05-11
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 6)
Link: https://www.hackerrank.com/challenges/s10-pearson-correlation-coefficient
"""


import math


def corr_pearson(n, X, Y):
    mean_x = sum(X) / n
    mean_y = sum(Y) / n
    std_x = math.sqrt(sum([(n - mean_x)**2 for n in X]) / n)
    std_y = math.sqrt(sum([(n - mean_y)**2 for n in Y]) / n)
    cov = sum([(X[i] - mean_x) * (Y[i] - mean_y) for i in range(0, n)]) / n
    return cov / (std_x * std_y)


n = int(input())
X = list(map(float, input().rstrip().split(" ")))
Y = list(map(float, input().rstrip().split(" ")))

# n = 10
# X = [10, 9.8, 8, 7.8, 7.7, 7, 6, 5, 4, 2]
# Y = [200, 44, 32, 24, 22, 17, 15, 12, 8, 4]

ans = corr_pearson(n, X, Y)

print(round(ans, 3))
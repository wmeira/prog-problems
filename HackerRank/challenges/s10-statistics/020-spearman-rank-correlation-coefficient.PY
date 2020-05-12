"""
Author: William Meira
Date: 2020-05-11
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 7)
Link: https://www.hackerrank.com/challenges/s10-spearman-rank-correlation-coefficient
"""

import math


def corr_pearson(n, X, Y):
    mean_x = sum(X) / n
    mean_y = sum(Y) / n
    std_x = math.sqrt(sum([(n - mean_x)**2 for n in X]) / n)
    std_y = math.sqrt(sum([(n - mean_y)**2 for n in Y]) / n)
    cov = sum([(X[i] - mean_x) * (Y[i] - mean_y) for i in range(0, n)]) / n
    return cov / (std_x * std_y)


# n = int(input())
# x = list(map(float, input().rstrip().split(" ")))
# y = list(map(float, input().rstrip().split(" ")))

n = 10
x = [10, 9.8, 8, 7.8, 7.7, 1.7, 6, 5, 1.4, 2]
y = [200, 44, 32, 24, 22, 17, 15, 12, 8, 4]


sorted_x = sorted(x)
sorted_y = sorted(y)

rank_x = [sorted_x.index(i) for i in x]
rank_y = [sorted_y.index(i) for i in y]

d2 = [(rank_x[i] - rank_y[i])**2 for i in range(0, n)]
r = 1 - 6 * sum(d2) / (n * (n * n - 1))

print(round(r, 3))
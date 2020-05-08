"""
Author: William Meira
Date: 2020-05-08
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 4)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-binomial-distribution-1
"""


import math


def combinations(n, x):
    # return math.prod(range(n + 1 - x, n + 1)) / math.factorial(x)  # less product op.
    return math.factorial(n) / (math.factorial(x) * math.factorial(n - x))


def binomial_dist(trials, success, p):
    return combinations(trials, success) * p**success * (1 - p)**(trials - success)


def binomial_curve(trials, p):
    curve = []
    for s in range(0, trials + 1):
        k = binomial_dist(trials, s, p)
        print(k)
        curve.append((s, k))
    return curve


if __name__ == '__main__':
    trials = 6
    success = [3, 4, 5, 6]
    boys = 1.09  # hardcoded
    girls = 1  # hardcoded
    p = boys / (girls + boys)

    result = sum(binomial_dist(trials, s, p) for s in success)

    print('{:.3f}'.format(result) + '\n')

    # import matplotlib.pyplot as plt
    # curve = binomial_curve(trials, p)
    # plt.plot(*zip(*curve))
    # plt.show()


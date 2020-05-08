"""
Author: William Meira
Date: 2020-05-08
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 4)
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-binomial-distribution-2
"""


# Enter your code here. Read input from STDIN. Print output to STDOUT

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
        curve.append((s, k))
    return curve


if __name__ == '__main__':

    p, trials = list(map(int, input().split(" ")))
    p = p / 100

    # 1 - prob. that a batch of 10 pistons will contain no more than 2 rejects.
    success_1 = range(0, 3)
    result_1 = round(sum(binomial_dist(trials, s, p) for s in success_1), 3)
    print('{:.3f} '.format(result_1))

    # 2 - prob. that a batch of 10 pistons will contain at least 2 rejects.
    success_2 = range(2, trials + 1)
    result_2 = round(sum(binomial_dist(trials, s, p) for s in success_2), 3)
    print('{:.3f}'.format(result_2))

    # import matplotlib.pyplot as plt
    # curve = binomial_curve(trials, p)
    # plt.plot(*zip(*curve))
    # plt.show()

"""
Author: William Meira
Date: 2020-05-14
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 9)
Level: Medium
Link: https://www.hackerrank.com/challenges/s10-multiple-linear-regression/
"""


def mult(x, y):
    # if len(x) != len(y[0]):
    #     print(len(x))
    #     print(len(y[0]))
    #     raise ValueError("Multiplication does not result in a square matrix")

    result = [[0] * len(y[0]) for _ in range(len(x))]
    for row in range(len(x)):
        for col in range(len(y[0])):
            for k in range(len(y)):
                result[row][col] += x[row][k] * y[k][col]
    return result


def get_subarray(x, p, q, n):
    """
    params
    :x is the array
    :p is the row to skip
    :q is the column to skip
    :n is the len(x)
    """
    return [[x[row][col] for col in range(n) if col != q]
                         for row in range(n) if row != p]


def determinant(x, n):
    # assume it is a square matrix

    # Base case: single element
    if n == 1:
        return x[0][0]
    elif n == 2:
        return x[0][0] * x[1][1] - x[1][0] * x[0][1]

    determ = 0
    sign = 1  # sign multiplier

    for f in range(n):
        subarr = get_subarray(x, 0, f, n)
        determ += sign * x[0][f] * determinant(subarr, n - 1)
        sign = -sign  # terms are to be added with alternate sign
    return determ


def adjoint(x, n):
    adj = [[0] * n for _ in range(n)]

    if n == 1:
        adj[0][0] = 1
    else:
        for i in range(n):
            for j in range(n):
                # Get cofactor of x[i][j]
                subarr = get_subarray(x, i, j, n)

                # sign of adj[j][i] positive if sum of row
                # and column indexes is even.
                sign = 1 if ((i + j) % 2 == 0) else -1

                # Interchanging rows and columns to get the
                # transpose of the cofactor matrix
                adj[j][i] = sign * determinant(subarr, n - 1)
    return adj


def inverse(x):
    n = len(x)
    det = determinant(x, n)
    if det == 0:
        raise ValueError("Singular Matrix, can't find its inverse")

    # Find adjoint
    adj = adjoint(x, n)

    inverse = [[0] * n for _ in range(n)]
    # find Inverse using formula "inverse(A) = adj(A)/det(A)"
    for i in range(n):
        for j in range(n):
            inverse[i][j] = adj[i][j] / det
    return inverse


# INPUT
m, n = list(map(int, input().rstrip().split(" ")))

x = []
y = []
for i in range(n):
    data = list(map(float, input().rstrip().split(" ")))
    x.append([1] + data[:m])
    y.append(data[m:])

q = int(input())
d = []
for i in range(q):
    d.append(list(map(float, input().rstrip().split(" "))))

# -------------------------------------------------------------
# Apparently my implementation is too slow for biggest test... Numpy is faster
# The answer is the same, mine just take a long time to solve
# Oh that o(n³) matrix muiltiplication

# xt = [[x[j][i] for j in range(len(x))] for i in range(len(x[0]))]
# xtx = mult(xt, x)
# xtx_i = inverse(xtx)
# b = mult(xtx_i, mult(xt, y))
# -------------------------------------------------------------

import numpy as np

x = np.array(x)
y = np.array(y)
b = np.dot(np.linalg.inv(np.dot(x.T, x)), np.dot(x.T, y))

# print(b)

for case in range(q):
    print(b[0][0]
          + sum([d[case][i] * b[i + 1][0] for i in range(len(d[case]))]))



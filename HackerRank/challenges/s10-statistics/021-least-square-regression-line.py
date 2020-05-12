"""
Author: William Meira
Date: 2020-05-12
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 8)
Link: https://www.hackerrank.com/challenges/s10-least-square-regression-line
"""

# from sklearn import linear_model
# import numpy as np

# x = []
# y = []
# for _ in range(0, 5):
#     d = list(map(float, input().rstrip().split(" ")))
#     x.append(d[0])
#     y.append(d[1])

# x = np.asarray(x).reshape(-1, 1)

# lm = linear_model.LinearRegression()
# lm.fit(x, y)

# a = lm.intercept_
# b = lm.coef_[0]

# ans = a + b * 80

# print(round(ans, 3))



n = 5
x = []
y = []
for _ in range(0, n):
    d = list(map(float, input().rstrip().split(" ")))
    x.append(d[0])
    y.append(d[1])


sum_x = sum(x)
sum_y = sum(y)
mean_x = sum_x / n
mean_y = sum_y / n

sum_x2 = sum([i * i for i in x])
xy = sum([x[i] * y[i] for i in range(0, n)])

b = (n * xy - sum_x * sum_y) / (n * sum_x2 - sum_x * sum_x)
a = mean_y - b * mean_x

ans = a + b * 80

print(round(ans, 3))
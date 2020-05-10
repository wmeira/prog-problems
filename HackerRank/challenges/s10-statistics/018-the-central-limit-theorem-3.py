"""
Author: William Meira
Date: 2020-05-10
Platform: HackerRank
Type: Challenge - 10 Days of Statistics (day 6)
Link: https://www.hackerrank.com/challenges/s10-the-central-limit-theorem-3
"""


import math


n, mean, std, p, z = 100, 500, 80, 0.95, 1.96

# sample mean = population mean
# sample standard deviation = population std / sqrt(n)
# http://www.stat.yale.edu/Courses/1997-98/101/sampmn.htm

a = mean - std / math.sqrt(n) * z
b = mean + std / math.sqrt(n) * z

print(round(a, 2))
print(round(b, 2))

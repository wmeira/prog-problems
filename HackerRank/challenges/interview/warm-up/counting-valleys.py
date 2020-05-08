"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Interview
Level: Easy
Link: https://www.hackerrank.com/challenges/counting-valleys/
"""

# !/bin/python3

import os


def counting_valleys(n, s):
    level = 0
    valleys = 0
    for step in s:
        if step == 'U':
            if level == -1:
                valleys += 1
            level += 1
        else:  # D
            level -= 1
    return valleys


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    s = input()

    result = counting_valleys(n, s)

    fptr.write(str(result) + '\n')

    fptr.close()

"""
Author: William Meira
Date: 2020-05-07
Platform: HackerRank
Type: Interview
Level: Easy
Link: https://www.hackerrank.com/challenges/repeated-string/
"""

#!/bin/python3

import os


LETTER = 'a'


def repeated_string(s, n):
    length = len(s)
    remainder = n % length
    return n//length*s.count(LETTER) + s[:remainder].count(LETTER)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()
    n = int(input())

    result = repeated_string(s, n)
    fptr.write(str(result) + '\n')

    fptr.close()

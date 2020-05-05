"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Interview
Level: Easy
Link: https://www.hackerrank.com/challenges/jumping-on-the-clouds/
"""

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the jumpingOnClouds function below.
def jumping_on_clouds(n, c):
    jumps = 0
    pos = 1
    while pos < n:
        if pos+1 < n and c[pos+1] == 0:
            pos += 2
        else:
            pos += 1
        jumps += 1
    return jumps



if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    c = list(map(int, input().rstrip().split()))

    result = jumping_on_clouds(n, c)

    fptr.write(str(result) + '\n')

    fptr.close()

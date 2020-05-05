"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Interview
Level: Easy
Link: https://www.hackerrank.com/challenges/sock-merchant
"""

#!/bin/python3

import math
import os
import random
import re
import sys

# Complete the sockMerchant function below.
def sockMerchant(n, ar):
    n_sock = {} #dict make it fast to look for odd sock O(1)
    socks = 0
    for sock in ar: #just once O(n)
        if sock in n_sock:
            del n_sock[sock]
            socks += 1
        else:
            n_sock[sock] = 1
    return socks



if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input())

    ar = list(map(int, input().rstrip().split()))

    result = sockMerchant(n, ar)

    fptr.write(str(result) + '\n')

    fptr.close()

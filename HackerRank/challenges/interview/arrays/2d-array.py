"""
Author: William Meira
Date: 2020-05-07
Platform: HackerRank
Type: Interview
Level: Easy
Link: https://www.hackerrank.com/challenges/2d-array/
"""


import os


def hourglass_sum(arr):
    return max([arr[row - 1][col - 1]
              + arr[row - 1][col]
              + arr[row - 1][col + 1]
              + arr[row][col]
              + arr[row + 1][col - 1]
              + arr[row + 1][col]
              + arr[row + 1][col + 1]
              for row in range(1, 5) for col in range(1, 5)])


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr = []

    for _ in range(6):
        arr.append(list(map(int, input().rstrip().split())))

    result = hourglass_sum(arr)

    fptr.write(str(result) + '\n')

    fptr.close()

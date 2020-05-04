"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Challenge - 10 Days of Statistics
Link: https://www.hackerrank.com/challenges/s10-weighted-mean/problem
"""

def calculate_weighted_mean(arr, weight, length):
    w_sum = sum([weight[n]*arr[n] for n in range(0,length)])
    return w_sum/sum(weight)

length = int(input())
arr = [int(a) for a in input().split()]
weight = [int(a) for a in input().split()]

mean = calculate_weighted_mean(arr, weight, length)

print("{:.1f}".format(mean))
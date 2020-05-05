"""
Author: William Meira
Date: 2020-05-04
Platform: HackerRank
Type: Challenge - 10 Days of Statistics
Level: Easy
Link: https://www.hackerrank.com/challenges/s10-basic-statistics/problem
"""

# Enter your code here. Read input from STDIN. Print output to STDOUT

def calculate_mean(arr, length):
    return sum(arr) / length

def calculate_median(arr, length):
    if length%2 == 1: #odd
        return arr[length//2]
    else: #even
        return (arr[length//2-1] + arr[length//2])/2

def calculate_mode(arr, length):
    occur = 1
    max_occur = occur
    current_mode = arr[0]
    mode = current_mode
    for n in range(1, length):
        if arr[n] == current_mode:
            occur += 1
        else:
            if occur > max_occur:
                mode = current_mode
                max_occur = occur
            current_mode = arr[n]
            occur = 1
    if occur > max_occur:
        mode = current_mode
    return mode

# Input
length = int(input())
arr = [int(a) for a in input().split()]

if not length or length == 0:
    mean = median = mode = 0
else:
    arr.sort()
    mean = calculate_mean(arr, length)
    median = calculate_median(arr, length)
    mode = calculate_mode(arr, length)

print('{:.1f}'.format(mean))
print('{:.1f}'.format(median))
print('{}'.format(mode))


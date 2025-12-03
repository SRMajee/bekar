# # 31. Explain short-circuit evaluation in any() & all() with demo.
any([0,0,5])  # stops when 5 appears
all([1,1,0])  # stops at 0

# # 32. Show how itertools.chain() avoids memory overhead.
# from itertools import chain

# a = [1, 2, 3]
# b = [4, 5, 6]
# print(list(chain(a, b)))  # no new list created internally

# 33. Compare Python map/filter with list comprehensions.

# | map/filter      | list-comprehension |
# | --------------- | ------------------ |
# | More functional | More pythonic      |
# | Lazy            | Eager              |
# | Composable      | Less composable    |

# #34. Write generator to produce squares infinitely.
# def sq():
#     n = 1
#     while True:
#         yield n * n
#         n += 1


# f = sq()
# [print(next(f)) for _ in range(5)]


# # 35. Implement reduce-max without using builtin max().
# from functools import reduce

# nums = [5, 9, 3, 7]
# print(reduce(lambda a, b: a if a > b else b, nums))

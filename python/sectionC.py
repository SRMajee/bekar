# # 21. Partition prime & non-prime.
# nums = range(1, 10)
# def prime(n):
#     return all(n % i for i in range(2, int(n**0.5) + 1)) and n > 1

# result = {
#     "prime": list(filter(prime, nums)),
#     "non_prime": list(filter(lambda x: not prime(x), nums)),
# }

# print(result)


# # 22. Flatten using map + chain.
from itertools import chain

words = ["ab", "cd", "ef"]
print(list(chain.from_iterable(map(list, words))))
# print(list("ab"))
# from itertools import chain

# words = ["ab", "cd", "ef"]

# maintains order
print(list(dict.fromkeys(chain.from_iterable(map(list, words)))))



# # 23. First 20 Fibonacci using generator.
# def fib():
#     a, b = 0, 1
#     while True:
#         yield a
#         a, b = b, a + b

# f = fib()
# [print(next(f)) for _ in range(20)]

# # 24. Count file lines lazily.
# sum(1 for _ in open("big.txt"))

# 25. Compare map-reduce vs loop.
# | map/reduce     | loop       |
# | -------------- | ---------- |
# | Declarative    | Imperative |
# | Short          | Verbose    |
# | Parallelizable | Sequential |
# | Lazy           | Eager      |


# # 26. Pure lambda vs impure.
# x=5; f=lambda y:y+x        # pure - no mutation
# # impure = x+=1 inside lambda

# # 27. map(filter()) taxation.
# s = [120000, 80000, 60000, 50000]
# tax = lambda x: x * 0.2
# is_tax = lambda x: x > 60000
# print(list(map(tax, filter(is_tax, s))))


# # 28. Tribonacci.
# def trib():
#     a, b, c = 0, 1, 1
#     while True:
#         yield a
#         a, b, c = b, c, a + b + c


# t = trib()
# [next(t) for _ in range(20)]

# # 29. Dictionary using reduce.
# from functools import reduce

# words = ["a", "b", "a", "c", "b", "a"]
# print(reduce(lambda acc, w: (acc.update({w: acc.get(w, 0) + 1}) or acc), words, {}))

# # 30. Replace loop with pipeline.
# nums = [1, 2, 3, 4, 5, 6]
# print(list(map(lambda x: x * x, filter(lambda x: x % 2 == 0, nums))))

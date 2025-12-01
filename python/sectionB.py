# # 11. Sort students by score using max() key.
# data = [
#     {"name": "A", "score": 40},
#     {"name": "B", "score": 70},
#     {"name": "C", "score": 55},
# ]
# print((max(data, key=lambda d: d["score"]))  )


# # # Sort in ascending order by score
# ascending = sorted(data, key=lambda d: d["score"])

# # Sort in descending order by score
# descending = sorted(data, key=lambda d: d["score"], reverse=True)

# print("Ascending :", ascending)
# print("Descending:", descending)


# # 12. Find repeated characters.
s = "mississippi"
# freq = {}
# list(map(lambda c: freq.update({c: freq.get(c, 0) + 1}), s))
# print({k: v for k, v in freq.items() if v > 1})

# freq = dict(map(lambda c: (c, s.count(c)), set(s)))
# print({ k for k, v in freq.items() if v > 1})

# from functools import reduce

# s = "mississippi"
# freq = reduce(lambda acc, ch: acc.update({ch: acc.get(ch, 0) + 1}) or acc, s, {})
# result = {k: v for k, v in freq.items() if v > 1}
# print(result)

# # 13. Word frequency from file.
# from functools import reduce

# with open("a.txt") as f:
#     words = f.read().split()

# freq = reduce(lambda acc, w: acc.update({w: acc.get(w, 0) + 1}) or acc, words, {})
# print(freq)

# 2. Using map() + lambda to update dictionary
# with open("a.txt") as f:
#     words = f.read().split()

# freq = {}
# list(map(lambda w: freq.update({w: freq.get(w, 0) + 1}), words))
# print(freq)

# # ✔ Entire processing done in lambda + map
# # ⚠ Uses side-effect on freq dict (allowed in FP style questions)

# # 3. Using filter() + map()
# with open("a.txt") as f:
#     words = f.read().split()

# print(dict(map(lambda w:(w,words.count(w)), set(words))))


# # 14. Convert strings to lengths using map.
# print(list(map(len,["apple","kiwi"])))


# # 15. Filter primes.
# print(list(filter(
#     lambda n: n > 1 and all(n % i for i in range(2, int(n**0.5)+1)),
#     range(1, 100)
# )))
# def prime(n):
#     return all(n % i for i in range(2, int(n**0.5) + 1)) and n > 1
# print(list(filter(prime, range(1, 100))))
# print(list(filter(lambda x : prime(x), range(1, 100))))

# # 16. reduce() multiplication.
# from functools import reduce

# print(reduce(lambda a, b: a * b, [1, 2, 3, 4]))

# # 17. partial() create quad(a,b,c,x).
# from functools import partial

# def quad(a, b, c, x):
#     return a * b * c * x

# f = partial(quad, 1, 2, 3)
# print(f(5))  # 30


# # 18. compose(f,g).
# def compose(f, g):
#     return lambda x: f(g(x))


# f = lambda x: x * x
# g = lambda x: x + 1
# print(compose(f, g)(4))  # 25

# # 19. Read CSV lazily.
# import csv
# data=csv.reader(open("file.csv"))
# next(data)

# # 20. Infinite cycle.
# from itertools import cycle

# c = cycle(["A", "B", "C"])
# for i in range(6):
#     print(next(c))

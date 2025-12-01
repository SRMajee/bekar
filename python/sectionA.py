# #1. define lazy evalutaion

# r = range(10)
# it = enumerate(["A","B"])
# print(next(it))
# print(next(it))

# lst = [1,2,3]
# it = iter(lst)
# print(next(it))

# #2. enumerate and zip
# names = ["A", "B", "C"]
# print(next(enumerate(names,1)))

# w = ["Mon", "Tue", "Wed"]
# print(list(zip(w, names)))

# #3. closure
# def outer(a):
#     def inner(b):
#         return a + b
#     return inner

# f = outer(10)
# print(f(4))

# #4. any() vs all()
# print(any([0, False, 4]))
# print(all([1, True, 4]))

# #5. map - convert all words to uppercase
# print(list(map(str.upper, ["hello", "world"])))

# # #6. filter - print even nos
# print(list(filter(lambda x: x % 2 == 0, range(1, 21))))

# #7. reduce - sum a list
# from functools import reduce
# print(reduce(lambda a,b:a+b,[1,2,3,4],0))

# #8. higher order functions
# # functions returing a function
# # functions taking a function
# # decorators use both


# def apply(f, x):  # accepts a function
#     return f(x)


# def gen(n):  # returns a function
#     return lambda x: x + n


# n = 3
# f = gen(n)
# print(apply(f,5))


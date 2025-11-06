list(L,H,T):- [H|T]=L.

% Check if element present in list
% member(X,L):-[H|T]=L,H=X.
% member(X,L):- [H|_] = L,H=X.
member(X,[X|_]).
% member(X,L):-[_|T] = L,member(X,T).
member(X,[_|T]):-member(X,T).

% Length of list
size([],0).
% size(L,N):- [_|T]=L, size(T,N1),N is N1+1.
size([_|T],N):-size(T,N1),N = N1+1.

% sum of elements of list
sum([],0).
sum([H|T],X):-sum(T,X1),X is X1+H.

% check if sorted asc
is_sorted([]).
is_sorted([_]).
% is_sorted(L):-[H|T]=L, [A|B]=T, H=<A, is_sorted(T).
% is_sorted([H|[A|B]]):-H=<A, is_sorted([A|B]).
is_sorted([H,A|B]):-H=<A, is_sorted([A|B]).

% append
append([],L,L).
append(L1,L2,L):- [H|T]=L1,append(T,L2,L3),L=[H|L3].

% accumulator
listsize(L,N):-listacc(L,0,N).
listacc([],A,A).
listacc([H|T],A,N):- A1=A+1,listacc(T,A1,N).

% last element of list
lastElement([X],X).
lastElement([_|T],X):-lastElement(T,X).

% max element of list
maxElement([X],X).
maxElement([H|T],X):- maxElement(T,X1) , (H > X1 -> X=H;X=X1 ).


% min element of list
minElement([X],X).
minElement([H|T],X):- minElement(T,X1) , (H < X1 -> X=H;X=X1 ).

% prependElement
prependElement(E, L, [E|L]).

% Base case
prependList([], L, L).

% Recursive case
prependList([H|T], L, [H|R]) :- prependList(T, L, R).

% gcd
gcd(X,0,X).
gcd(X,Y,Z):- not(Y=0), R is X mod Y, gcd(Y,R,Z).

% fibonacci
fibonacci(0, 0).
fibonacci(1, 1).
fibonacci(N, F) :-
    N > 1,
    N1 is N - 1,
    N2 is N - 2,
    fibonacci(N1, F1),
    fibonacci(N2, F2),
    F is F1 + F2.

% factorial(N, F)
factorial(0, 1).
factorial(N, F) :-
    N > 0,
    N1 is N - 1,
    factorial(N1, F1),
    F is N * F1.

% reverseList(List, Reversed)
reverseList([], []).
reverseList([H|T], R) :-
    reverseList(T, RevT),
    append(RevT, [H], R).


% reverseList(List, Reversed)
reverseList(L, R) :- reverseAcc(L, [], R).

% reverseAcc(CurrentList, Accumulator, Result)
reverseAcc([], Acc, Acc).
reverseAcc([H|T], Acc, R) :-
    reverseAcc(T, [H|Acc], R).

% palindrome check
palindrome(L) :-
    reverseList(L, R),
    L = R.

% Optional: Return yes/no
isPalindrome(L, Result) :-
    reverseList(L, R),
    (L = R -> Result = yes ; Result = no).

% --- Palindrome check for list or string ---
palindromeStr(Str) :-
    string_chars(Str, Chars),       % Convert string to list of chars
    reverseList(Chars, Rev),
    Chars = Rev.

% --- Universal Palindrome ---
palindromeAny(Input) :-
    ( string(Input) -> string_chars(Input, Chars) ; Chars = Input ),
    reverseList(Chars, Rev),
    Chars = Rev.


% --- Bubble Sort ---
bubble([X,Y|T], [Y|R]) :- X > Y, !, bubble([X|T], R).
bubble([X|T], [X|R]) :- bubble(T, R).
bubble([], []).
bubbleSort(L, Sorted) :-
    bubble(L, L1),
    (L = L1 -> Sorted = L ; bubbleSort(L1, Sorted)).

% --- Linear Search ---
linearSearch([X|_], X) :- !.
linearSearch([_|T], X) :- linearSearch(T, X).

% --- Quick Sort ---
quickSort([], []).
quickSort([H|T], Sorted) :-
    partition(H, T, Smaller, Greater),
    quickSort(Smaller, S1),
    quickSort(Greater, S2),
    append(S1, [H|S2], Sorted).

partition(_, [], [], []).
partition(P, [H|T], [H|S], G) :- H =< P, !, partition(P, T, S, G).
partition(P, [H|T], S, [H|G]) :- partition(P, T, S, G)


% quickSort(List, Sorted)
quickSort([], []).
quickSort([H|T], Sorted) :-
    partition(H, T, Smaller, Greater),
    quickSort(Smaller, SortedSmaller),
    quickSort(Greater, SortedGreater),
    append(SortedSmaller, [H|SortedGreater], Sorted).

% partition(Pivot, List, Smaller, Greater)
partition(_, [], [], []).
partition(P, [H|T], [H|Smaller], Greater) :-
    H =< P, !, partition(P, T, Smaller, Greater).
partition(P, [H|T], Smaller, [H|Greater]) :-
    partition(P, T, Smaller, Greater).


% Basic Recursive Definition
trimonacci(0, 0).
trimonacci(1, 1).
trimonacci(2, 1).
trimonacci(N, T) :-
    N > 2,
    N1 is N - 1,
    N2 is N - 2,
    N3 is N - 3,
    trimonacci(N1, T1),
    trimonacci(N2, T2),
    trimonacci(N3, T3),
    T is T1 + T2 + T3.

% Generate first N Trimonacci numbers
trimonacciList(N, L) :- trimonacciListAcc(N, 0, [], L).
trimonacciListAcc(N, I, Acc, L) :-
    I < N,
    trimonacci(I, T),
    I1 is I + 1,
    append(Acc, [T], Acc1),
    trimonacciListAcc(N, I1, Acc1, L).
trimonacciListAcc(N, N, L, L).

% Tail-recursive optimized version
trimonacciFast(N, R) :- trimonacciFastAcc(N, 0, 1, 1, R).
trimonacciFastAcc(0, A, _, _, A).
trimonacciFastAcc(1, _, B, _, B).
trimonacciFastAcc(2, _, _, C, C).
trimonacciFastAcc(N, A, B, C, R) :-
    N > 2,
    N1 is N - 1,
    Next is A + B + C,
    trimonacciFastAcc(N1, B, C, Next, R).

% check_num(+X)
% Prints whether X is positive, zero, or negative.

check_num(X) :-
    X > 0, !,
    write('Positive'), nl.

check_num(X) :-
    X =:= 0, !,
    write('Zero'), nl.

check_num(_) :-
    write('Negative'), nl.

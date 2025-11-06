% ------------------------------------------------------------
% 1. Generate first N natural numbers
% ------------------------------------------------------------
% append
append([],L,L)
append(L1,L2,L):- [H|T]=L1,append(T,L2,L3),L=[H|L3].

% genNaturals(N, List)
genNaturals(0, []) :- !.
genNaturals(N, List) :-
    N > 0,
    N1 is N - 1,
    genNaturals(N1, L1),
    append(L1, [N], List).

% Example:
% ?- genNaturals(5, L).
% L = [1, 2, 3, 4, 5].

% ------------------------------------------------------------
% 2. Generate a list by replicating a number X, N times
% ------------------------------------------------------------

% replicate(X, N, List)
replicate(_, 0, []):-!.
replicate(X, N, [X|R]) :-
    N > 0,
    N1 is N - 1,
    replicate(X, N1, R).

% Example:
% ?- replicate(7, 4, L).
% L = [7, 7, 7, 7].

% ------------------------------------------------------------
% 3. Insert an element at first, last, or ith position
% ------------------------------------------------------------

% insertFirst(Element, List, Result)
insertFirst(E, L, [E|L]).

% insertLast(Element, List, Result)
insertLast(E, [], [E]).
insertLast(E, [H|T], [H|R]) :-
    insertLast(E, T, R).

% insertAt(Element, List, Pos, Result)
insertAt(E, L, 1, [E|L]).
insertAt(E, [H|T], Pos, [H|R]) :-
    Pos > 1,
    Pos1 is Pos - 1,
    insertAt(E, T, Pos1, R).

% Examples:
% ?- insertFirst(9, [1,2,3], R).
% R = [9,1,2,3].
%
% ?- insertLast(9, [1,2,3], R).
% R = [1,2,3,9].
%
% ?- insertAt(9, [1,2,3,4], 3, R).
% R = [1,2,9,3,4].

% ------------------------------------------------------------
% 4. Max of 2 or 3 numbers
% ------------------------------------------------------------

% max2(A,B,Max)
max2(A, B, A) :- A >= B, !.
max2(_, B, B).

% max3(A,B,C,Max)
max3(A, B, C, Max) :-
    max2(A, B, Temp),
    max2(Temp, C, Max).

% Examples:
% ?- max2(5, 9, M).
% M = 9.
%
% ?- max3(5, 9, 3, M).
% M = 9.

% ------------------------------------------------------------
% 5. Divide a list into two parts and print all possible combinations
% ------------------------------------------------------------

% divideList(L, A, B)
divideList(L,A,B) :- append(A, B, L).

% Example:
% ?- divideList([1,2,3],A,B).
% A = [], B = [1,2,3];
% A = [1], B = [2,3];
% A = [1,2], B = [3];
% A = [1,2,3], B = [].

% ------------------------------------------------------------
% 6. Find first, last, and ith element of a list
% ------------------------------------------------------------

% firstElement(List, X)
firstElement([X|_], X).

% lastElement(List, X)
lastElement([X], X).
lastElement([_|T], X) :- lastElement(T, X).

% ithElement(List, I, X)
ithElement([H|_], 1, H).
ithElement([_|T], I, X) :-
    I > 1,
    I1 is I - 1,
    ithElement(T, I1, X).

% Examples:
% ?- firstElement([10,20,30], X).
% X = 10.
%
% ?- lastElement([10,20,30], X).
% X = 30.
%
% ?- ithElement([10,20,30,40], 3, X).
% X = 30.


% --- Insertion Sort ---
insert(X, [], [X]).
insert(X, [H|T], [X,H|T]) :- X =< H, !.
insert(X, [H|T], [H|R]) :- insert(X, T, R).
insertionSort([], []).
insertionSort([H|T], Sorted) :-
    insertionSort(T, SortedTail),
    insert(H, SortedTail, Sorted).

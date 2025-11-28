% member(X,L):-[H|T]=L,H=X.
% member(X,L):- [_|T] = L, member(X,T).

member(H,[H|T]).
member(X,[_|T]):-  member(X,T).


size([],0).
% size(L,X):-[_|T]=L,size(T,X1),X is X1+1.
size([_|T],X):- size(T,X1), X = X1+1.

sum([],0).
sum([H|T],X):- sum(T,X1), X is X1+H.

minElement([X],X).
minElement([H|T],X):- minElement(T,X1) , (H<X1 -> X=H;X=X1).


maxElement([X],X).
maxElement([H|T],X):- maxElement(T,X1) , (H>X1 -> X=H;X=X1).



% Generates natural numbers starting from 0
num(0).
num(X) :-
    num(Y),
    X is Y + 1.

% Write numbers from I to J
writeNum(I, J) :-
    num(X),
    X >= I, X =< J,
    write(X), nl,
    fail.
writeNum(_, _).  % succeed after finishing


writeNum2(I, J) :-
    between(I, J, X),
    write(X), nl,
    fail.
writeNum2(_, _).


% ?- writeNum(3, 7).
% 3
% 4
% 5
% 6
% 7
% true.


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


% even_or_odd(+N)
% Prints "Even" if divisible by 2, else "Odd".

even_or_odd(N) :-
    0 is N mod 2,
    write('Even'), nl.

even_or_odd(_) :-
    write('Odd'), nl.

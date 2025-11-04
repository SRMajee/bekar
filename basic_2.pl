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

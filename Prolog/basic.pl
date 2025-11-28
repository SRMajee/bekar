likes(sumesh,makima).
likes(sumesh,tsunade).
likes(sumesh,hinata).
likes(sriju,yourname).
likes(sriju,kantara).
likes(sriju,comatozze).
likes(sumesh,comatozze).
likes(sumesh,hentai).
likes(sumesh,kantara).
likes(shyam,classic).
likes(sumesh,X) :- person(X).
likes(shyam,X) :- likes(X,classic).

person(reze).
person(tsunade).
person('Makima').

animal(sparrow).
animal(ranvijay).
animal(modi).

feather(sparrow).
feather(modi).

female(tsunade).
female(hinata).
female('Makima').

male(itachi).
male(sasuke).

parents(itachi,fugaku,mikoto).
parents(sasuke,fugaku,mikoto).

% ?- likes(sumesh,makima), likes(sumesh,sakura). <- AND
% ?- likes(sumesh,X), likes(sriju,X)
%  , -> AND
%  ; -> OR

isBird(X):- animal(X),feather(X).
isBrother(X,Y):- male(X),parents(X,M,F),parents(Y,M,F).


/*
anime(X,naruto) = anime(luffy,Y).
'student' = student.
f(X,X) = f(a,b).
f(X,a(b,c)) = f(Z,a(Z,C)).
*/



population(china,100).
population(india,105).
population(usa,25).
population(germany,9).

area(china,10).
area(india,4).
area(usa,12).
area(germany,4).

density(X,Y):-
    population(X,Pop),
    area(X,Ar),
    Y is Pop/Ar.

num(0):-!.
num(X):-num(Y),X is Y+1.

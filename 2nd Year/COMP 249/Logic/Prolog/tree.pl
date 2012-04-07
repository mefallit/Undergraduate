male(murat).
male(arda).
male(cem).
male(orcun).
male(oktay).
male(nazim).

female(selin).
female(irem).
female(merve).
female(sibel).
female(aysel).


child(selin,arda).
child(selin,irem).
child(selin,merve).
child(murat,arda).
child(murat,irem).
child(murat,merve).
child(aysel,orcun).
child(aysel,oktay).
child(cem,orcun).
child(cem,oktay).
child(orcun,nazim).
child(merve,sibel).
child(orcun,sibel).
child(cansu,nazim).

married(selin,mehmet).
married(aysel,cem).
married(merve,orcun).

father(X,B):-male(B),child(B,X). 
mother(X,A):-female(A),child(A,X).

gelin(X,A):- married(X,Y), mother(Y,A).

kaynana(Y,A):- child(Y,M),married(A,M).
kaynana1(Y,A):-child(Y,M),married(M,A).





uveykardes(X,Y):- ((father(X,A), mother(X,B), married								(A,B),father(Y,A), mother(Y,M));
                  (father(X,A), mother(X,B),married(A,B),father(Y,N), mother(Y,B))), B \=M, A\=N.



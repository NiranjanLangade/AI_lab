on(floor,monkey).
on(floor,box).
in(room,monkey).
in(room,box).
in(room,banana).
at(ceiling,banana).

strong(monkey).
grasp(monkey).
climb(monkey,box).

push(monkey,box):-
    strong(monkey).

under(box,banana):-
    push(monkey,box).

canreach(monkey,banana):-
    at(floor,banana);
    at(ceiling,banana),
    under(box,banana),
    climb(monkey,box).

canget(monkey,banana):-
    canreach(monkey,banana),grasp(monkey).
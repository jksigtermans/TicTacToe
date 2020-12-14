# Tic-Tac-Toe
** Assignment completion **

Status: applied basic technologies and built application scaffolding.

** Applied required technologies: **

1) Spring Boot latest version (applied requirement)

2) Spring data JPA to PostgreSQL database (applied requirement)

3) Spring security Basic Authentication (applied requirement)

4) Dockerized PostgreSQL (applied requirement)

** Applied chosen technologies: **

1) AKKA Actors (creating a concurrent safe, distributed, scalable service layer)

2) Spring RESTful interface (necessity derived from assignment)

3) Event sourcing principle (all game moves can be replayed)

4) Lombok framework (avoiding boilerplate code)

5) Java version 11 (currently minimal standard used in the field)

** Not yet applied assignment requirements: **

1) Unit testing (probably using Mockito) and integration tests

Why: no TDD approach, PoC approach

2) Production ready code

Why: no code review, no intensive testing, no front-end

3) TO-DO in code: webSocket updates / Server-side push messaging (using Postgres Listen/Notify principle)

Why: too little time :-)

4) TO-DO in code: add user supplied credentials to list of authenticated credentials

Why: how to?

5) TO-DO in code: independent game management (like time control on participation, pausing, player turns, et cetera)

Why: too little time :-)

6) Computer player engine (computer making strategic move decisions)

Why: too little time :-)

** Usage: **

1) Install Postgres e.g. local OR dockerized: 

	docker run -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword -d postgres

2) Apply table creation scripts in project root

3) Apply database credentials in application.yaml

4) Run application as a Spring Boot application

5) Optional requests:

Non-secure call to register a player with UN=foo and PW=bar:

	http://localhost:8080/srv/register_player/foo/bar

Secure call to create a new game for player with ID=15, playing against the computer (true) or leave game available to another player (false):
	
	http://localhost:8080/srv/create_game/15/true
	
Secure call to show currently available games:
	
	http://localhost:8080/srv/get_available_games

** Note: ** secure calls need a Basic Auth header for UN=foo and PW=bar: Authorization=Basic Zm9vOmJhcg==

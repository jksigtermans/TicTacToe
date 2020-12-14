# Tic-Tac-Toe

## Assignment
* Implement the game of Tic-Tac-Toe based on the product backlog below.
* You do ***not*** have to support all requirements: choose whatever you're comfortable with to implement.
* Ensure that you're happy with the implemented functionality.
* The most important thing is that you can motivate your choices and solutions.
* ***Have fun doing the assignment and make a nice application!***

  

## Product backlog
* As a player I want to play Tic-Tac-Toe against another player or against computer.
  * **Acceptance criteria**
    * The first player to achieve three-in-a-row wins.
    * Three-in-a-row can be horizontal, vertical or diagonal.
    * When the game ends a new game can be started.
    * The achieved score is registered. The score is calculated based on the lowest number of moves combined with the duration of the player's move. Duration of computer's moves can be considered 0.
      (you don't get points when you lose)
* As player I want be able to register my name along with a password so only authenticated users can play the game.   
  * **Acceptance criteria**
    * Credentials should be persisted in a data store.
* As a player I want to create games so that other players can join the game and start playing with me.
  * **Acceptance criteria**
    * When a game is created, it will be available for others to join for a certain amount of time (for example 2 minute). After that, if nobody joins the game will be cancelled.
    * If the creator of the game, leaves the game before another player joins (The creator stops listening to server push game updates), game will be cancelled right away.
* As a player I want to be able to join available games and start playing.
  * **Acceptance criteria**
    * The player can check list of available games along with the top 3 scores of game creators and join any of them.
    * Upon joining the game, one of the players is picked up randomly to start the game.
    * both sides see the other person's top 3 scores during the game.
    * Every player has a certain amount of time (for example 1 minute) to make a move, otherwise he/she will loose the game.     
    * If a player is disconnected (stops listening to server push game updates), it's timer will be frozen for a maximum certain amount of time so he can return to the game. If he does not return to the game (starts listening to game updates), it's timer will unfrozen and continue as normal.  

## Technical note
* Please use spring boot latest version, spring security for authentication (use http basic authentication for all protected endpoints) and spring data for working with underlying database.
* Please use dockerized postgres for database and preserve the data between application restarts.
* If you need any other infra component , please dockerize it.
* Please save your time and avoid any front end work.
* For simplicity and ease of testing by reviewer, please use HTTP based solution for server push game updates. Stop listening to updates, resembles a disconnect from the game in read world.
* Treat this assignment like a production ready code. Writing tests specially integration ones are vey welcomed. Think about code efficiency, readability and design patterns.
* Please Use Javadoc and put as much comments in your code as necessary to make reviewing the code easier.
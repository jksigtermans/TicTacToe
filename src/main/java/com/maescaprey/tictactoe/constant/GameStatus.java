package com.maescaprey.tictactoe.constant;

/**
 * Statuses the game may be in.
 * 
 * @author Maes-Caprey
 */
public enum GameStatus {

	/*
	 * The game is created, and waiting for an opponent to join. 
	 */
	CREATED,
	
	/*
	 * The game has begun. It has two players, or the player is playing against the computer.
	 */
	STARTED,
	
	/*
	 * The game is paused. The player that joined is not responding. 
	 */
	PAUSED,
	
	/*
	 * The game has ended. One of the players has won the game. 
	 */
	ENDED
}

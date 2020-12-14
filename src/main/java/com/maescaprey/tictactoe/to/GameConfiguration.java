package com.maescaprey.tictactoe.to;

import lombok.Data;

/**
 * Transfer object for the configuration of a new game.
 * 
 * @author Maes-Caprey
 */
@Data
public class GameConfiguration implements TransferObject {

	/*
	 * The main player's id.
	 */
	private final long playerId;
	
	/*
	 * True if the player wants to compete with the computer, false otherwise.
	 */
	private final boolean isComputerOpponent;
}

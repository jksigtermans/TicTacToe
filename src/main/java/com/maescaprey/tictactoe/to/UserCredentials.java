package com.maescaprey.tictactoe.to;

import lombok.Data;

/**
 * Transfer object for the credentials of a user.
 * 
 * @author Maes-Caprey
 */
@Data
public class UserCredentials implements TransferObject {

	/*
	 * The user's name.
	 */
	private final String userName;
	
	/*
	 * The user's password.
	 */
	private final String password;
}

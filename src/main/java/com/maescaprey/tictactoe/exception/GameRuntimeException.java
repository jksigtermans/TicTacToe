package com.maescaprey.tictactoe.exception;

/**
 * Game runtime exception.
 */
public class GameRuntimeException extends RuntimeException {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -5814035452185903552L;

	/**
	 * Constructor for message only.
	 * 
	 * @param message
	 */
	public GameRuntimeException(final String message) {

		super(message);
	}

	/**
	 * Constructor for cause argument only.
	 * 
	 * @param throwable
	 */
	public GameRuntimeException(final Throwable throwable) {

		super(throwable);
	}

	/**
	 * Constructor for both message and cause arguments.
	 * 
	 * @param message
	 * @param throwable
	 */
	public GameRuntimeException(final String message, final Throwable throwable) {

		super(message, throwable);
	}
	
}

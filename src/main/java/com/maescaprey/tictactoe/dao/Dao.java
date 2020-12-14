package com.maescaprey.tictactoe.dao;

import com.maescaprey.tictactoe.domain.AbstractDomainObject;
import com.maescaprey.tictactoe.domain.Game;

/**
 * Contract for all DAO implementations.
 * 
 * @author Maes-Caprey
 */
public interface Dao {

	/**
	 * Get all available games.
	 * 
	 * @return All available games
	 */
	Iterable<Game> findAvailableGames();
	
	/**
	 * Save given transient entity. 
	 * 
	 * @param <T>
	 * @param transientEntity The entity to persist
	 * @return True if persisted entity holds an ID, false otherwise
	 */
	<T extends AbstractDomainObject> boolean save(T transientEntity);
}

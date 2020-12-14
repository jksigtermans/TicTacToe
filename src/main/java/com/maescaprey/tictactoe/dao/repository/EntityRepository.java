package com.maescaprey.tictactoe.dao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.maescaprey.tictactoe.constant.GameStatus;
import com.maescaprey.tictactoe.domain.AbstractDomainObject;
import com.maescaprey.tictactoe.domain.Game;

/**
 * Data-source repository for entities of all types.
 * 
 * @author Maes-Caprey
 */
public interface EntityRepository extends CrudRepository<AbstractDomainObject, Long> {

	/**
	 * Get all games with given status.
	 * 
	 * @param status
	 * @return Games with given status
	 */
	@Query("SELECT g FROM Game g WHERE g.status = ?1")
	Iterable<Game> findGamesByStatus(GameStatus status);	
}

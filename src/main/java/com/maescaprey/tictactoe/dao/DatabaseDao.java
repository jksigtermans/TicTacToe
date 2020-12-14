package com.maescaprey.tictactoe.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maescaprey.tictactoe.constant.GameStatus;
import com.maescaprey.tictactoe.dao.repository.EntityRepository;
import com.maescaprey.tictactoe.domain.AbstractDomainObject;
import com.maescaprey.tictactoe.domain.Game;

import lombok.extern.slf4j.Slf4j;

/**
 * DAO Implementation for database access.
 * 
 * @author Maes-Caprey
 */
@Slf4j
@Repository
public class DatabaseDao implements Dao {

	/*
	 * Entity repository.
	 */
	@Autowired
	private EntityRepository entityRepository;

	@Override
	public Iterable<Game> findAvailableGames() {
		
		return entityRepository.findGamesByStatus(GameStatus.CREATED);
	}

	@Override
	public <T extends AbstractDomainObject> boolean save(final T transientEntity) {
		
		log.debug("Saving entity of type {}", transientEntity.getClass().getSimpleName());
				
		final T persistedEntity = entityRepository.save(transientEntity);

		// Check if persisted entity holds an ID
		final boolean hasId = persistedEntity.getId() != null;
		if (!hasId) {
			
			log.error("Unable to save entity of type {}", transientEntity.getClass().getSimpleName());
		}
		
		return hasId;
	}
}

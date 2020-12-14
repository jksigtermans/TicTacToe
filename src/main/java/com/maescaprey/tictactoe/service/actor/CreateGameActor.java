package com.maescaprey.tictactoe.service.actor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maescaprey.tictactoe.constant.GameStatus;
import com.maescaprey.tictactoe.dao.DatabaseDao;
import com.maescaprey.tictactoe.domain.Game;
import com.maescaprey.tictactoe.service.ActorService;
import com.maescaprey.tictactoe.to.GameConfiguration;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import lombok.extern.slf4j.Slf4j;

/**
 * An actor whose responsibility is to register a new game.
 * 
 * @author Maes-Caprey
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateGameActor extends AbstractActor {

	/*
	 * Reference to the data layer.
	 */
	@Autowired
	private DatabaseDao databaseDao;

	/*
	 * Service for managing actors in the actor model.
	 */
	@Autowired
	private ActorService actorService;
	
	/*
	 * An actor to manage a game during its lifecycle.
	 */
	private ActorRef manageGameActor; 

	/**
	 * Spring post-construct initialization for this controller.
	 */
	@PostConstruct
	public void init() {
		
		manageGameActor = actorService.createActor("manageGameActor");
	}

	@Override
	public Receive createReceive() {

		return receiveBuilder().match(GameConfiguration.class, configuration -> {

			final var playerId = configuration.getPlayerId();
			final var isComputerOpponent = configuration.isComputerOpponent();

			log.debug("Creating game for player with id {}, playing against {}", playerId, isComputerOpponent ? "the computer." : "another player.");

			// Create a new game
			final var newGame = new Game();
			newGame.setMainPlayerId(playerId);
			newGame.setStatus(isComputerOpponent ? GameStatus.STARTED : GameStatus.CREATED);

			// Attempt to save the game
			if (databaseDao.save(newGame)) {

				log.debug("Game created. The id is: {}", newGame.getId());

				getSender().tell(newGame, getSelf());

				// Start management of the game
				manageGameActor.tell(newGame, ActorRef.noSender());

			} else {
				getSender().tell(null, getSelf());
			}
		}).build();
	}
}

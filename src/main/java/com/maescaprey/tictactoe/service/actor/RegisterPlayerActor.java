package com.maescaprey.tictactoe.service.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maescaprey.tictactoe.dao.DatabaseDao;
import com.maescaprey.tictactoe.domain.Player;
import com.maescaprey.tictactoe.to.UserCredentials;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

/**
 * An actor whose responsibility is to register a new, given, player.
 * 
 * @author Maes-Caprey
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterPlayerActor extends AbstractActor {

	/*
	 * Reference to the data layer.
	 */
	@Autowired
	private DatabaseDao databaseDao;

	@Override
	public Receive createReceive() {

		return receiveBuilder().match(UserCredentials.class, credentials -> {

			final var userName = credentials.getUserName();

			log.debug("Registering player with user name: {}", userName);

			// Create a new player
			final var newPlayer = new Player();
			newPlayer.setUserName(userName);
			newPlayer.setPassword(credentials.getPassword());

			// Attempt to save the player data
			if (databaseDao.save(newPlayer)) {

				log.debug("Player with user name {} registered. The id is: {}", userName, newPlayer.getId());

				getSender().tell(newPlayer, getSelf());

			} else {
				getSender().tell(null, getSelf());
			}
		}).build();
	}
}

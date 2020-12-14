package com.maescaprey.tictactoe.service.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maescaprey.tictactoe.constant.GameType;
import com.maescaprey.tictactoe.dao.DatabaseDao;

import akka.actor.AbstractActor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GetGamesActor extends AbstractActor {

	/*
	 * Reference to the data layer.
	 */
	@Autowired
	private DatabaseDao databaseDao;
	
	@Override
	public Receive createReceive() {

		return receiveBuilder().match(GameType.class, gameType -> {

			switch(gameType) {
			
			case AVAILABLE:
				log.debug("Getting all available games.");

				getSender().tell(databaseDao.findAvailableGames(), getSelf());
				break;
			default:
				getSender().tell(null, getSelf());
			}
		}).build();
	}
}

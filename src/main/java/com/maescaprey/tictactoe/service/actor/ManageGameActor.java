package com.maescaprey.tictactoe.service.actor;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.maescaprey.tictactoe.domain.Game;

import akka.actor.AbstractActorWithTimers;
import lombok.extern.slf4j.Slf4j;

/**
 * Actor responsible to manage a game. 
 * 
 * All game control, like time-based decisions, is done in this actor.
 * 
 * @author Maes-Caprey
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ManageGameActor extends AbstractActorWithTimers {

	@Override
	public Receive createReceive() {

		return receiveBuilder()
		        .match(Game.class, game -> {

		        	log.info("Started managing game with id={}", game.getId());
		        	
//TODO: do independent game management (in this actor or another), such as:
// A) Register creation start (NOW)
// B) Keep pause time (when opponent does not respond)
// C) Keep turn time (duration of player's turn)
// D) End game with status CREATED will be ENDED if they have no second player after two minutes
// E) Set status to ENDED when main player does not respond
// F) If game has status ENDED, send poison pill to all managing actors
		        	
//TODO: create server-send messages using Postgres notify/listen mechanism
//@see https://github.com/ranjanprj/SpringBootWSPostgresNotify
		        	
		        })
		        .build();	}
}

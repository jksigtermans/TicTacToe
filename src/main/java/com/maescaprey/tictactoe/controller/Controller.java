package com.maescaprey.tictactoe.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maescaprey.tictactoe.constant.GameType;
import com.maescaprey.tictactoe.domain.Game;
import com.maescaprey.tictactoe.domain.Player;
import com.maescaprey.tictactoe.exception.GameRuntimeException;
import com.maescaprey.tictactoe.service.ActorService;
import com.maescaprey.tictactoe.to.GameConfiguration;
import com.maescaprey.tictactoe.to.UserCredentials;

import akka.actor.ActorRef;
import lombok.extern.slf4j.Slf4j;

/**
 * Central RESTful controller of the game. 
 * Handles REST calls to the service.
 * 
 * @author Maes-Caprey
 */
@Slf4j
@RestController(value = "/srv")
public class Controller {

	/*
	 * Service for managing actors in the actor model.
	 */
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	/*
	 * An actor to handle registering of players.
	 */
	private ActorRef registerPlayerActor; 

	/*
	 * An actor to handle creation of games.
	 */
	private ActorRef createGameActor;
	
	/*
	 * An actor to handle retrieval of available games.
	 */
	private ActorRef getGamesActor;
	
	/**
	 * Spring post-construct initialization for this controller.
	 */
	@PostConstruct
	public void init() {
		
		registerPlayerActor = actorService.createActor("registerPlayerActor");
		createGameActor = actorService.createActor("createGameActor");
		getGamesActor = actorService.createActor("getGamesActor");
	}	
	
	/**
	 * Register given player.
	 * Returns true if registering the player was successful, false otherwise.
	 * 
	 * @param userName The given user name. 
	 * @param password The given password. 
	 * @return True if registering the player was successful, false otherwise.
	 */
	@RequestMapping(value = "/srv/register_player/{userName}/{password}")
	public @ResponseBody ResponseEntity<Player> registerPlayer(@PathVariable final String userName, @PathVariable final String password) {

		log.debug("Called registerPlayer({}, {})", userName, password);
		
		final UserCredentials credentials = new UserCredentials(userName, password);
		
		final Player newPlayer = actorService.askAndAwait(registerPlayerActor, credentials, Player.class, 5000L);

//TODO: add credentials to collection of authenticated users
        try {
			authenticationManagerBuilder.inMemoryAuthentication().withUser(userName).password(password).roles("USER");

        } catch (final Exception exception) {

			throw new GameRuntimeException("Unable to register user: " + userName, exception);
		}

		return ResponseEntity.ok(newPlayer);
	}

	/**
	 * Creates a new game, against the computer if given boolean is true, false otherwise.
	 * 
	 * @param isComputerOpponent True if the player wants to compete with the computer, false otherwise.
	 * @return The newly created game. 
	 */
	@RequestMapping(value = "/srv/create_game/{playerId}/{isComputerOpponent}")
	public @ResponseBody ResponseEntity<Game> createGame(@PathVariable final long playerId, @PathVariable final boolean isComputerOpponent) {

		log.debug("Called createGame({}, {})", playerId, isComputerOpponent);
		
		final GameConfiguration configuration = new GameConfiguration(playerId, isComputerOpponent);

		final Game newGame = actorService.askAndAwait(createGameActor, configuration, Game.class, 5000L);

		return ResponseEntity.ok(newGame);
	}

	/**
	 * Get all available games.
	 * 
	 * @return All available games.
	 */
	@RequestMapping(value = "/srv/get_available_games", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Iterable<Game>> getAvailableGames() {

		log.debug("Called getAvailableGames()");

		@SuppressWarnings("unchecked")
		final Iterable<Game> availableGames = actorService.askAndAwait(getGamesActor, GameType.AVAILABLE, Iterable.class, 5000L);
		
		return ResponseEntity.ok(availableGames);
	}
}

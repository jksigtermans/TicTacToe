package com.maescaprey.tictactoe.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.maescaprey.tictactoe.configuration.SpringExtension;
import com.maescaprey.tictactoe.exception.GameRuntimeException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.pattern.Patterns;
import akka.util.Timeout;
import lombok.extern.slf4j.Slf4j;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * Utility service for all actor activities.
 * 
 * AKKA Is used, to create a concurrent safe and distributed service layer.
 * 
 * @author Maes-Caprey
 */
@Configuration
@ComponentScan
@Slf4j
public class ActorService {

	/*
	 * Spring's application context.
	 */
	@Autowired
	private ApplicationContext applicationContext;

	/*
	 * General actor system to use.
	 */
	private ActorSystem actorSystem;

	@PostConstruct
	public void init() {

		actorSystem = initActorSystem();
	}

	/**
	 * Initialize the actor system using Spring AKKA extension, so the AKKA actors
	 * are Spring managed beans.
	 * 
	 * @return The actor system
	 */
	@Bean
	public ActorSystem initActorSystem() {

		final ActorSystem system = ActorSystem.create("ticTacToeSystem");
		SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);

		return system;
	}

	/**
	 * Create a new reference to given actor type.
	 * 
	 * @param actorBeanName The name of the actor bean.
	 * @return Reference to given actor type.
	 */
	public ActorRef createActor(final String actorBeanName) {

		return actorSystem.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(actorSystem).props(actorBeanName));
	}

	/**
	 * Send a message to given actor, and await for the expected response, for given duration in milliseconds. 
	 * 
	 * @param <T>
	 * @param actorReference The receiving actor
	 * @param message The message to send
	 * @param expectedType The expected response type
	 * @param timeoutInMilliseconds Maximum duration time to wait in milliseconds
	 * @return Actor response
	 */
	public <T> T askAndAwait(final ActorRef actorReference, final Object message, final Class<T> expectedType, final long timeoutInMilliseconds) {

		final Timeout timeout = new Timeout(Duration.create(timeoutInMilliseconds, TimeUnit.MILLISECONDS));

		@SuppressWarnings("unchecked")
		final Future<T> future = (Future<T>) Patterns.ask(actorReference, message, timeoutInMilliseconds);
		try {
			final T response = Await.result(future, timeout.duration());
			log.debug("Response of actor {} is: {}", actorReference, response);

			return response;

		} catch (final Exception exception) {

			throw new GameRuntimeException(exception);
		}
	}
}

package com.maescaprey.tictactoe.configuration;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import lombok.RequiredArgsConstructor;

/**
 * Allows Spring to override the instantiation process for an AKKA actor.
 * 
 * @see https://www.baeldung.com/akka-with-spring
 * 
 * @author Maes-Caprey
 */
@RequiredArgsConstructor
public class SpringActorProducer implements IndirectActorProducer {

	/*
	 * Spring's application context.
	 */
	private final ApplicationContext applicationContext;

	/*
	 * Name of the actor bean. 
	 */
	private final String beanActorName;

	@Override
	public Actor produce() {
		
		return (Actor) applicationContext.getBean(beanActorName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Actor> actorClass() {
		
		return (Class<? extends Actor>) applicationContext.getType(beanActorName);
	}
}
package com.maescaprey.tictactoe.configuration;

import org.springframework.context.ApplicationContext;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;

/**
 * Spring extension to be able to have AKKA actors also become managed Spring beans.
 * 
 * @see https://www.baeldung.com/akka-with-spring
 * 
 * @author Maes-Caprey
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.InnerSprintExtension> {

	/*
	 * Singleton implementation of this extension. 
	 */
	public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

	/**
	 * Private constructor to avoid public instantiation. 
	 */
	private SpringExtension() {
	}
	
	@Override
	public InnerSprintExtension createExtension(final ExtendedActorSystem system) {

		return new InnerSprintExtension();
	}

	/**
	 * Actual extension to use spring application context for actor instantiation. 
	 * 
	 * @author Maes-Caprey
	 */
	public static class InnerSprintExtension implements Extension {
		
		/*
		 * Spring application context.
		 */
		private volatile ApplicationContext applicationContext;

		/**
		 * Initialize this extension. 
		 * 
		 * @param applicationContext
		 */
		public void initialize(final ApplicationContext applicationContext) {

			this.applicationContext = applicationContext;
		}

		/**
		 * Return AKKA properties for given actor bean. 
		 * 
		 * @param actorBeanName
		 * @return AKKA properties for given actor bean
		 */
		public Props props(final String actorBeanName) {
			
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
		}
	}
}
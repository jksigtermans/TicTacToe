package com.maescaprey.tictactoe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity for a player who plays the game.
 * 
 * @author Maes-Caprey
 */
@Entity
@Table(name="TTT_PLAYER", uniqueConstraints=@UniqueConstraint(columnNames={"USER_NAME"}))
@Data
@EqualsAndHashCode(callSuper=true)
public class Player extends AbstractDomainObject {

	/*
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -2105010355385744448L;
	
	/*
	 * The player's user name.
	 */
	@Column(name = "USER_NAME", unique=true, nullable = false)
	private String userName;
	
	/*
	 * The player's password.
	 */
	@Column(name = "PASSWORD", nullable = false)
	private String password;
}

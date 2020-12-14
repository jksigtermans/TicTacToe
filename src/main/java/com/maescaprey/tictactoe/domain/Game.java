package com.maescaprey.tictactoe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.maescaprey.tictactoe.constant.GameStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity for a game.
 * 
 * @author Maes-Caprey
 */
@Entity
@Table(name="TTT_GAME")
@Data
@EqualsAndHashCode(callSuper=true)
public class Game extends AbstractDomainObject {

	/*
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 7980577292948890771L;
	
	/*
	 * Main player id (player that started the game).
	 */
	@Column(name="MAIN_PLAYER_ID", unique=false, nullable = false)
    private Long mainPlayerId;

	/*
	 * Guest player id (opponent that accepted the game).
	 * Optional value; null if main player is playing against the computer.
	 */
	@Column(name="GUEST_PLAYER_ID", unique=false, nullable = true)
    private Long guestPlayerId;

	/*
	 * Status the game is in.
	 */
	@Column(name="STATUS", unique=false, nullable = false)
	@Enumerated(EnumType.STRING)
	private GameStatus status;

	/*
	 * The ID of the player whose turn it is.
	 */
	@Column(name="TURN_ID", unique=false, nullable = true)
    private Long turnId;
}

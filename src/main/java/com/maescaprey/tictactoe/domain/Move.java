package com.maescaprey.tictactoe.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity for moves that the player make on the game board.
 * 
 * The entities can potentially be used for event sourcing. 
 * @see https://martinfowler.com/eaaDev/EventSourcing.html
 * 
 * @author Maes-Caprey
 */
@Entity
@Table(name="TTT_MOVE", uniqueConstraints=@UniqueConstraint(columnNames={"FIELD", "GAME_ID"}))
@Data
@EqualsAndHashCode(callSuper=true)
public class Move extends AbstractDomainObject {

	/*
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -2555380659829738803L;
	
	/*
	 * The game ID.
	 */
	@Column(name="GAME_ID", unique=false, nullable = false)
    private Long gameId;

	/*
	 * The player ID.
	 */
	@Column(name="PLAYER_ID", unique=false, nullable = false)
    private Long playerId;

	/*
	 * The point in time this move was made.
	 */
	@Column(name="TIME", unique=false, nullable = false)
	private Instant time;

	/*
	 * The field number [0-8] representing a position on the board. 
	 * 
	 * The board fields have their own unique ID, as follows:
	 * 
	 * <code>0|1|2</code>
	 * <code>-|-|-</code>
	 * <code>3|4|5</code>
	 * <code>-|-|-</code>
	 * <code>6|7|8</code>
	 */
	@Column(name="FIELD", unique=false, nullable = false)
	private short field;
}

package com.maescaprey.tictactoe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity represents a (high) score by a player. 
 * 
 * @author Maes-Caprey
 */
@Entity
@Table(name="TTT_SCORE")
@Data
@EqualsAndHashCode(callSuper=true)
public class Score extends AbstractDomainObject {

	/*
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 689736433305794133L;

	/*
	 * The player ID.
	 */
	@Column(name="PLAYER_ID", unique=false, nullable = false)
    private Long playerId;

	/*
	 * The player's score.
	 */
	@Column(name="SCORE", unique=false, nullable = false)
    private Long score;
}

package com.maescaprey.tictactoe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/**
 * Abstract super type for domain objects.
 * 
 * @author Maes-Caprey
 */
@Data
@MappedSuperclass
public abstract class AbstractDomainObject implements Serializable {

	/*
	 * Serial version ID.
	 */
	private static final long serialVersionUID = 7258726277673445027L;

	/*
	 * The id for the child entity.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable = false)
    private Long id;
}

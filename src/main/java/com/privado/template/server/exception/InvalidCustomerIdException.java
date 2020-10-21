package com.privado.template.server.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidCustomerIdException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6636632641726215542L;

	private final String message;

	public InvalidCustomerIdException(String message) {
		super();
		this.message = message;
	}

}

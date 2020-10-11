package com.privado.template.server.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InvalidCustomerIdException extends Exception {
	private String message;

	public InvalidCustomerIdException(String message) {
		super();
		this.message = message;
	}

}

package com.privado.template.server.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2568760818710122396L;

	private final String message;

	public DocumentNotFoundException(String message) {
		super();
		this.message = message;
	}

}

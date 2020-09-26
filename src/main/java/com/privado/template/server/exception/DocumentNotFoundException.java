package com.privado.template.server.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DocumentNotFoundException extends Exception {
	private String message;

	public DocumentNotFoundException(String message) {
		super();
		this.message = message;
	}

}

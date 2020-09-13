package com.privado.template.server.bean;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Template {

	@Id
	private String id;

	private String type;
	private String entity;
	private int customerId;
	private String law;
	private List<Field> fields;

	public Template() {
	}

}

package com.privado.template.server.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "templates")
public class Template {

	@Id
	private String id;

	private String type;
	private String entity;
	private String customerId;
	private String law;
	private List<Field> fields;

}

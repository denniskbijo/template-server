package com.privado.template.server.bean;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Field {

	@Id
	private String id;

	private String field;
	private String dataType;
	private String defaultValue;
	private String fieldType;
	private String fieldTypeLabel;
	private boolean isRemovable;
	private boolean mandatory;
	private List<String> optionsList;
	private OptionsUrl optionsUrl;
}

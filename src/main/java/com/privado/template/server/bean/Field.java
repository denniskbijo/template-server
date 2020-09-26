package com.privado.template.server.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Model corresponding to a field description in {@link Template}
 * 
 * @author denniskbijo
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class Field {

	@org.springframework.data.mongodb.core.mapping.Field("field")
	@JsonProperty("field")
	private String fieldName;

	private String label;

	@org.springframework.data.mongodb.core.mapping.Field("data_type")
	@JsonProperty("data_type")
	private String dataType;

	@org.springframework.data.mongodb.core.mapping.Field("default")
	@JsonProperty("default")
	private String defaultValue;

	@org.springframework.data.mongodb.core.mapping.Field("field_type")
	@JsonProperty("field_type")
	private String fieldType;

	@org.springframework.data.mongodb.core.mapping.Field("field_type_label")
	@JsonProperty("field_type_label")
	private String fieldTypeLabel;

	@org.springframework.data.mongodb.core.mapping.Field("is_removable")
	@JsonProperty("is_removable")
	private boolean isRemovable;

	private boolean mandatory;

	@org.springframework.data.mongodb.core.mapping.Field("options_list")
	@JsonProperty("options_list")
	private List<String> optionsList;

	@org.springframework.data.mongodb.core.mapping.Field("options_url")
	@JsonProperty("options_url")
	private OptionsUrl optionsUrl;
}

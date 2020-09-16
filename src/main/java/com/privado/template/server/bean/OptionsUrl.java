package com.privado.template.server.bean;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OptionsUrl {

	private String url;

	@Field("request_type")
	@JsonProperty("request_type")
	private String requestType;
}

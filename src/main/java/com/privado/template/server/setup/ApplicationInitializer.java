package com.privado.template.server.setup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privado.template.server.bean.Template;
import com.privado.template.server.repo.TemplateRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class ApplicationInitializer {

	@Bean
	CommandLineRunner initDatabase(TemplateRepository repository) {
		ObjectMapper mapper = new ObjectMapper();
		String template1 = "{\n" + "	\"type\": \"system\",\n" + "	\"entity\": \"entity\",\n"
				+ "	\"customerId\": \"system\",\n" + "	\"law\" : \"base\",\n" + "	\"fields\" : [ \n"
				+ "		{ \"field\" : \"name\", \"label\" : \"Name\", \"data_type\" :\"short-text\", \"default\" : \"Type name here..\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": true}, \n"
				+ "		{ \"field\" : \"description\", \"label\" : \"Description\", \"data_type\" :\"long-text\", \"default\" : \"Type description here..\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false},\n"
				+ "		{ \"field\" : \"entity_type\", \"label\" : \"Entity Type\", \"data_type\" :\"options\", \"default\" : \"\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_list\":[\n"
				+ "				\"Affiliate\", \"Client\", \"Holding Company\", \"Regulatory Body\", \"Subsidiary\"\n"
				+ "			]\n" + "		},\n"
				+ "		{ \"field\" : \"location\", \"label\" : \"Location\", \"data_type\" :\"options\", \"default\" : \"\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_url\": {\n" + "				\"url\" : \"dm/geos\",\n"
				+ "				\"request_type\" : \"GET\"\n" + "			}\n" + "		}\n" + "}";

		return args -> {
			log.info("Preloading " + repository.save(mapper.readValue(template1, Template.class)));
		};
	}
}
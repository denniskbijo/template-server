package com.privado.template.server;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privado.template.server.bean.Template;
import com.privado.template.server.repo.TemplateRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private TemplateRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		initDatabase();

	}

	private void initDatabase() throws IOException {
		repository.deleteAll();

		ObjectMapper mapper = new ObjectMapper();
		String template1 = "{\n	\"type\": \"system\",\n	\"entity\": \"entity\",\n"
				+ "	\"customerId\": \"system\",\n	\"law\" : \"base\",\n	\"fields\" : [ \n"
				+ "		{ \"field\" : \"name\", \"label\" : \"Name\", \"data_type\" :\"short-text\", \"default\" : \"Type name here..\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": true}, \n"
				+ "		{ \"field\" : \"description\", \"label\" : \"Description\", \"data_type\" :\"long-text\", \"default\" : \"Type description here..\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false},\n"
				+ "		{ \"field\" : \"entity_type\", \"label\" : \"Entity Type\", \"data_type\" :\"options\", \"default\" : \"\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_list\":[\n"
				+ "				\"Affiliate\", \"Client\", \"Holding Company\", \"Regulatory Body\", \"Subsidiary\"\n"
				+ "			]\n" + "		},\n"
				+ "		{ \"field\" : \"location\", \"label\" : \"Location\", \"data_type\" :\"options\", \"default\" : \"\", \"field_type\":	\"basic_details\", \"field_type_label\":	\"Basic Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_url\": {\n \"url\" : \"dm/geos\",\n				\"request_type\" : \"GET\"\n			}\n		}\n ] }";

		String template2 = "{\n" + "	\"type\": \"system\",\n" + "	\"entity\": \"entity\",\n"
				+ "	\"customerId\": \"system\",\n" + "	\"law\" : \"GDPR\",\n" + "	\"fields\" : [ \n"
				+ "		{ \"field\" : \"address\", \"label\" : \"Address\", \"data_type\" :\"long-text\", \"default\" : \"Type address here..\", \"field_type\":	\"contact_details\", \"field_type_label\":	\"Contact Details\", \"is_removable\" : false, \"mandatory\": false},\n"
				+ "		{ \"field\" : \"representative\", \"label\" : \"Representative\", \"data_type\" :\"options\", \"default\" : \"Type the representative name here..\", \"field_type\":	\"contact_details\", \"field_type_label\":	\"Contact Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_url\": {\n" + "				\"url\" : \"dm/customer/<customer_id>/users\",\n"
				+ "				\"request_type\" : \"GET\"\n" + "			}\n" + "		}\n" + "	]\n" + "}\n";
		String template3 = "{\n" + "	\"type\": \"system\",\n" + "	\"entity\": \"entity\",\n"
				+ "	\"customerId\": \"system\",\n" + "	\"law\" : \"CCPA\",\n" + "	\"fields\" : [ \n"
				+ "		{ \"field\" : \"representative_contact_details\", \"label\" : \"Representative Contact Details\", \"data_type\" :\"long-text\", \"default\" : \"Type contact details here..\", \"field_type\":	\"contact_details\", \"field_type_label\":	\"Contact Details\", \"is_removable\" : false, \"mandatory\": false},\n"
				+ "		{ \"field\" : \"data_protection_officer\", \"label\" : \"Data Protection Officer\", \"data_type\" :\"options\", \"default\" : \"Type the data protection officer name here..\", \"field_type\":	\"contact_details\", \"field_type_label\":	\"Contact Details\", \"is_removable\" : false, \"mandatory\": false,\n"
				+ "			\"options_url\": {\n" + "				\"url\" : \"dm/customer/<customer_id>/users\",\n"
				+ "				\"request_type\" : \"GET\"\n" + "			}\n" + "		}, \n"
				+ "		{ \"field\" : \"dpo_contact_details\", \"label\" : \"Data Protection Officer Contact Details\", \"data_type\" :\"long-text\", \"default\" : \"Type contact details here..\", \"field_type\":	\"contact_details\", \"field_type_label\":	\"Contact Details\", \"is_removable\" : false, \"mandatory\": false}\n"
				+ "	]\n" + "}\n";
		log.info(template1);

		log.info("Preloading " + repository.save(mapper.readValue(template1, Template.class)));
		log.info("Preloading " + repository.save(mapper.readValue(template2, Template.class)));
		log.info("Preloading " + repository.save(mapper.readValue(template3, Template.class)));
		// fetch all customers
		System.out.println("Templates found with findAll():");
		System.out.println("-------------------------------");
		for (Template template : repository.findAll()) {
			System.out.println(template);
		}
	}
}

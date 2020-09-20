package com.privado.template.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.privado.template.server.bean.Template;

/**
 * {@link MongoRepository} for database interactions of {@link Template}
 * 
 * @author denniskbijo
 *
 */
public interface TemplateRepository extends MongoRepository<Template, String> {

	/**
	 * Gets the Template for the given customerId
	 * 
	 * @param customerId
	 * @return
	 */
	public Template findByCustomerId(String customerId);
}

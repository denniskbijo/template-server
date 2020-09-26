package com.privado.template.server.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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
	@Query("{'customerId' : ?0}")
	public List<Template> findByCustomerId(String customerId);

}

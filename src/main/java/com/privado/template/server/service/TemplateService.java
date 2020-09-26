package com.privado.template.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.privado.template.server.bean.Template;
import com.privado.template.server.exception.DocumentNotFoundException;
import com.privado.template.server.repo.TemplateRepository;

/**
 * Business logic related to {@link Template}
 * 
 * @author denniskbijo
 *
 */
@Component
public class TemplateService {

	@Autowired
	private TemplateRepository repo;

	public Template getTemplateById(String id) {
		Optional<Template> optional = repo.findById(id);
		return optional.get();
	}

	public List<Template> getTemplates() {

		return repo.findAll();
	}

	/**
	 * Gets the Template for the given customerId
	 * 
	 * @param customerId
	 * @return
	 * @throws DocumentNotFoundException
	 */
	public Template getTemplateByCustomerId(String customerId) throws DocumentNotFoundException {

		List<Template> resultTemplates = repo.findByCustomerId(customerId);
		if (!resultTemplates.isEmpty())
			return resultTemplates.get(0);
		else
			throw new DocumentNotFoundException("No Template found for customer: " + customerId);
	}


}

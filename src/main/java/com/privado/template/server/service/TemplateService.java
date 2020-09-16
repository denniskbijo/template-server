package com.privado.template.server.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.privado.template.server.bean.Template;
import com.privado.template.server.repo.TemplateRepository;

@Component
public class TemplateService {
	@Autowired
	private TemplateRepository repo;

	public Template getTemplateById(String id){
		Optional<Template> optional = repo.findById(id);
		return optional.get();
	}
}

package com.privado.template.server.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.privado.template.server.bean.Template;

public interface TemplateRepository extends MongoRepository<Template, String> {

}

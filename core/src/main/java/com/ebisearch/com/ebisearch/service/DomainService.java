package com.ebisearch.com.ebisearch.service;

import com.ebisearch.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DomainService {

    Domain save(Domain domain);

    void delete(Domain domain);

    Domain findOne(String id);

    Iterable<Domain> findAll();

    Page<Domain> findByAuthor(String author, PageRequest pageRequest);

    List<Domain> findByTitle(String title);

}
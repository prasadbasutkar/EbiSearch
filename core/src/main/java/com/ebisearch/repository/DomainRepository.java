package com.ebisearch.repository;

import com.ebisearch.model.Domain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DomainRepository extends ElasticsearchRepository<Domain, String> {

    Page<Domain> findByAuthor(String author, Pageable pageable);

    List<Domain> findByTitle(String title);

}
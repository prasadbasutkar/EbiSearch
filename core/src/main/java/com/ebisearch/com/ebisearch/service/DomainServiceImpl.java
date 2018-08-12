package com.ebisearch.com.ebisearch.service;

import com.ebisearch.model.Domain;
import com.ebisearch.repository.DomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainServiceImpl implements DomainService {

    private DomainRepository domainRepository;

    @Autowired
    public void setDomainRepository(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public Domain save(Domain domain) {
        return domainRepository.save(domain);
    }

    public void delete(Domain domain) {
        domainRepository.delete(domain);
    }

    public Domain findOne(String id) {
        Optional<Domain> domains = domainRepository.findById(id);
        return domains.get();
    }

    public Iterable<Domain> findAll() {
        return domainRepository.findAll();
    }

    public Page<Domain> findByAuthor(String author, PageRequest pageRequest) {
        return domainRepository.findByAuthor(author, pageRequest);
    }

    public List<Domain> findByTitle(String title) {
        return domainRepository.findByTitle(title);
    }

}
package com.ebisearch.api;
import com.ebisearch.model.Domain;
import com.ebisearch.repository.DomainRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class DomainEndpoint {
    private DomainRepository domainRepository;

    public DomainEndpoint(DomainRepository domainRepository){
        this.domainRepository = domainRepository;
    }

    @GetMapping("/domains")
    public List<Domain> getHotels(){
        //List<Domain> domains = (List<Domain>) this.domainRepository.findAll();
        Iterable<Domain> domainsIter = this.domainRepository.findAll();
        return toList(domainsIter);
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}





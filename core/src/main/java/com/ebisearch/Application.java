package com.ebisearch;

import com.ebisearch.com.ebisearch.service.DomainService;
import com.ebisearch.model.Domain;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private DomainService domainService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

        domainService.save(new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        domainService.save(new Domain("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        domainService.save(new Domain("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));

        //fuzzey search
        Page<Domain> domains = domainService.findByAuthor("Rambabu", new PageRequest(0, 10));

        //List<Domain> domains = domainService.findByTitle("Elasticsearch Basics");

        domains.forEach(x -> System.out.println(x));


    }

    //useful for debug
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearch--");
    }

}
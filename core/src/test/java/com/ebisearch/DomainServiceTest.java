package com.ebisearch;
import com.ebisearch.com.ebisearch.service.DomainService;
import com.ebisearch.model.Domain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class DomainServiceTest {
    @Autowired
    private DomainService domainService;
    @Autowired
    private ElasticsearchTemplate esTemplate;
    @Before
    public void before() {
        esTemplate.deleteIndex(Domain.class);
        esTemplate.createIndex(Domain.class);
        esTemplate.putMapping(Domain.class);
        esTemplate.refresh(Domain.class);
    }
    @Test
    public void testSave() {
        Domain domain = new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        Domain testDomain = domainService.save(domain);
        Assert.assertNotNull(testDomain.getId());
        Assert.assertEquals(testDomain.getTitle(), domain.getTitle());
        Assert.assertEquals(testDomain.getAuthor(), domain.getAuthor());
        Assert.assertEquals(testDomain.getReleaseDate(), domain.getReleaseDate());
    }
    @Test
    public void testFindOne() {
        Domain domain = new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        domainService.save(domain);
        Domain testDomain = domainService.findOne(domain.getId());
        Assert.assertNotNull(testDomain.getId());
        Assert.assertEquals(testDomain.getTitle(), domain.getTitle());
        Assert.assertEquals(testDomain.getAuthor(), domain.getAuthor());
        Assert.assertEquals(testDomain.getReleaseDate(), domain.getReleaseDate());
    }
    @Test
    public void testFindByTitle() {
        Domain domain = new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        domainService.save(domain);
        List<Domain> byTitle = domainService.findByTitle(domain.getTitle());
        //Assert.assertThat(byTitle.size(), is(1));
    }
    @Test
    public void testFindByAuthor() {
        List<Domain> domainList = new ArrayList<>();
        domainList.add(new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017"));
        domainList.add(new Domain("1002", "Apache Lucene Basics", "Rambabu Posa", "13-MAR-2017"));
        domainList.add(new Domain("1003", "Apache Solr Basics", "Rambabu Posa", "21-MAR-2017"));
        domainList.add(new Domain("1007", "Spring Data + ElasticSearch", "Rambabu Posa", "01-APR-2017"));
        domainList.add(new Domain("1008", "Spring Boot + MongoDB", "Mkyong", "25-FEB-2017"));
        for (Domain domain : domainList) {
            domainService.save(domain);
        }
        Page<Domain> byAuthor = domainService.findByAuthor("Rambabu Posa", new PageRequest(0, 10));
        //Assert.assertThat(byAuthor.getTotalElements(), Assert.is(4L));
        Page<Domain> byAuthor2 = domainService.findByAuthor("Mkyong", new PageRequest(0, 10));
        //Assert.assertThat(byAuthor2.getTotalElements(), is(1L));
    }
    @Test
    public void testDelete() {
        Domain domain = new Domain("1001", "Elasticsearch Basics", "Rambabu Posa", "23-FEB-2017");
        domainService.save(domain);
        domainService.delete(domain);
        Domain testDomain = domainService.findOne(domain.getId());
        assertNull(testDomain);
    }
}

package coop.nisc.demo.elastic;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticSomeStringsRepository extends ElasticsearchRepository<SomeStringsDocument, String> {

    Iterable<SomeStringsDocument> findAllByNameIsLike(String name);

    Iterable<SomeStringsDocument> findAllBy(String search);
}

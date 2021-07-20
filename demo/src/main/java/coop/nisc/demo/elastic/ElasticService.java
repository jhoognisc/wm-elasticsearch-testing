package coop.nisc.demo.elastic;

import com.google.common.collect.Lists;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ElasticService {

    private final ElasticSomeStringsRepository elasticSomeStringsRepository;

    ElasticService(ElasticSomeStringsRepository elasticSomeStringsRepository) {
        this.elasticSomeStringsRepository = elasticSomeStringsRepository;
    }

    public List<SomeStringsDocument> getElasticResults() {
        //addDoc();

        //todo workflow index with few fields for workflow, task, service order data. test object instead of nested
        //todo main scenarios: search by task ids or workflow id
        // https://codecurated.com/blog/elasticsearch-text-vs-keyword/

        // todo ??? sync up questions ??? ///
        // are there any words we want keyword on vs text (keyword only does exact matching). leave all as text for now
        // service order details a list?

        // this is my value - keyword column "this is my value"
        // this is my value - text "this" "is"

        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("string")
                .field("name")
                .field("id")
                .field("resource.id")
                .type(MultiMatchQueryBuilder.Type.PHRASE_PREFIX);

        QueryBuilder nestQuery = QueryBuilders.multiMatchQuery("1")
                .field("id")
                .field("type");
        NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("resource", nestQuery, ScoreMode.None);

        elasticSomeStringsRepository.search(nestedQueryBuilder);

        Iterable<SomeStringsDocument> iterator =  elasticSomeStringsRepository.findAllByNameIsLike("string");
        List<SomeStringsDocument> list = new ArrayList<>();
        iterator.forEach(list::add);
        return list;
    }

    private void addDoc() {
        SomeStringsDocument test = new SomeStringsDocument();
        test.setName("service made with nested component");
        test.setResource(Lists.newArrayList(new Resource("1", "EMPLOYEE"),
                new Resource("2", "CONTRACTOR")));
        elasticSomeStringsRepository.save(test);
    }
}

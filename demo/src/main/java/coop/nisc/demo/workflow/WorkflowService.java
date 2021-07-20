package coop.nisc.demo.workflow;

import coop.nisc.demo.elastic.WorkflowRepository;
import coop.nisc.demo.workflow.document.Workflow;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkflowService {

    private final WorkflowRepository workflowRepository;

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    WorkflowService(WorkflowRepository workflowRepository,
                    ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.workflowRepository = workflowRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    public List<Workflow> getWorkflowResults(AdvancedSearchQuery advancedSearchQuery) {
        List<Workflow> workflows = new ArrayList<>();

        QueryBuilder advancedQuery = createQuery(advancedSearchQuery);

        //IMPORTANT must = AND, should = OR
        //https://stackoverflow.com/questions/25552321/or-and-and-operators-in-elasticsearch-query/25552608

        //termquery mustnot

        QueryBuilder quer2 = QueryBuilders.termQuery("serviceOrderDetails.customerNameKeyword", "Pam Customerman");

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("serviceOrderDetails.customerName", "Micheal"))
                .should(QueryBuilders.matchQuery("serviceOrderDetails.contactName", "Micheal"))
                .should(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("serviceOrderDetails.customerName", "Pam"))
                        .must(QueryBuilders.termQuery("serviceOrderDetails.contactName", "Jim")));

        //angular components query builder
        //query builder server project
        //smarttrack advanced search

        //todo make list of high level tasks
        //todo check if results sort by score

        //?? gradle apply plugin lombok, figure out which way is newer


        //
        //match all fields for quick search used with advanced search
        //    QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery("Jim", "*"));
        //date range queries
        //    QueryBuilders.rangeQuery("tasks.neededDate").from(1919572000000L).to(1919612000000L);
        //does not contain
        //    QueryBuilders.boolQuery().mustNot(QueryBuilders.matchQuery("serviceOrderDetails.contactName", "Jim"));
        //is
        //    QueryBuilders.termQuery("serviceOrderDetails.customerNameKeyword", "Pam Customerman");
        //is not
        //    QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery("serviceOrderDetails.customerNameKeyword", "Pam Customerman"));

//         (A or B) and (C or (D and E))
//        QueryBuilders.boolQuery()
//                .must(QueryBuilders.boolQuery()
//                    .should(QueryBuilders.matchQuery("A", "A"))
//                    .should(QueryBuilders.matchQuery("B", "B")))
//                .must(QueryBuilders.boolQuery()
//                    .should(QueryBuilders.matchQuery("C", "C"))
//                    .should(QueryBuilders.boolQuery()
//                        .must(QueryBuilders.matchQuery("D", "D"))
//                        .must(QueryBuilders.matchQuery("E", "E"))))
//                .must(QueryBuilders.matchQuery("_all", "E"));

        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(advancedQuery)
                .build();

        elasticsearchRestTemplate.search(searchQuery, Workflow.class, IndexCoordinates.of("workflow")).getSearchHits()
                .forEach(hit -> {
                    Workflow workflow = hit.getContent();
                    workflow.setScore(hit.getScore());
                    workflows.add(workflow);
                });

//        return workflowRepository.findServiceOrders("Jim");
        return workflows;
    }

    private QueryBuilder createQuery(AdvancedSearchQuery advancedSearchQuery) {
//        BoolQueryBuilder query = QueryBuilders.boolQuery();

        if (!advancedSearchQuery.getSearchQueryTreeOperation().equals("LEAF")) {
            BoolQueryBuilder query = QueryBuilders.boolQuery();
            advancedSearchQuery.getChildren().forEach(childQuery -> {
                if (advancedSearchQuery.getSearchQueryTreeOperation().equals("AND")) {
                    query.must(createQuery(childQuery));
                } else if (advancedSearchQuery.getSearchQueryTreeOperation().equals("OR")) {
                    query.should(createQuery(childQuery));
                }
            });
            return query;
        } else {
            switch (advancedSearchQuery.getSearchQuery().getValueComparisonType()) {
                case AFTER:
                    return QueryBuilders.rangeQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId())
                            .from(Long.parseLong(advancedSearchQuery.getSearchQuery().getValue()));
                case BEFORE:
                    return QueryBuilders.rangeQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId())
                            .to(Long.parseLong(advancedSearchQuery.getSearchQuery().getValue()));
                case CONTAINS:
                    return QueryBuilders.matchQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId(),
                            advancedSearchQuery.getSearchQuery().getValue());
                case DOES_NOT_CONTAIN:
                    return QueryBuilders.boolQuery().mustNot(QueryBuilders.matchQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId(),
                            advancedSearchQuery.getSearchQuery().getValue()));
                case IS:
                    return QueryBuilders.termQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId(),
                            advancedSearchQuery.getSearchQuery().getValue());
                case IS_NOT:
                    return QueryBuilders.boolQuery().mustNot(QueryBuilders.termQuery(advancedSearchQuery.getSearchQuery().getSearchOptionId(),
                            advancedSearchQuery.getSearchQuery().getValue()));
                default:
                    return null;
            }
        }
    }
}

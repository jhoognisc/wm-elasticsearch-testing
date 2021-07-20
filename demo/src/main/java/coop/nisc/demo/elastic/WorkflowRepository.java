package coop.nisc.demo.elastic;

import coop.nisc.demo.workflow.document.Workflow;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowRepository extends ElasticsearchRepository<Workflow, String> {

    //            "{\"match\": {\"sourceType\": \"?0\"}}]}}")
//    @Query("{" +
//              "\"bool\": {" +
//                "\"must\": [" +
//                  "{\"match\": {\"serviceOrderDetails.customerName\": \"?0\"}}" +
//                "]" +
//              "}" +
//            "}")

    @Query("{" +
              "\"multi_match\": {" +
                "\"query\": \"?0\"," +
                "\"fields\": [" +
                  "\"serviceOrderDetails.customerName\", " +
                  "\"serviceOrderDetails.contactName\"" +
                "]" +
              "}" +
            "}")
    List<Workflow> findServiceOrders(String name);
}

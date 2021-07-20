package coop.nisc.demo.workflow.document;

import coop.nisc.demo.workflow.document.ServiceOrderDetails;
import coop.nisc.demo.workflow.document.SourceType;
import coop.nisc.demo.workflow.document.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;
import java.util.UUID;

@Document(indexName = "workflow")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workflow {

    @Id
    private String id;

    private UUID domainUuid;

    private float score;

    private SourceType sourceType;

    @Field(type = FieldType.Object, includeInParent = true)
    private List<ServiceOrderDetails> serviceOrderDetails;

    @Field(type = FieldType.Object, includeInParent = true)
    private List<Task> tasks;
}

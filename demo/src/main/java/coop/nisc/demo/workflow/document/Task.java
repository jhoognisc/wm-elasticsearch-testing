package coop.nisc.demo.workflow.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    public String id;

    public String description;

    public String status;

    public boolean critical;

    public float estimatedHours;

    public String neededDate;

    public String priority;

    public String workGroup;

    public boolean locked;

    public String remarks;

    @Field(type = FieldType.Object, includeInParent = true)
    public List<Assignment> assignments;
}

package coop.nisc.demo.workflow.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    public UUID id;

    @Field(type = FieldType.Object, includeInParent = true)
    public AssigneeKey assigneeKey;

    public String assigneeName;

    public String scheduledStartDateTime;

    public float scheduledHours;

    public String actualStartDateTime;

    public String actualEndDateTime;
}

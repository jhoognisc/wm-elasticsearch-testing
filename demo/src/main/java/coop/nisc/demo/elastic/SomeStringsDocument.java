package coop.nisc.demo.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Score;

import java.util.List;

@Document(indexName = "somestrings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SomeStringsDocument {

    @Id
    public String id;

    @Score
    public Float score;

    public String name;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Resource> resource;

    public void setName(String name) {
        this.name = name;
    }

    public void setResource(List<Resource> resource) {
        this.resource = resource;
    }
}

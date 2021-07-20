package coop.nisc.demo.elastic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class Resource {

    public String id;

    public String type;

    public Resource(String id, String type) {
        this.id = id;
        this.type = type;
    }
}

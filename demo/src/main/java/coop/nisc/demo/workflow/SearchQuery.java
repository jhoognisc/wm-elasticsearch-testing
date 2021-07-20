package coop.nisc.demo.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQuery {

    private String searchOptionId;

    private String value;

    private ValueComparisonType valueComparisonType;
}

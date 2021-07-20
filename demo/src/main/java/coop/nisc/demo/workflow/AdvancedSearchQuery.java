package coop.nisc.demo.workflow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvancedSearchQuery {

    private SearchQuery searchQuery;

    private String searchQueryTreeOperation;

    private List<AdvancedSearchQuery> children;
}

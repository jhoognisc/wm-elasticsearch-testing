package coop.nisc.demo.workflow;

import coop.nisc.demo.workflow.document.Workflow;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @GetMapping
    ResponseEntity<List<Workflow>> getWorkflowResults(@RequestHeader Map<String, String> headers,
                                                      @RequestBody AdvancedSearchQuery advancedSearchQuery) {

        return ResponseEntity.ok(workflowService.getWorkflowResults(advancedSearchQuery));
    }
}

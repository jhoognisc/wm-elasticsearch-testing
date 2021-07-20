package coop.nisc.demo.elastic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("elastic")
public class ElasticController {

    private final ElasticService elasticService;

    ElasticController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @GetMapping
    ResponseEntity<List<SomeStringsDocument>> getCurrentAndFutureCrewRotations(@RequestHeader Map<String, String> headers) {

        return ResponseEntity.ok(elasticService.getElasticResults());
    }
}

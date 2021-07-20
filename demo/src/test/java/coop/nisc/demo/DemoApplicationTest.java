package coop.nisc.demo;

import coop.nisc.metrics.spring.prometheus.PrometheusAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *  Unit tests for {@link DemoApplication}.
 */
@SpringBootTest
@EnableAutoConfiguration(exclude = {
		PrometheusAutoConfiguration.class
})
public class DemoApplicationTest {

    @Test
    public void contextLoads() {
        // Confirms that the application context can be loaded successfully
    }

}

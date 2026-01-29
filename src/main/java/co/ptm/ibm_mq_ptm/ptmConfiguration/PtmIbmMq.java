package co.ptm.ibm_mq_ptm.ptmConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class PtmIbmMq implements EnvironmentPostProcessor {
    private static final String SOURCE_PREFIX = "ptm.ibm.mq.";
    private static final String TARGET_PREFIX = "ibm.mq.";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment env, SpringApplication application) {

        Map<String, Object> mapped = new HashMap<>();

        for (PropertySource<?> ps : env.getPropertySources()) {
            if (!(ps instanceof EnumerablePropertySource<?> eps)) {
                continue;
            }

            for (String name : eps.getPropertyNames()) {
                if (name.startsWith(SOURCE_PREFIX)) {
                    String targetName =
                            TARGET_PREFIX + name.substring(SOURCE_PREFIX.length());

                    Object value = eps.getProperty(name);
                    mapped.put(targetName, value);
                }
            }
        }

        if (!mapped.isEmpty()) {
            env.getPropertySources()
                    .addFirst(new MapPropertySource("ibm-mq-alias", mapped));
        }
    }
}

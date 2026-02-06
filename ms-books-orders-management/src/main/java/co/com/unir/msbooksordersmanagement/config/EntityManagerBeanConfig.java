package co.com.unir.msbooksordersmanagement.config;

import io.github.perplexhub.rsql.RSQLCommonSupport;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.SharedEntityManagerCreator;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Configuration
public class EntityManagerBeanConfig {

    @Bean
    @Primary
    public EntityManager entityManager(EntityManagerFactory emf) {
        return SharedEntityManagerCreator.createSharedEntityManager(emf);
    }

    @Bean
    public RSQLCommonSupport rsqlCommonSupport(Map<String, EntityManager> entityManagerMap) {
        return new RSQLCommonSupport(entityManagerMap);
    }

    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
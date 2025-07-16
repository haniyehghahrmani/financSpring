package org.java.financespring.configuration;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.java.financespring.repository.h2repo",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager"
)
public class H2Config {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean h2EntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(h2DataSource())
                .packages("org.java.financespring.model.h2Model")
                .persistenceUnit("h2")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager h2TransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(h2EntityManager(builder).getObject());
    }
}

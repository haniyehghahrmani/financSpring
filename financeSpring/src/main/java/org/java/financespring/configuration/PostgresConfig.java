package org.java.financespring.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.java.financespring.repository.pgrepo",
        entityManagerFactoryRef = "pgEntityManager",
        transactionManagerRef = "pgTransactionManager"
)
public class PostgresConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource pgDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean pgEntityManager(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(pgDataSource())
                .packages("org.java.financespring.model.pgModel")
                .persistenceUnit("postgres")
                .build();
    }

    @Bean
    public PlatformTransactionManager pgTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(pgEntityManager(builder).getObject());
    }
}

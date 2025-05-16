//package org.java.financespring.configuration;
//
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.orm.jpa.vendor.Database;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableTransactionManagement
//public class MultiDatabaseConfig {
//
//    @Primary
//    @Bean(name = "postgresqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
//    public DataSource postgresqlDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "entityManagerFactory")
////    @Primary
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            @Qualifier("postgresqlDataSource") DataSource dataSource) {
//        return postgresqlEntityManagerFactory(dataSource);
//    }
//
//
//    @Bean(name = "postgresqlEntityManagerFactory")
//    @Primary
//    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
//            @Qualifier("postgresqlDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("org.java.financespring");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.POSTGRESQL);
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "postgresqlTransactionManager")
//    public PlatformTransactionManager postgresqlTransactionManager(
//            @Qualifier("postgresqlEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory.getObject());
//    }
//
//    @Bean(name = "oracleDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.oracle")
//    public DataSource oracleDataSource() {
//        return DataSourceBuilder.create().type(HikariDataSource.class).build();
//    }
//
//    @Bean(name = "oracleEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(
//            @Qualifier("oracleDataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("org.java.financespring");
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setDatabase(Database.ORACLE);
//        em.setJpaVendorAdapter(vendorAdapter);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
////        properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
//        em.setJpaPropertyMap(properties);
//
//        return em;
//    }
//
//    @Bean(name = "oracleTransactionManager")
//    public PlatformTransactionManager oracleTransactionManager(
//            @Qualifier("oracleEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory.getObject());
//    }
//}

package com.devsmile.springdata.config.address;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.devsmile.springdata.dao.address", entityManagerFactoryRef = "addressEntityManager",
        transactionManagerRef = "addressTransactionManager"
)
public class DataSourceAddressConfig {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource addressDataSourse() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("database2.datasource1.driver-class-name"));
        dataSource.setUrl(environment.getProperty("database2.datasource1.url"));
        dataSource.setUsername(environment.getProperty("database2.datasource1.username"));
        dataSource.setPassword(environment.getProperty("database2.datasource1.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean addressEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(addressDataSourse());
        em.setPackagesToScan("com.devsmile.springdata.model.user");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put("hibenate.show-sql", environment.getProperty("spring.jpa.show-sql"));

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();

        return em;
    }

    @Bean
    public PlatformTransactionManager addressTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(addressEntityManager().getObject());
        return transactionManager;
    }
}

package com.devsmile.springdata.config.user;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.devsmile.springdata.config.Constants;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.devsmile.springdata.dao.user", entityManagerFactoryRef = "userEntityManager",
        transactionManagerRef = "userTransactionManager"
)
public class DataSourceUserConfig {

    @Autowired
    private Environment environment;

    @Bean
    @Primary
    public DataSource userDataSourse() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("database1.datasource.driver-class-name"));
        dataSource.setUrl(environment.getProperty("database1.datasource.url"));
        dataSource.setUsername(environment.getProperty("database1.datasource.username"));
        dataSource.setPassword(environment.getProperty("database1.datasource.password"));
        return dataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(userDataSourse());

        em.setPackagesToScan(new String[] { Constants.PACKAGE_ENTITIES_USER });
        em.setPersistenceUnitName(Constants.JPA_UNIT_USER);

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
    @Primary
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(userEntityManager().getObject());
        return transactionManager;
    }
}

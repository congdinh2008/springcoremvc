package com.congdinh;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.congdinh")
public class HibernateConfig {
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();// IoC
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl(
                "jdbc:sqlserver://localhost:1433;databaseName=springcoremvc_dev;encrypt=true;trustServerCertificate=true;loginTimeout=30;");
        dataSource.setUsername("sa");
        dataSource.setPassword("abcd@1234");
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();// IoC
        sessionFactory.setDataSource(dataSource()); // DI - Set DataSource nhu 1 bean
        sessionFactory.setPackagesToScan("com.congdinh.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }

    // Đăng ký bean này để quản lý transaction tự động
    @Bean
    public PlatformTransactionManager hibernTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();// IoC
        transactionManager.setSessionFactory(sessionFactory().getObject()); // DI - Set SessionFactory nhu 1 bean
        return transactionManager;
    }
}

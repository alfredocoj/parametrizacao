package com.uema.config;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

// Permite o controle transacional. Annotation definida pelo Spring
//
@EnableTransactionManagement
public class JPAConfiguration {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "12qwaszx";
    private static final String DATABASE = "parametrizacao";

    private JpaTransactionManager transactionManager = new JpaTransactionManager();

    // Indicamos qual implementação de controle transacional queremos utilizar
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) throws BeansException {
        this.transactionManager.setEntityManagerFactory(emf);
        System.out.println("I got the factory!");
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.uema.parametrizacao.entities","com.uema.admin.entities"});

        // Definimos aqui a implementação da JPA escolhida para tratar com os
        // dados, no caso será o Hibernate
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    // Método responsável pela configuração de conexão com o banco de dados
    @Bean
    private DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
}
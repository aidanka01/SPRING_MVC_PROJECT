package peaksoft.config;


import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class HibernateConfig {

    private final Environment env;

    @Autowired
    public HibernateConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan("peaksoft.entity");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(jpaProperties());
        return em;
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new JpaTransactionManager(
                Objects.requireNonNull(getEntityManagerFactoryBean().getObject()));
    }

    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("db.hibernate.show_sql"));
        properties.put("hibernate.format_sql", env.getProperty("db.hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.hbm2ddl.auto"));
        return properties;
    }
}

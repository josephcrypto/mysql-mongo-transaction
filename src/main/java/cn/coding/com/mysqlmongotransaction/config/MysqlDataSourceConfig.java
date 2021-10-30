package cn.coding.com.mysqlmongotransaction.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "MYSQL_ENTITY_MANAGER_FACTORY",
transactionManagerRef = "MYSQL_PLATFORM_TX_MANAGER", basePackages = {"cn.coding.com.mysqlmongotransaction.repository.mysql"})
public class MysqlDataSourceConfig {

    public static final String MYSQL_JPA_PROS = "mysql.jpa";
    public static final String MYSQL_DATASOURCE = "mysql.datasource";
    public static final String MYSQL_PERSISTENCE_UNIT = "MYSQL_PERSISTENCE_UNIT";
    public static final String MYSQL_ENTITY_MANAGER = "MYSQL_ENTITY_MANAGER";
    public static final String MYSQL_ENTITY_MANAGER_FACTORY = "MYSQL_ENTITY_MANAGER_FACTORY";
    public static final String MYSQL_PLATFORM_TX_MANAGER = "MYSQL_PLATFORM_TX_MANAGER";


    @Primary
    @Bean(name = MYSQL_JPA_PROS)
    @ConfigurationProperties(prefix = MYSQL_JPA_PROS)
    public JpaProperties mysqlJpaProperties(){
        return new JpaProperties();
    }

    @Primary
    @Bean
//    @ConfigurationProperties(prefix = MYSQL_DATASOURCE)
    public DataSourceProperties  mysqlDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = MYSQL_DATASOURCE)
    @ConfigurationProperties(prefix = MYSQL_DATASOURCE)
    public DataSource mysqlDataSource(){
        return mysqlDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = MYSQL_ENTITY_MANAGER)
    public EntityManager mysqlEntityManager(@Qualifier(MYSQL_ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory){
        return entityManagerFactory.createEntityManager();
    }

    @Primary
    @Bean(name = MYSQL_ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(mysqlDataSource())
                .packages("cn.coding.com.mysqlmongotransaction.entity.mysql")
                .persistenceUnit(MYSQL_PERSISTENCE_UNIT)
                .properties(mysqlJpaProperties().getProperties())
                .build();
    }

    @Primary
    @Bean(name = MYSQL_PLATFORM_TX_MANAGER)
    public PlatformTransactionManager mysqlPlatformTransactionManager(@Qualifier(MYSQL_ENTITY_MANAGER_FACTORY) EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

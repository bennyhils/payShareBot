package ru.payShare.configuration;

import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jooq.JooqExceptionTranslator;
import org.springframework.boot.autoconfigure.jooq.JooqProperties;
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@EnableConfigurationProperties({ JooqProperties.class })
@Configuration
public class DataSourceConfiguration {

    @Bean("dataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("hikariDataSource")
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource hikariDataSource(
            @Qualifier("dataSourceProperties") DataSourceProperties replicaDataSourceProperties) {
        return replicaDataSourceProperties
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("hikariDataSource") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean("dslContext")
    public DSLContext dslContext(
            @Qualifier("hikariDataSource") DataSource dataSource,
            @Qualifier("transactionManager") PlatformTransactionManager txManager,
            JooqProperties properties) {
        DefaultConfiguration config = new DefaultConfiguration();
        config.set(new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource)));
        config.set(new DefaultExecuteListenerProvider(new JooqExceptionTranslator()));
        config.set(new SpringTransactionProvider(txManager));
        config.set(properties.determineSqlDialect(dataSource));
        return new DefaultDSLContext(config);
    }
}

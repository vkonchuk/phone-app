package com.vkonchuk.catalog.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource.catalog")
    public DataSource catalogDataSource() {
        return DataSourceBuilder.create().build();
    }

}

package com.example.demo.utils;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceSqlServer {

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://localhost:1433;databaseName=coupondb;encrypt=true;trustServerCertificate=true");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("Stefano1994.");
        return dataSourceBuilder.build();
    }
}

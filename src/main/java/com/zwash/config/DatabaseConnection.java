package com.zwash.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import javax.sql.DataSource;
import org.postgresql.Driver;



public class DatabaseConnection {
    private static HikariDataSource dataSource;

    public static Connection getConnection() throws Exception {
    	
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setDriverClassName(Driver.class.getName());
            config.setJdbcUrl("jdbc:postgresql://127.0.0.1:5433/zwash");
            config.setUsername("zeriab");
            config.setPassword("password");
            config.setPoolName("pool-1");
            config.setMaximumPoolSize(1);
            config.setMaxLifetime(300000); 
            config.setLeakDetectionThreshold(5000);
            config.setConnectionTimeout(1000);

            dataSource = new HikariDataSource(config);
        }

        return dataSource;
    }

    static void closeDataSource() {
        dataSource.close();
        dataSource = null;
    }

    private DatabaseConnection() {}
}

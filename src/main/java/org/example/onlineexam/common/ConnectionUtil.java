package org.example.onlineexam.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public enum ConnectionUtil {
    INSTANCE;

    ConnectionUtil(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:13306/miniexam");
        config.setUsername("boris");
        config.setPassword("boris");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setConnectionTimeout(1000*30);
        config.setMaximumPoolSize(20); //WAS의 쓰레드 개수
        config.setMinimumIdle(1); // 처음부터 연결을 많이 하면 비용 문제

        ds = new HikariDataSource(config);
    }

    private HikariDataSource ds;


    public HikariDataSource getDs() {
        return ds;
    }
}

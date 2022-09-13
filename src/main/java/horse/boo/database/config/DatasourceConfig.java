package horse.boo.database.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DatasourceConfig {
    private DatasourceConfig(){

    }

    public static DataSource createDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(SqlDatasourceConfig.URL);
        config.setUsername(SqlDatasourceConfig.USER);
        config.setPassword(SqlDatasourceConfig.PASSWORD);
        config.setAutoCommit(true);
        config.setMaximumPoolSize(32);
        return new HikariDataSource(config);
    }
}

package config;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public DbConnection() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(SqlDatasourceConfig.URL,
                SqlDatasourceConfig.USER,
                SqlDatasourceConfig.PASSWORD);
    }
    public DSLContext getContext(Connection connection) {
        return DSL.using(connection, SqlDatasourceConfig.DIALECT);
    }
}

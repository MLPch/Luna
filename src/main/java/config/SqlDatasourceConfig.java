package config;

import org.jooq.SQLDialect;
import repository.PonyRepository;
import repository.PonyRepositoryImpl;

public class SqlDatasourceConfig {
    public static final String URL ="jdbc:postgresql://localhost:5432/ponybase";
    public static final String USER ="postgres";
    public static final String PASSWORD ="PonyPass";
    public static final SQLDialect DIALECT = SQLDialect.POSTGRES;

    public static PonyRepository ponyRepository() {
        return new PonyRepositoryImpl(new DbConnection());
    }
}

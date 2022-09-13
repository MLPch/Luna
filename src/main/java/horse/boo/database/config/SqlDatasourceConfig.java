package horse.boo.database.config;

import org.jooq.SQLDialect;
import horse.boo.database.repository.PonyRepository;
import horse.boo.database.repository.PonyRepositoryImpl;

public class SqlDatasourceConfig {
    public static final String URL ="jdbc:postgresql://localhost:5432/ponybase";
    public static final String USER ="postgres";
    public static final String PASSWORD ="PonyPass";
    public static final SQLDialect DIALECT = SQLDialect.POSTGRES;

    public static PonyRepository ponyRepository() {
        return new PonyRepositoryImpl(new DbConnection());
    }
}

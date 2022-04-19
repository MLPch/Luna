package horse.boo.database.repository;


import horse.boo.database.config.DatasourceConfig;
import horse.boo.database.config.DbConnection;
import horse.boo.database.jooq.model.tables.Ponies;
import horse.boo.database.model.Pony;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import horse.boo.database.repository.mapper.PonyMapper;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PonyRepositoryImpl implements PonyRepository {
    private static final Logger log = LoggerFactory.getLogger(PonyRepositoryImpl.class);

    private final DbConnection dbConnection;

    public PonyRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Pony> findAll() {
        try (var connection = dbConnection.getConnection()) {
            DSLContext ctxt = dbConnection.getContext(connection);
            Result<Record> ponies = ctxt.select()
                    .from(Ponies.PONIES)
                    .fetch();

            return ponies.stream()
                    .map(PonyMapper::recordToModel)
                    .collect(Collectors.toList());
        } catch (SQLException ex) {
            log.error("Something went wrong", ex);
        }
        return List.of();
    }

    @Override
    public void addNextLineInBase(Integer id, String name, Integer age) {
        try (var connection = dbConnection.getConnection()) {
            DSLContext ctxt = dbConnection.getContext(connection);
//        DataSource dataSource = DatasourceConfig.createDataSource();
        ctxt.insertInto(Ponies.PONIES)
                .set(Ponies.PONIES.PONIES_ID, id)
                .set(Ponies.PONIES.PONIES_NAME, name)
                .set(Ponies.PONIES.PONIES_AGE, age)
                .execute();
        } catch (SQLException ex) {
            log.error("Something went wrong", ex);
        }
    }

    @Override
    public Optional<Pony> findById(@NotNull String id) {
        if (id.isBlank()) {
            return Optional.empty();
        }
        try (var connection = dbConnection.getConnection()) {
            DSLContext ctxt = dbConnection.getContext(connection);
            Record pony = ctxt.select()
                    .from(Ponies.PONIES)
                    .where(Ponies.PONIES.PONIES_ID.eq(Integer.valueOf(id)))
                    .fetchOne();

            return Optional.ofNullable(PonyMapper.recordToModel(pony));
        } catch (SQLException ex) {
            log.error("Something went wrong", ex);
        }
        return Optional.empty();
    }
}

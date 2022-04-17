package repository;


import config.DbConnection;
import jooq.model.tables.Ponies;
import model.Pony;
import org.jetbrains.annotations.NotNull;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.mapper.PonyMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PonyRepositoryImpl implements PonyRepository {
    private static final Logger log = LoggerFactory.getLogger(PonyRepositoryImpl.class);

    private DbConnection dbConnection;

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

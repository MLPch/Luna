/*
 * This file is generated by jOOQ.
 */
package horse.boo.database.jooq.model.tables;


import horse.boo.database.jooq.model.Keys;
import horse.boo.database.jooq.model.Public;
import horse.boo.database.jooq.model.tables.records.PoniesRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ponies extends TableImpl<PoniesRecord> {

    private static final long serialVersionUID = -1361014408;

    /**
     * The reference instance of <code>public.ponies</code>
     */
    public static final Ponies PONIES = new Ponies();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PoniesRecord> getRecordType() {
        return PoniesRecord.class;
    }

    /**
     * The column <code>public.ponies.id</code>.
     */
    public final TableField<PoniesRecord, Integer> PONIES_ID = createField(DSL.name("id"),
        org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('ponies_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.ponies.name</code>.
     */
    public final TableField<PoniesRecord, String> PONIES_NAME = createField(DSL.name("name"),
        org.jooq.impl.SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>public.ponies.age</code>.
     */
    public final TableField<PoniesRecord, Integer> PONIES_AGE = createField(DSL.name("age"),
        org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * Create a <code>public.ponies</code> table reference
     */
    public Ponies() {
        this(DSL.name("ponies"), null);
    }

    /**
     * Create an aliased <code>public.ponies</code> table reference
     */
    public Ponies(String alias) {
        this(DSL.name(alias), PONIES);
    }

    /**
     * Create an aliased <code>public.ponies</code> table reference
     */
    public Ponies(Name alias) {
        this(alias, PONIES);
    }

    private Ponies(Name alias, Table<PoniesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Ponies(Name alias, Table<PoniesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Ponies(Table<O> child, ForeignKey<O, PoniesRecord> key) {
        super(child, key, PONIES);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<PoniesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_PONIES;
    }

    @Override
    public UniqueKey<PoniesRecord> getPrimaryKey() {
        return Keys.PONIES_PKEY;
    }

    @Override
    public List<UniqueKey<PoniesRecord>> getKeys() {
        return Arrays.<UniqueKey<PoniesRecord>>asList(Keys.PONIES_PKEY);
    }

    @Override
    public Ponies as(String alias) {
        return new Ponies(DSL.name(alias), this);
    }

    @Override
    public Ponies as(Name alias) {
        return new Ponies(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ponies rename(String name) {
        return new Ponies(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ponies rename(Name name) {
        return new Ponies(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}

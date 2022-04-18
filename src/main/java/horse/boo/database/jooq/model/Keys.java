/*
 * This file is generated by jOOQ.
 */
package horse.boo.database.jooq.model;


import horse.boo.database.jooq.model.tables.records.PoniesRecord;
import horse.boo.database.jooq.model.tables.Ponies;
import org.jooq.Identity;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<PoniesRecord, Integer> IDENTITY_PONIES = Identities0.IDENTITY_PONIES;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<PoniesRecord> PONIES_PKEY = UniqueKeys0.PONIES_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<PoniesRecord, Integer> IDENTITY_PONIES =
            Internal.createIdentity(Ponies.PONIES, Ponies.PONIES.PONIES_ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<PoniesRecord> PONIES_PKEY =
            Internal.createUniqueKey(Ponies.PONIES, "ponies_pkey", new TableField[] { Ponies.PONIES.PONIES_ID }, true);
    }
}
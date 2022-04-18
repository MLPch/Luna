package horse.boo.database.repository.mapper;

import horse.boo.database.jooq.model.tables.Ponies;
import horse.boo.database.model.Pony;
import org.jooq.Record;

public class PonyMapper {

  public static Pony recordToModel(Record record) {
    if (record == null) {
      return null;
    }
    return new Pony()
        .setId(record.getValue(Ponies.PONIES.PONIES_ID))
        .setName(record.getValue(Ponies.PONIES.PONIES_NAME))
        .setAge(record.getValue(Ponies.PONIES.PONIES_AGE));
  }
}

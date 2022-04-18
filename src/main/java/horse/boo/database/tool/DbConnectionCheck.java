package horse.boo.database.tool;

import horse.boo.database.config.SqlDatasourceConfig;
import horse.boo.database.model.Pony;
import horse.boo.database.repository.PonyRepository;

import java.util.List;

public class DbConnectionCheck {
  public static void main(String[] args) {
    PonyRepository repository = SqlDatasourceConfig.ponyRepository();
    List<Pony> ponies = repository.findAll();
    ponies.forEach(System.out::println);
  }
}

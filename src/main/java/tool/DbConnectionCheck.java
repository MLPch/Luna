package tool;

import config.SqlDatasourceConfig;
import model.Pony;
import repository.PonyRepository;

import java.util.List;

public class DbConnectionCheck {
  public static void main(String[] args) {
    PonyRepository repository = SqlDatasourceConfig.ponyRepository();
    List<Pony> ponies = repository.findAll();
    ponies.forEach(System.out::println);
  }
}

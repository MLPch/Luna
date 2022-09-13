package horse.boo.database.repository;

import horse.boo.database.model.Pony;

import java.util.List;
import java.util.Optional;

public interface PonyRepository {
    List<Pony> findAll();
    void addNextLineInBase(Integer id, String name, Integer age);
    Optional<Pony> findById(String id);
}

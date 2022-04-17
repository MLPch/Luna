package repository;

import model.Pony;

import java.util.List;
import java.util.Optional;

public interface PonyRepository {
    List<Pony> findAll();
    Optional<Pony> findById(String id);
}

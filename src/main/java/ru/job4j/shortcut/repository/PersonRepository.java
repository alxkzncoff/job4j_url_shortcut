package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    @Query("SELECT DISTINCT p FROM Person p WHERE p.username = ?1")
    Optional<Person> findByUsername(String username);

    @Override
    List<Person> findAll();
}

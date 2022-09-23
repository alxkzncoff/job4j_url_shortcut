package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Person;
import ru.job4j.shortcut.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PersonRepository store;

    public UserService(PersonRepository store) {
        this.store = store;
    }

    public Person save(Person person) {
        return store.save(person);
    }

    public Optional<Person> findById(int id) {
        return store.findById(id);
    }

    public Optional<Person> findByUsername(String username) {
        return store.findByUsername(username);
    }

    public List<Person> findAll() {
        return store.findAll();
    }

    public void delete(Person person) {
        store.delete(person);
    }
}

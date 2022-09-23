package ru.job4j.shortcut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Person;
import ru.job4j.shortcut.repository.PersonRepository;

import java.util.Optional;
import static java.util.Collections.emptyList;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository store;

    public UserDetailsServiceImpl(PersonRepository store) {
        this.store = store;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = store.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.get().getUsername(), user.get().getPassword(), emptyList());
    }
}

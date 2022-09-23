package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.domain.Person;
import ru.job4j.shortcut.repository.LinkRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository store;

    public LinkService(LinkRepository store) {
        this.store = store;
    }

    public Link save(Link link) {
        return store.save(link);
    }

    public Optional<Link> findByUrl(String url) {
        return store.findByUrl(url);
    }

    public Optional<Link> findByCode(String code) {
        return store.findByCode(code);
    }

    public List<Link> findAllByUserId(Person person) {
        return store.findAllByUserId(person);
    }

    public void incrementViews(String code) {
        store.incrementViews(code);
    }
}

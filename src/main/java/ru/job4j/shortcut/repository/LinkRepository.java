package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.domain.Person;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Integer> {

    @Query("SELECT DISTINCT l FROM Link l WHERE l.url = ?1")
    Optional<Link> findByUrl(String url);

    @Query("SELECT DISTINCT l FROM Link l WHERE l.code = ?1")
    Optional<Link> findByCode(String code);

    @Query("SELECT DISTINCT l FROM Link l WHERE l.person = ?1")
    List<Link> findAllByUserId(Person person);

    @Modifying
    @Transactional
    @Query("UPDATE Link l set l.views = l.views + 1 where l.code = ?1")
    void incrementViews(String code);
}

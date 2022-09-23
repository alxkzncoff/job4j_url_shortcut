package ru.job4j.shortcut.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "of")
@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;
    private String code;
    private int views = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Link link = (Link) o;
        return id == link.id && Objects.equals(url, link.url)
                && Objects.equals(code, link.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, code);
    }
}

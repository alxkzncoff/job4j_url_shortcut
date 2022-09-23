package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegRespDTO {
    private String login;
    private String password;
    private boolean registration;
}

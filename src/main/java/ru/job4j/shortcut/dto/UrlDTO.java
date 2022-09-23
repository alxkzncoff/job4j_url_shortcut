package ru.job4j.shortcut.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UrlDTO {

    @NotBlank(message = "Field url must not be empty")
    private String url;
}

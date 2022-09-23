package ru.job4j.shortcut.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegReqDTO {

    @NotBlank(message = "Field site must not be empty")
    private String site;
}

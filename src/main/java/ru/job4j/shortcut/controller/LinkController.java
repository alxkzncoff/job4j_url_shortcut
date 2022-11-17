package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.dto.CodeDTO;
import ru.job4j.shortcut.domain.Link;
import ru.job4j.shortcut.dto.StatisticDTO;
import ru.job4j.shortcut.dto.UrlDTO;
import ru.job4j.shortcut.service.LinkService;
import ru.job4j.shortcut.service.UserService;
import ru.job4j.shortcut.util.PasswordGenerator;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/links")
public class LinkController {

    private final PasswordGenerator passwordGenerator;
    private final UserService userService;
    private final LinkService linkService;

    public LinkController(PasswordGenerator passwordGenerator, UserService userService, LinkService linkService) {
        this.passwordGenerator = passwordGenerator;
        this.userService = userService;
        this.linkService = linkService;
    }

    @PostMapping("/convert")
    public ResponseEntity<CodeDTO> convert(@Valid @RequestBody UrlDTO urlDTO)
            throws MalformedURLException, URISyntaxException {
        var url = new URL(urlDTO.getUrl()).toURI();
        if (linkService.findByUrl(url.toString()).isPresent()) {
            throw new IllegalArgumentException("URL has already added to the service.");
        }
        var code = passwordGenerator.generate(6);
        var currentUser = userService.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Login before use service."));
        var link = Link.of()
                .url(url.toString())
                .code(code)
                .person(currentUser)
                .build();
        linkService.save(link);
        return ResponseEntity.status(HttpStatus.OK).body(new CodeDTO(code));
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        Link link = linkService.findByCode(code).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Wrong url code or url have not registered yet."));
        linkService.incrementViews(code);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header("Location", link.getUrl()).build();
    }

    @GetMapping("/statistic")
    public List<StatisticDTO> statistic() {
        var currentUser = userService.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Login before use service."));
        var statistic = new ArrayList<StatisticDTO>();
        linkService.findAllByUserId(currentUser).forEach(
                link -> statistic.add(new StatisticDTO(link.getUrl(), link.getViews()))
        );
        return statistic;
    }
}

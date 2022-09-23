package ru.job4j.shortcut.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.domain.Person;
import ru.job4j.shortcut.dto.RegReqDTO;
import ru.job4j.shortcut.dto.RegRespDTO;
import ru.job4j.shortcut.service.UserService;
import ru.job4j.shortcut.util.PasswordGenerator;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

@RestController
@RequestMapping("/users")
public class UserController {

    private final PasswordGenerator passwordGenerator;
    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    public UserController(PasswordGenerator passwordGenerator, UserService userService, BCryptPasswordEncoder encoder) {
        this.passwordGenerator = passwordGenerator;
        this.userService = userService;
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    public ResponseEntity<RegRespDTO> registration(@Valid @RequestBody RegReqDTO regReq)
            throws MalformedURLException, URISyntaxException {
        var url = new URL(regReq.getSite()).toURI();
        var username = url.getHost().replace(".", "_");
        var regResp = new RegRespDTO(username, null, false);
        var status = HttpStatus.CONFLICT;
        if (userService.findByUsername(username).isEmpty()) {
            var password = passwordGenerator.generate(12);
            var user = Person.of()
                    .username(username)
                    .password(encoder.encode(password))
                    .build();
            userService.save(user);
            regResp.setPassword(password);
            regResp.setRegistration(true);
            status = HttpStatus.CREATED;
        }
        return ResponseEntity.status(status).body(regResp);
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}

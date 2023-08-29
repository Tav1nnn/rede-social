package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.services.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/user/v1")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid InsertUserDto dto) {

        var dtoCreated = service.createUser(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getKey()).toUri();

        return ResponseEntity.created(uri).body(dtoCreated);

    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){

        var userDto = service.findById(id);

        return ResponseEntity.ok().body(userDto);
    }
}

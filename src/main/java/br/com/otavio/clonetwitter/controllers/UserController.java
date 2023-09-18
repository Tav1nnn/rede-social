package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.dto.TokenDTO;
import br.com.otavio.clonetwitter.dto.user.AuthUserDto;
import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.services.JwtService;
import br.com.otavio.clonetwitter.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/user/v1")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtService jwtService;

    public UserController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/register")
    public void createUser(@RequestBody @Valid InsertUserDto dto, HttpServletResponse response) {
        service.createUser(dto);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AuthUserDto dto){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        var authentication = manager.authenticate(authenticationToken);

        UserEntity entity = (UserEntity) authentication.getPrincipal();

        return ResponseEntity.ok().body(jwtService.createAcessToken(entity.getUsername(), entity.getRole()));
    }

    @GetMapping(value = "findbyid/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        var userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }
}

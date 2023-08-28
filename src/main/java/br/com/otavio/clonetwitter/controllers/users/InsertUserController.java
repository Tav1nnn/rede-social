package br.com.otavio.clonetwitter.controllers.users;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.services.users.InsertUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/v1")
public class InsertUserController {

    @Autowired
    private InsertUserService service;

    @PostMapping(value = "/cadastrar")
    public UserDto insertUser(@RequestBody @Valid InsertUserDto dto) {
        return service.insertUser(dto);

    }
}

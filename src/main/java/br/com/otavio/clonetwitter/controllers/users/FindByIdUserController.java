package br.com.otavio.clonetwitter.controllers.users;

import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.services.users.FindByIdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/v1")
public class FindByIdUserController {

    @Autowired
    private FindByIdUserService service;

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){

        var userDto = service.findById(id);

        return ResponseEntity.ok().body(userDto);
    }
}

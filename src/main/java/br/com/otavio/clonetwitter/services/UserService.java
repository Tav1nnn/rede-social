package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.controllers.UserController;
import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConsumesApiCep consumesApiCep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserDto createUser(InsertUserDto dto) {
        logger.info("Service: creating one user");

        var entity = DozerMapper.parseObject(dto, UserEntity.class);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        entity = repository.save(entity);

        var userdto = DozerMapper.parseObject(entity, UserDto.class);

        //userdto.setCep(consumesApiCep.queryCep(userdto.getCep()));

        return userdto.add(linkTo(methodOn(UserController.class).findById(userdto.getKey())).withSelfRel());
    }

    public UserDto findById(Long id) {
        Optional<UserEntity> optional = repository.findById(id);

        var userEnity = optional.orElseThrow(() -> new ResourceAccessException("User not found. Id: " +  id));

        var userdto = DozerMapper.parseObject(userEnity, UserDto.class);

        userdto.setCep(consumesApiCep.queryCep(userdto.getCep()));

        return userdto.add(linkTo(methodOn(UserController.class).findById(userdto.getKey())).withSelfRel());
    }

}

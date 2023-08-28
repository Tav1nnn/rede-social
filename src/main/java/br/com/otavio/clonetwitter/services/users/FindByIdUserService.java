package br.com.otavio.clonetwitter.services.users;

import br.com.otavio.clonetwitter.controllers.users.FindByIdUserController;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FindByIdUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConsumesApiCep consumesApiCep;

    public UserDto findById(Long id) {
        Optional<UserEntity> optional = repository.findById(id);

        var userEnity = optional.orElseThrow(() -> new ResourceAccessException("User not found. Id: " +  id));

        var userdto = DozerMapper.parseObject(userEnity, UserDto.class);

        userdto.setCep(consumesApiCep.queryCep(userdto.getCep()));

        return userdto.add(linkTo(methodOn(FindByIdUserController.class).findById(userdto.getKey())).withSelfRel());
    }
}

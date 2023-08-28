package br.com.otavio.clonetwitter.services.users;

import br.com.otavio.clonetwitter.controllers.users.CreateUserController;
import br.com.otavio.clonetwitter.controllers.users.FindByIdUserController;
import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

@Service
public class CreateUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConsumesApiCep consumesApiCep;

    private Logger logger = Logger.getLogger(CreateUserService.class.getName());

    public UserDto createUser(InsertUserDto dto) {
        logger.info("Service: creating one user");

        var entity = DozerMapper.parseObject(dto, UserEntity.class);

        entity = repository.save(entity);

        var userdto = DozerMapper.parseObject(entity, UserDto.class);

        userdto.setCep(consumesApiCep.queryCep(userdto.getCep()));

        return userdto.add(linkTo(methodOn(FindByIdUserController.class).findById(userdto.getKey())).withSelfRel());
    }


}

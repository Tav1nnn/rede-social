package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private Logger logger = Logger.getLogger(UserService.class.getName());

    public UserDto insertUser(InsertUserDto dto) {
        logger.info("Service: creating one user");

        var entity = DozerMapper.parseObject(dto, UserEntity.class);

        System.out.println(entity.getBirthday());

        entity = repository.save(entity);

        return DozerMapper.parseObject(entity, UserDto.class);
    }
}

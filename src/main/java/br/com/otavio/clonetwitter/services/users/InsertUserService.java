package br.com.otavio.clonetwitter.services.users;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import mapper.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class InsertUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConsumesApiCep consumesApiCep;


    private Logger logger = Logger.getLogger(InsertUserService.class.getName());

    public UserDto insertUser(InsertUserDto dto) {
        logger.info("Service: creating one user");

        var entity = DozerMapper.parseObject(dto, UserEntity.class);

        entity = repository.save(entity);

        entity.setCep(queryCep(dto.getCep()));

        return DozerMapper.parseObject(entity, UserDto.class);
    }

    private String queryCep (String cep) {

        var cepEntity = consumesApiCep.queryCep(cep);

        return cepEntity.getLocalidade() +", "+cepEntity.getUf()+".";
    }
}

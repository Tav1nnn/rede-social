package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.publication.NewPublicationDto;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository repository;

    @Autowired
    private UserService userService;

    public void newPublication(NewPublicationDto dto) {
        PublicationEntity entity = new PublicationEntity();

        entity.setCaption(dto.caption());
        entity.setCreate_at(new Date(new java.util.Date().getTime()));
        UserEntity entityUser = DozerMapper.parseObject(userService.getUser(), UserEntity.class);
        System.out.println(entityUser.getId());
        entity.setUser(entityUser);

        repository.save(entity);

    }
}

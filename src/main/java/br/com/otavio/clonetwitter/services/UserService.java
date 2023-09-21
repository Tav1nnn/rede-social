package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.controllers.UserController;
import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.dto.user.UserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.repositories.RoleRepository;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository repositoryRole;

    @Autowired
    private ConsumesApiCep consumesApiCep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = Logger.getLogger(UserService.class.getName());

    public void createUser(InsertUserDto dto) {
        logger.info("Service: creating one user");

        UserEntity entity = DozerMapper.parseObject(dto, UserEntity.class);
        entity.setRole(new ArrayList<>());
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.getRole().add(repositoryRole.findByName("USER"));
        entity = repository.save(entity);
    }
    public UserDto findById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found. Id: " + id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username " + username + " not found"));
    }


    public UserDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity entity = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Optional<UserEntity> optional = repository.findByUsername(authentication.getName());

            entity = optional.orElseThrow(() -> new ResourceNotFoundException("User not found"));
        }

        return DozerMapper.parseObject(entity, UserDto.class);
    }

    private UserDto toDto(UserEntity entity) {
        UserDto dto = DozerMapper.parseObject(entity, UserDto.class);
        dto.setCep(consumesApiCep.queryCep(entity.getCep()));
        dto.add(linkTo(methodOn(UserController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

}

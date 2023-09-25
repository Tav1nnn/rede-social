package br.com.otavio.clonetwitter.controllers.exceptions.validation.user;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.controllers.exceptions.model.FieldMessage;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import br.com.otavio.clonetwitter.services.consumesAPI.ConsumesApiCep;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class InsertUserValidator implements ConstraintValidator<InsertUserValid, InsertUserDto> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ConsumesApiCep consumesApiCep;

    @Override
    public void initialize(InsertUserValid ann) {

    }

    @Override
    public boolean isValid (InsertUserDto dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Optional<UserEntity> optional = repository.findByEmail(dto.getEmail());

        if(optional.isPresent()) {
            list.add(new FieldMessage("email", "There is already a user with this email"));
        }

        optional = repository.findByUsername(dto.getUsername());


        if(optional.isPresent()) {
            list.add(new FieldMessage("username", "There is already a user with this username"));
        }

        Date date = new Date();

        java.sql.Date dateSql = new java.sql.Date(date.getTime());

        long differenceDate = dateSql.getTime() - dto.getBirthday().getTime();

        //milissegundos => segundo / segundos => minutos / minutos => horas / horas => dias
        differenceDate = differenceDate / (1000 * 60 * 60 * 24);

        if((differenceDate/365) < 18 ) {
            list.add(new FieldMessage("birthday", "Forbidden under 18 years old"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();

    }
}

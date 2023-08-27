package br.com.otavio.clonetwitter.exceptions.validation.user;

import br.com.otavio.clonetwitter.dto.user.InsertUserDto;
import br.com.otavio.clonetwitter.exceptions.model.FieldMessage;
import br.com.otavio.clonetwitter.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class InsertUserValidator implements ConstraintValidator<InsertUserValid, InsertUserDto> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(InsertUserValid ann) {

    }

    @Override
    public boolean isValid (InsertUserDto dto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        /*var userentity = repository.findByEmail(dto.getEmail());

        if(userentity != null){
            list.add(new FieldMessage("email", "There is already a user with this email"));
        }

        LocalDate datenow = LocalDate.now();

        int years = Period.between(dto.getBirthday(), datenow).getYears();


        if(years<18) {
            list.add(new FieldMessage("birthday", "Forbidden under 18 years old"));
        }*/

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();

    }
}

package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

}

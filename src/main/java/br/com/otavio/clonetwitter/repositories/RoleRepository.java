package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}

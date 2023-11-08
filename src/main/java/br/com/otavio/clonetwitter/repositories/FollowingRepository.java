package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.FollowingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<FollowingEntity, Long> {

}

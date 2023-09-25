package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShareRepository extends JpaRepository<ShareEntity, Long> {
    Optional<ShareEntity> findByUserAndPublication(UserEntity user, PublicationEntity publication);
}

package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    long countByPublicationEntity(PublicationEntity publicationEntity);

    List<CommentEntity> findByPublicationEntity (PublicationEntity publicationEntity);
}

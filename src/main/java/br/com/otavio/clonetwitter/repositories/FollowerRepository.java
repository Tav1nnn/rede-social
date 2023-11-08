package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.entities.FollowerEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<FollowerEntity, Long> {

}

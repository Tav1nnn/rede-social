package br.com.otavio.clonetwitter.repositories;

import br.com.otavio.clonetwitter.entities.PublicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Long> {
}

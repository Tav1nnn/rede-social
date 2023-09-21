package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;

import java.sql.Date;
import java.util.List;

public record PublicationNoDependenciesDto(
       
         Long id,
         String caption,
         Date create_at
)
{}

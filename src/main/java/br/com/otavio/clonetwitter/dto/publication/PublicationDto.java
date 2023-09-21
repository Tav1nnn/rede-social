package br.com.otavio.clonetwitter.dto.publication;

import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;

import java.sql.Date;
import java.util.List;

public record PublicationDto (
       
         Long id,
         String caption,
         Date create_at,
         UserEntity user,
         List<LikeEntity> likes,
         List<ShareEntity> shares
)
{}

package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.dto.publication.NewPublicationDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationLikeDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationShareDto;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private UserService userService;

    public void createNewPublication(NewPublicationDto publicationDto){
        PublicationEntity publicationEntity = createPublicationEntity(publicationDto);
        UserEntity userEntity = getUserEntityFromService();
        publicationEntity.setUser(userEntity);

        publicationRepository.save(publicationEntity);
    }

    public PublicationDto findById(Long id) {

        PublicationEntity entity = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return publicationEntityToPublicationDto(entity);
    }

    public PublicationLikeDto findByIdWithLike(Long id) {
        PublicationEntity entity = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return publicationEntityToPublicationLikeDto(entity);
    }

    public PublicationShareDto findByIdWithShare(Long id) {
        PublicationEntity entity = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return publicationEntityToPublicationShareDto(entity);
    }

    private UserEntity getUserEntityFromService() {
        return DozerMapper.parseObject(userService.getUser(), UserEntity.class);
    }

    private PublicationEntity createPublicationEntity(NewPublicationDto publicationDto) {
        PublicationEntity publicationEntity = new PublicationEntity();
        publicationEntity.setCaption(publicationDto.caption());
        publicationEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        return publicationEntity;
    }

    private PublicationDto publicationEntityToPublicationDto(PublicationEntity publicationEntity) {
        PublicationDto publicationDto = new PublicationDto();

        publicationDto.setId(publicationEntity.getId());
        publicationDto.setCaption(publicationEntity.getCaption());
        publicationDto.setCreate_at(publicationEntity.getCreate_at());
        UsernameDto usernameDto = new UsernameDto(publicationEntity.getUser().getUsername());
        publicationDto.setUser(usernameDto);

        return publicationDto;
    }

    private PublicationLikeDto publicationEntityToPublicationLikeDto(PublicationEntity entity) {
        PublicationLikeDto publicationLikeDto = new PublicationLikeDto();

        publicationLikeDto.setId(entity.getId());
        publicationLikeDto.setCaption(entity.getCaption());
        publicationLikeDto.setCreate_at(entity.getCreate_at());
        UsernameDto usernameDto = new UsernameDto(entity.getUser().getUsername());
        publicationLikeDto.setUser(usernameDto);

        publicationLikeDto.setUsernameOfLikeList(new ArrayList<>());

        for(LikeEntity likeEntity : entity.getLikes()){
            UsernameDto usernameDtoOfLike = new UsernameDto(likeEntity.getUser().getUsername());
            publicationLikeDto.getUsernameOfLikeList().add(usernameDtoOfLike);
        }

        return publicationLikeDto;
    }

    private PublicationShareDto publicationEntityToPublicationShareDto(PublicationEntity entity) {
        PublicationShareDto publicationShareDto = new PublicationShareDto();

        publicationShareDto.setId(entity.getId());
        publicationShareDto.setCaption(entity.getCaption());
        publicationShareDto.setCreate_at(entity.getCreate_at());
        UsernameDto usernameDto = new UsernameDto(entity.getUser().getUsername());
        publicationShareDto.setUser(usernameDto);

        publicationShareDto.setUsernameOfLikeList(new ArrayList<>());

        for(ShareEntity shareEntity: entity.getShares()){
            UsernameDto usernameDtoOfLike = new UsernameDto(shareEntity.getUser().getUsername());
            publicationShareDto.getUsernameOfLikeList().add(usernameDtoOfLike);
        }

        return publicationShareDto;
    }
}

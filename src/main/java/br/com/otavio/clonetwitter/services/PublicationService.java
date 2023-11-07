package br.com.otavio.clonetwitter.services;

import br.com.otavio.clonetwitter.controllers.PublicationController;
import br.com.otavio.clonetwitter.controllers.UserController;
import br.com.otavio.clonetwitter.dto.NumberOfInteractionsDTO;
import br.com.otavio.clonetwitter.dto.publication.*;
import br.com.otavio.clonetwitter.dto.user.UsernameDto;
import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.entities.PublicationEntity;
import br.com.otavio.clonetwitter.entities.ShareEntity;
import br.com.otavio.clonetwitter.entities.UserEntity;
import br.com.otavio.clonetwitter.mapper.DozerMapper;
import br.com.otavio.clonetwitter.mapper.PublicationMapper;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private LikeService likeService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationMapper publicationMapper;

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

    public PublicationCommentDto findByIdWithComment(Long idPublication) {
        PublicationEntity entity = publicationRepository.findById(idPublication)
                .orElseThrow(() -> new ResourceNotFoundException("id no found"));

        return publicatinEntityToPublicationCommentDto(entity);
    }

    public PublicationInteractionsNumberDTO findByIdWithInteractionsNumber(Long id) {
        PublicationEntity entity = publicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id not found"));

        return publicationEntityToPublicationInteractionsNumberDTO(entity);
    }

    private PublicationEntity createPublicationEntity(NewPublicationDto publicationDto) {
        PublicationEntity publicationEntity = new PublicationEntity();
        publicationEntity.setCaption(publicationDto.caption());
        publicationEntity.setCreate_at(new Date(new java.util.Date().getTime()));
        return publicationEntity;
    }

    private UserEntity getUserEntityFromService() {
        return DozerMapper.parseObject(userService.getUser(), UserEntity.class);
    }

    private PublicationDto publicationEntityToPublicationDto(PublicationEntity publicationEntity) {
        PublicationDto publicationDto = publicationMapper.toPublicationDto(publicationEntity);
        publicationDto.setUser(newUsernameDto(publicationEntity.getUser().getUsername(), publicationEntity.getUser().getId()));
        publicationDto.add(linkTo(methodOn(PublicationController.class).findById(publicationDto.getKey())).withSelfRel());
        return publicationDto;
    }

    private PublicationLikeDto publicationEntityToPublicationLikeDto(PublicationEntity entity) {
        PublicationLikeDto publicationLikeDto = publicationMapper.toPublicationLikeDto(entity);
        publicationLikeDto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        for(LikeEntity likeEntity : entity.getLikes()){
            publicationLikeDto.getUsernameOfLikeList().add(newUsernameDto(likeEntity.getUser().getUsername(), likeEntity.getUser().getId()));
        }

        publicationLikeDto.add(linkTo(methodOn(PublicationController.class).findById(publicationLikeDto.getKey())).withSelfRel());
        return publicationLikeDto;
    }

    private PublicationShareDto publicationEntityToPublicationShareDto(PublicationEntity entity) {
        PublicationShareDto publicationShareDto = publicationMapper.toPublicationShareDto(entity);
        publicationShareDto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        for(ShareEntity shareEntity: entity.getShares()){
            publicationShareDto.getUsernameOfLikeList().add(newUsernameDto(shareEntity.getUser().getUsername(), shareEntity.getUser().getId()));
        }
        publicationShareDto.add(linkTo(methodOn(PublicationController.class).findById(publicationShareDto.getKey())).withSelfRel());
        return publicationShareDto;
    }

    private PublicationCommentDto publicatinEntityToPublicationCommentDto(PublicationEntity entity) {
        PublicationCommentDto dto = publicationMapper.toPublicationCommentDto(entity);
        dto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getId()));
        dto.setListCommentDto(commentService.listComment(dto.getKey()));
        dto.add(linkTo(methodOn(PublicationController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    private PublicationInteractionsNumberDTO publicationEntityToPublicationInteractionsNumberDTO(PublicationEntity entity) {
        PublicationInteractionsNumberDTO dto = publicationMapper.toPublicationInteractionsNumberDto(entity);
        dto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        NumberOfInteractionsDTO numberOfInteractionsDTO = new NumberOfInteractionsDTO(
                likeService.countLike(publicationMapper.toPublicationDto(entity)),
                shareService.countShare(publicationMapper.toPublicationDto(entity)),
                commentService.countComment(publicationMapper.toPublicationDto(entity))
        );
        dto.setNumberOfInteractionsDTO(numberOfInteractionsDTO);
        dto.add(linkTo(methodOn(PublicationController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    private UsernameDto newUsernameDto(String username, Long id) {
        UsernameDto usernameDto = new UsernameDto();
        usernameDto.setUsername(username);
        usernameDto.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());

        return usernameDto;
    }
}

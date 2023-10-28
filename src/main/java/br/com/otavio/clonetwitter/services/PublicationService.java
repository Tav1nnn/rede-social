package br.com.otavio.clonetwitter.services;

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
import br.com.otavio.clonetwitter.repositories.CommentRepository;
import br.com.otavio.clonetwitter.repositories.LikeRepository;
import br.com.otavio.clonetwitter.repositories.PublicationRepository;
import br.com.otavio.clonetwitter.repositories.ShareRepository;
import br.com.otavio.clonetwitter.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationMapper publicationMapper;

    public PublicationService(PublicationRepository publicationRepository, LikeRepository likeRepository, ShareRepository shareRepository,
                              CommentRepository commentRepository, CommentService commentService, UserService userService, PublicationMapper publicationMapper) {
        this.publicationRepository = publicationRepository;
        this.likeRepository = likeRepository;
        this.shareRepository = shareRepository;
        this.commentRepository = commentRepository;
        this.commentService = commentService;
        this.userService = userService;
        this.publicationMapper = publicationMapper;
    }

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
        publicationDto.add(linkTo(methodOn(UserController.class).findById(publicationDto.getKey())).withSelfRel());
        return publicationDto;
    }

    private PublicationLikeDto publicationEntityToPublicationLikeDto(PublicationEntity entity) {
        PublicationLikeDto publicationLikeDto = publicationMapper.toPublicationLikeDto(entity);
        publicationLikeDto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        for(LikeEntity likeEntity : entity.getLikes()){
            publicationLikeDto.getUsernameOfLikeList().add(newUsernameDto(likeEntity.getUser().getUsername(), likeEntity.getUser().getId()));
        }

        return publicationLikeDto;
    }

    private PublicationShareDto publicationEntityToPublicationShareDto(PublicationEntity entity) {
        PublicationShareDto publicationShareDto = publicationMapper.toPublicationShareDto(entity);
        publicationShareDto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        for(ShareEntity shareEntity: entity.getShares()){
            publicationShareDto.getUsernameOfLikeList().add(newUsernameDto(shareEntity.getUser().getUsername(), shareEntity.getUser().getId()));
        }

        return publicationShareDto;
    }

    private PublicationCommentDto publicatinEntityToPublicationCommentDto(PublicationEntity entity) {
        PublicationCommentDto dto = publicationMapper.toPublicationCommentDto(entity);
        dto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getId()));
        dto.setListCommentDto(commentService.listComment(dto.getKey()));
        return dto;
    }

    private PublicationInteractionsNumberDTO publicationEntityToPublicationInteractionsNumberDTO(PublicationEntity entity) {
        PublicationInteractionsNumberDTO dto = publicationMapper.toPublicationInteractionsNumberDto(entity);
        dto.setUser(newUsernameDto(entity.getUser().getUsername(), entity.getUser().getId()));

        NumberOfInteractionsDTO numberOfInteractionsDTO = new NumberOfInteractionsDTO(
                likeRepository.countByPublication(entity),
                shareRepository.countByPublication(entity),
                commentRepository.countByPublicationEntity(entity)
        );
        dto.setNumberOfInteractionsDTO(numberOfInteractionsDTO);
        return dto;
    }


    private UsernameDto newUsernameDto(String username, Long id) {
        UsernameDto usernameDto = new UsernameDto();
        usernameDto.setUsername(username);
        usernameDto.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());

        return usernameDto;
    }
}

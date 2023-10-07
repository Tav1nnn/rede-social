package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.dto.publication.NewPublicationDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationLikeDto;
import br.com.otavio.clonetwitter.dto.publication.PublicationShareDto;
import br.com.otavio.clonetwitter.services.PublicationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(value = "/api/publication/v1")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping(value = "/create")
    public void createNewPublication(@RequestBody NewPublicationDto newPublicationDto, HttpServletResponse response) {
        publicationService.createNewPublication(newPublicationDto);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDto> findById(@PathVariable Long id){
        PublicationDto publicationDto = publicationService.findById(id);

        return ResponseEntity.ok().body(publicationDto);
    }

    @GetMapping("/like/{id}")
    public ResponseEntity<PublicationLikeDto> findByIdWithLike(@PathVariable Long id) {
        PublicationLikeDto publicationLikeDto = publicationService.findByIdWithLike(id);

        return ResponseEntity.ok().body(publicationLikeDto);
    }

    @GetMapping("/share/{id}")
    public ResponseEntity<PublicationShareDto> findByIdWithShare(@PathVariable Long id) {
        PublicationShareDto publicationShareDto = publicationService.findByIdWithShare(id);

        return ResponseEntity.ok().body(publicationShareDto);
    }
}

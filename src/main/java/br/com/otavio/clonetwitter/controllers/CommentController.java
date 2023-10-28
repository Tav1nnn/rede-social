package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.dto.comment.CommentInsertDto;
import br.com.otavio.clonetwitter.dto.comment.CommentListDto;
import br.com.otavio.clonetwitter.entities.CommentEntity;
import br.com.otavio.clonetwitter.services.CommentService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/comment/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping(value = "/newComment")
    public void newComment(@RequestBody  @Valid  CommentInsertDto commentInsertDto, HttpServletResponse response) {
        System.out.println("teste");
        commentService.newComment(commentInsertDto);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @GetMapping(value = "/{id}")//isso de retornar a entidade e provisorio :)
    public ResponseEntity<CommentEntity> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(commentService.findById(id));
    }

    @GetMapping(value = "/listComment/{id}")
    public ResponseEntity<List<CommentListDto>> listCommet (@PathVariable Long idPublication) {
        return ResponseEntity.ok().body(commentService.listComment(idPublication));
    }
}

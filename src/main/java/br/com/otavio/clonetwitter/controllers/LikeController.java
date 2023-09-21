package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.entities.LikeEntity;
import br.com.otavio.clonetwitter.services.LikeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/like/v1")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping(value = "/{id}")
    public void createNewLike(@PathVariable Long id, HttpServletResponse response) {
        likeService.createNewLike(id);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

}

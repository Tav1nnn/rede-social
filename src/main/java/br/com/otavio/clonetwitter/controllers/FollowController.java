package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.services.FollowerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping(value = "/api/v1/follow")
public class FollowController {

    @Autowired
    private FollowerService followerService;

    @PostMapping(value = "/{id}")
    public void newFollow(@PathVariable Long id, HttpServletResponse response){
        followerService.newFollow(id);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}

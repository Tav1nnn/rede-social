package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.services.LikeService;
import br.com.otavio.clonetwitter.services.ShareService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/share/v1")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @PostMapping(value = "/{id}")
    public void createNewShare(@PathVariable Long id, HttpServletResponse response) {
        shareService.createNewShare(id);

        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteLike(@PathVariable Long id){
        shareService.deleteShare(id);
    }

}

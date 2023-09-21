package br.com.otavio.clonetwitter.controllers;

import br.com.otavio.clonetwitter.dto.publication.NewPublicationDto;
import br.com.otavio.clonetwitter.services.PublicationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/api/publication/v1/")
public class PublicationController {

    @Autowired
    private PublicationService service;

    @PostMapping
    public void newPublication(@RequestBody NewPublicationDto dto, HttpServletResponse response) {
        service.newPublication(dto);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}

package com.example.conference.controllers;

import com.example.conference.models.Session;
import com.example.conference.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//this will respond to payloads incoming and outgoing as json rest endpoints
@RequestMapping("/api/v1/sessions")
// tells the router what the mapping url will look like
public class SessionController {
    //inject sessions repository
    private SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> list() {
        return sessionService.getAll();
    }

    @GetMapping("/{id}")
    public Session get(@PathVariable("id") Long id) {
        return sessionService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@Valid @RequestBody final Session session) {
        return sessionService.createSession(session);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        sessionService.deleteSession(id);
    }

   /*
    BeanUtils.copyProperties gets existing session and copies the updated version
        3rd parameter ignores fields that you don't need to copy*/

    @PutMapping("/{id}")
    public Session update(@PathVariable("id") Long id, @RequestBody Session session) {
        return sessionService.updateSession(id, session);
    }

}

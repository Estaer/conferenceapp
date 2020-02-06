package com.example.conference.services;

import com.example.conference.models.Session;
import com.example.conference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAll(){
        return sessionRepository.findAll();
    }

    public Session getById(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    public Session createSession(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    public void deleteSession(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    public Session updateSession(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}

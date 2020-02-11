package com.example.conference.controllers;

import com.example.conference.models.Speaker;
import com.example.conference.services.SpeakerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {

    private SpeakerService speakerService;

    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    @GetMapping
    public List<Speaker> list() {
        return speakerService.getAll();
    }

    @GetMapping("/{id}")
    public Speaker get(@PathVariable("id") Long id) {
        return speakerService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@Valid @RequestBody final Speaker speaker) {
        return speakerService.addSpeaker(speaker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        speakerService.deleteSpeaker(id);
    }

    @PutMapping("/{id}")
    public Speaker update(@PathVariable("id") Long id, @RequestBody Speaker speaker) {
        return speakerService.updateSpeaker(id, speaker);
    }

    }

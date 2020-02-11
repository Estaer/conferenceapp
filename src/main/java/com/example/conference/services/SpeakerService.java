package com.example.conference.services;

import com.example.conference.models.Speaker;
import com.example.conference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@Service
public class SpeakerService {

    /*validatorfactory used to get a validator
     validator  is the central api for bean validation - calls the validate() method to validate bean
    validate() return a set of constraint violations*/

    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator validator = validatorFactory.getValidator();
    private Set<ConstraintViolation<Speaker>> violations;


    private final SpeakerRepository speakerRepository;

    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> getAll(){
        return speakerRepository.findAll();
    }

    public Speaker getById(@PathVariable Long id){
        return speakerRepository.getOne(id);
    }

    public Speaker addSpeaker(@RequestBody final Speaker speaker){
        violations = validator.validate(speaker);
        if (violations.size() > 0)
            throw new ConstraintViolationException(violations);

        return speakerRepository.saveAndFlush(speaker);
    }

    public void deleteSpeaker(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    public Speaker updateSpeaker(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}

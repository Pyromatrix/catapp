package net.javaguides.springboot.healthnote.controller;

import net.javaguides.springboot.healthnote.model.HealthNote;
import net.javaguides.springboot.healthnote.service.HealthNoteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/healthnote")
@CrossOrigin
public class HealthNoteController {

    @Autowired
    private HealthNoteRepository healthNoteRepository;

    @GetMapping("")
    public List<HealthNote> getHealthNotes(){
        return healthNoteRepository.findAllByDeletedIsNull();
    }

    @GetMapping("/{id}")
    public HealthNote getHealthNote(@PathVariable(name = "id") Long id) {
        return healthNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "healthnote"));
    }

    @PutMapping("/{id}")
    public HealthNote updateHealthNote(@PathVariable(name = "id") Long id, @RequestBody HealthNote healthNote) {
        healthNote.setId(id);
        return healthNoteRepository.save(healthNote);
    }

    @DeleteMapping("/{id}")
    public void deleteHealthNote(@PathVariable(name = "id") Long id) {
        HealthNote healthNote = healthNoteRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "healthnote"));
        healthNote.setDeleted(new Date());
        healthNote.setId(id);
        healthNoteRepository.save(healthNote);
    }

    @PostMapping("")
    public HealthNote createHealthNote(@RequestBody HealthNote healthNote) {
        return healthNoteRepository.save(healthNote);
    }
}

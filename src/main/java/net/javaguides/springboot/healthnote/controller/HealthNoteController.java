package net.javaguides.springboot.healthnote.controller;

import net.javaguides.springboot.healthnote.model.HealthNote;
import net.javaguides.springboot.healthnote.service.HealthNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/healthnote")
@CrossOrigin("http://localhost:3000/") // added to avoid CORS issues
public class HealthNoteController {

    @Autowired
    private HealthNoteRepository healthNoteRepository;

    @GetMapping("")
    public List<HealthNote> getHealthNotes(){
        return healthNoteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<HealthNote> getHealthNote(@PathVariable(name = "id") Long id) {
        return healthNoteRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteHealthNote(@PathVariable(name = "id") Long id) {
        healthNoteRepository.deleteById(id);
    }

    @PostMapping("")
    public HealthNote createHealthNote(@RequestBody HealthNote healthNote) {
        return healthNoteRepository.save(healthNote);
    }
}

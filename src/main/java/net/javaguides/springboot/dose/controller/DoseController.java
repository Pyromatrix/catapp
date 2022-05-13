package net.javaguides.springboot.dose.controller;

import net.javaguides.springboot.dose.model.Dose;
import net.javaguides.springboot.dose.service.DoseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dose")
@CrossOrigin("http://localhost:3000/") //  added to avoid CORS issues
public class DoseController {

    @Autowired
    private DoseRepository doseRepository;

    @GetMapping("")
    public List<Dose> getDoses(){
        return doseRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Dose> getDose(@PathVariable(name = "id") Long id) {
        return doseRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDose(@PathVariable(name = "id") Long id) {
        doseRepository.deleteById(id);
    }

    @PostMapping("")
    public Dose createDose(@RequestBody Dose dose) {
        return doseRepository.save(dose);
    }
}

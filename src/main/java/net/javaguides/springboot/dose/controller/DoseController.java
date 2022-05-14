package net.javaguides.springboot.dose.controller;

import net.javaguides.springboot.dose.model.Dose;
import net.javaguides.springboot.dose.service.DoseRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dose")
@CrossOrigin
public class DoseController {

    @Autowired
    private DoseRepository doseRepository;

    @GetMapping("")
    public List<Dose> getDoses(){
        return doseRepository.findAllByDeletedIsNull();
    }

    @GetMapping("/{id}")
    public Dose getDose(@PathVariable(name = "id") Long id) {
        return doseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "dose"));
    }

    @PutMapping("/{id}")
    public Dose updateDose(@PathVariable(name = "id") Long id, @RequestBody Dose dose) {
        dose.setId(id);
        return doseRepository.save(dose);
    }

    @DeleteMapping("/{id}")
    public void deleteDose(@PathVariable(name = "id") Long id) {
        Dose dose = doseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "dose"));
        dose.setDeleted(new Date());
        dose.setId(id);
        doseRepository.save(dose);
    }

    @PostMapping("")
    public Dose createDose(@RequestBody Dose dose) {
        return doseRepository.save(dose);
    }
}

package net.javaguides.springboot.vaccination.controller;

import net.javaguides.springboot.vaccination.model.Vaccination;
import net.javaguides.springboot.vaccination.service.VaccinationRepository;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vaccination")
@CrossOrigin
public class VaccinationController {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @GetMapping("")
    public List<Vaccination> getVaccinations(){
        return vaccinationRepository.findAllByDeletedIsNull();
    }

    @GetMapping("/{id}")
    public Vaccination getVaccination(@PathVariable(name = "id") Long id) {
        return vaccinationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "vaccination"));
    }

    @PutMapping("/{id}")
    public Vaccination updateVaccination(@PathVariable(name = "id") Long id, @RequestBody Vaccination vaccination) {
        vaccination.setId(id);
        return vaccinationRepository.save(vaccination);
    }

    @DeleteMapping("/{id}")
    public void deleteVaccination(@PathVariable(name = "id") Long id) {
        Vaccination vaccination = vaccinationRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "vaccination"));
        vaccination.setDeleted(new Date());
        vaccination.setId(id);
        vaccinationRepository.save(vaccination);
    }

    @PostMapping("")
    public Vaccination createVaccination(@RequestBody Vaccination vaccination) {
        return vaccinationRepository.save(vaccination);
    }
}

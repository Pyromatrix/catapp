package net.javaguides.springboot.vaccination.controller;

import net.javaguides.springboot.vaccination.model.Vaccination;
import net.javaguides.springboot.vaccination.service.VaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vaccination")
@CrossOrigin("http://localhost:3000/") //  added to avoid CORS issues
public class VaccinationController {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @GetMapping("")
    public List<Vaccination> getVaccinations(){
        return vaccinationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Vaccination> getVaccination(@PathVariable(name = "id") Long id) {
        return vaccinationRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVaccination(@PathVariable(name = "id") Long id) {
        vaccinationRepository.deleteById(id);
    }

    @PostMapping("")
    public Vaccination createVaccination(@RequestBody Vaccination item) {
        return vaccinationRepository.save(item);
    }
}

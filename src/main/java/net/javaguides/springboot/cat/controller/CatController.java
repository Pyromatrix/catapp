package net.javaguides.springboot.cat.controller;

import net.javaguides.springboot.cat.model.Cat;
import net.javaguides.springboot.cat.service.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin("http://localhost:3000/") //  added to avoid CORS issues
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("")
    public List<Cat> getCats(){
        return catRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Cat> getCat(@PathVariable(name = "id") Long id) {
        return catRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable(name = "id") Long id) {
        catRepository.deleteById(id);
    }

    @PostMapping("")
    public Cat createCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }
}

package net.javaguides.springboot.cat.controller;

import net.javaguides.springboot.cat.model.Cat;
import net.javaguides.springboot.cat.service.CatRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/cat")
@CrossOrigin
public class CatController {

    @Autowired
    private CatRepository catRepository;

    @GetMapping("")
    public List<Cat> getCats(){
        return catRepository.findAllByDeletedIsNull();
    }

    @GetMapping("/{id}")
    public Cat getCat(@PathVariable(name = "id") Long id) {
        return catRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "cat"));
    }

    @PutMapping("/{id}")
    public Cat updateCat(@PathVariable(name = "id") Long id, @RequestBody Cat cat) {
        cat.setId(id);
        return catRepository.save(cat);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable(name = "id") Long id) {
        Cat cat = catRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "cat"));
        cat.setDeleted(new Date());
        cat.setId(id);
        catRepository.save(cat);
    }

    @PostMapping("")
    public Cat createCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }
}

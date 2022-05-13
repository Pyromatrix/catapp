package net.javaguides.springboot.item.controller;

import net.javaguides.springboot.item.model.Item;
import net.javaguides.springboot.item.service.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
@CrossOrigin("http://localhost:3000/") //  added to avoid CORS issues
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Item> getItem(@PathVariable(name = "id") Long id) {
        return itemRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        itemRepository.deleteById(id);
    }

    @PostMapping("")
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}

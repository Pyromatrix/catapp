package net.javaguides.springboot.item.controller;

import net.javaguides.springboot.item.model.Item;
import net.javaguides.springboot.item.service.ItemRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("")
    public List<Item> getItems(){
        return itemRepository.findAllByDeletedIsNull();
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable(name = "id") Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "item"));
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable(name = "id") Long id, @RequestBody Item item) {
        item.setId(id);
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable(name = "id") Long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "item"));
        item.setDeleted(new Date());
        item.setId(id);
        itemRepository.save(item);
    }

    @PostMapping("")
    public Item createItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }
}

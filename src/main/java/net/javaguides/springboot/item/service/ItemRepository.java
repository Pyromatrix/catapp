package net.javaguides.springboot.item.service;

import net.javaguides.springboot.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByDeletedIsNull();
}

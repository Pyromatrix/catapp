package net.javaguides.springboot.cat.service;

import net.javaguides.springboot.cat.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long> {

    List<Cat> findAllByDeletedIsNull();
}

package net.javaguides.springboot.dose.service;

import net.javaguides.springboot.dose.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose, Long> {

}

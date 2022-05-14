package net.javaguides.springboot.vaccination.service;

import net.javaguides.springboot.vaccination.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

    List<Vaccination> findAllByDeletedIsNull();
}

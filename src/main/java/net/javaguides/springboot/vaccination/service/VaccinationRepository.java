package net.javaguides.springboot.vaccination.service;

import net.javaguides.springboot.vaccination.model.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {

}

package net.javaguides.springboot.dose.service;

import net.javaguides.springboot.dose.model.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public interface DoseRepository extends JpaRepository<Dose, Long> {

    List<Dose> findAllByDeletedIsNull();

    List<Dose> findAllByCatId(Long catId);

    List<Dose> findAllByDate(ZonedDateTime date);

    List<Dose> findAllByCatIdAndDeletedIsNull(Long catId);
}

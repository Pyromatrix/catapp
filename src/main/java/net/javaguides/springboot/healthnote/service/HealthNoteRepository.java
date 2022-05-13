package net.javaguides.springboot.healthnote.service;

import net.javaguides.springboot.healthnote.model.HealthNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthNoteRepository extends JpaRepository<HealthNote, Long> {

}

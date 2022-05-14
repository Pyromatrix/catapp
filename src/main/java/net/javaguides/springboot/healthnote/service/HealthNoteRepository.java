package net.javaguides.springboot.healthnote.service;

import net.javaguides.springboot.healthnote.model.HealthNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthNoteRepository extends JpaRepository<HealthNote, Long> {

    List<HealthNote> findAllByDeletedIsNull();
}

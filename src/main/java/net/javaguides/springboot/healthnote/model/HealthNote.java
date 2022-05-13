package net.javaguides.springboot.healthnote.model;

import lombok.*;

import net.javaguides.springboot.cat.model.Cat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "health_notes")
public class HealthNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "note")
    private String note;
}

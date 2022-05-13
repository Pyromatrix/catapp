package net.javaguides.springboot.healthnote.model;

import lombok.*;

import net.javaguides.springboot.cat.model.Cat;

import javax.persistence.*;
import java.util.Date;

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
    private Date date;

    @Column(name = "note")
    private String note;

    @Column(name = "created")
    private Date created;

    @Column(name = "deleted")
    private Date deleted;
}

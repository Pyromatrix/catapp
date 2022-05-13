package net.javaguides.springboot.dose.model;

import lombok.*;

import net.javaguides.springboot.cat.model.Cat;
import net.javaguides.springboot.item.model.Item;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doses")
public class Dose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private Date date;

    @Column(name = "created")
    private Date created;

    @Column(name = "deleted")
    private Date deleted;

}

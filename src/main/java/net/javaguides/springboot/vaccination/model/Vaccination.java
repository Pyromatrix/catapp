package net.javaguides.springboot.vaccination.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vaccinations")
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "give_date")
    private Date giveDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "deleted")
    private Date deleted;
}

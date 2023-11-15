package kz.kolesa.parsingkolesa.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "location", nullable = false)
    private String location;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private CarDescriptionEntity carDescription;
}

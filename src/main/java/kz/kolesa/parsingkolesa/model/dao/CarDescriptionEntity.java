package kz.kolesa.parsingkolesa.model.dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "car_description")
public class CarDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity car;

    @Column(name = "generation", nullable = false)
    private String generation;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "engine_volume", nullable = false)
    private String engineVolume;

    @Column(name = "transmission", nullable = false)
    private String transmission;

    @Column(name = "drive", nullable = false)
    private String drive;

    @Column(name = "rudder", nullable = false)
    private String rudder;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "customs_cleared", nullable = false)
    private String customsCleared;

    @Column(name = "description")
    private String description;

    @Column(name = "seller_comment")
    private String sellerComment;
}

package kz.kolesa.parsingkolesa.model.dao;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cars")
public class CarsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "location", nullable = false)
    private String location;

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

    @Column(name = "link_car", nullable = false)
    private String linkCar;

    @Column(name = "links_image")
    private String linksImage;

    @Column(name = "description")
    private String description;

    @Column(name = "seller_comment")
    private String sellerComment;
}

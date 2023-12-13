package kz.kolesa.parsingkolesa.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    private String brand;
    private String price;
    private String location;
    private String generation;
    private String body;
    private String engineVolume;
    private String transmission;
    private String drive;
    private String rudder;
    private String color;
    private String customsCleared;
    private String linkCar;
    private String linksImage;
    private String description;
    private String sellerComment;
}


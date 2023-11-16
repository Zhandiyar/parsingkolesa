package kz.kolesa.parsingkolesa.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDescriptionDto {
    private String generation;
    private String body;
    private String engineVolume;
    private String transmission;
    private String drive;
    private String rudder;
    private String color;
    private String customsCleared;
    private String description;
    private String sellerComment;
    private CarDto car;
}

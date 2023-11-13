package kz.kolesa.parsingkolesa.controller;

import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping(value = "/cars")
    public List<CarEntity> getAllCar() {
        return carService.getAllCars();
    }
}

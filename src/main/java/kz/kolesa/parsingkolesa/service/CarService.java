package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.model.dao.CarsEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarService {
    void save(CarDto carDto);
    List<CarsEntity> getAllCars();

    Optional<CarsEntity> findById(Long id);
}

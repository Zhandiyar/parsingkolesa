package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    void save (CarDto carDto);
    public boolean isExist(String carTitle);
    List<CarEntity> getAllCars();
}

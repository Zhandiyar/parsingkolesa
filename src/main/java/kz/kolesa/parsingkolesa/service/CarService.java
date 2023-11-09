package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.model.dao.Car;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {
    void save (Car car);
    public boolean isExist(String carTitle);
    List<Car> getAllCar();
}

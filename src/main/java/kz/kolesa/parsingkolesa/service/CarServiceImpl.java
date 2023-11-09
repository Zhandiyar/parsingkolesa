package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.model.dao.Car;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void save(Car car) {
        carRepository.save(car);
    }


    @Override
    public boolean isExist(String carTitle) {
        List<Car> cars = carRepository.findAll();
        for (Car k : cars){
            if (k.getTitle().equals(carTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }
}

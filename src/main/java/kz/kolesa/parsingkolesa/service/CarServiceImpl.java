package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.mapper.CarMapper;
import kz.kolesa.parsingkolesa.model.dao.CarDescriptionEntity;
import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDescriptionDto;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.repository.CarDescriptionRepository;
import kz.kolesa.parsingkolesa.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarDescriptionRepository carDescriptionRepository;
    private final CarMapper carMapper;

    @Override
    public void save(CarDto carDto) {
        carRepository.save(carMapper.mapDtoToEntity(carDto));
    }

    @Override
    public void save(CarDescriptionDto carDescriptionDto) {
        carDescriptionRepository.save(carMapper.mapDtoToEntity(carDescriptionDto));
    }

    @Override
    public boolean isExist(String carTitle) {
        List<CarEntity> cars = carRepository.findAll();
        for (CarEntity k : cars) {
            if (k.getTitle().equals(carTitle)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }
}

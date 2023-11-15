package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.model.dao.CarDescriptionEntity;
import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDescriptionDto;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.repository.CarDescriptionRepository;
import kz.kolesa.parsingkolesa.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarDescriptionRepository carDescriptionRepository;

    @Override
    public void saveCar(CarDto carDto) {
        CarEntity car = CarEntity.builder()
                .title(carDto.getTitle())
                .description(carDto.getDescription())
                .location(carDto.getLocation())
                .price(carDto.getPrice())
                .build();
        carRepository.save(car);
    }

    @Override
    public void saveCarDescription(CarDescriptionDto carDescriptionDto) {
        CarDescriptionEntity carDescription = CarDescriptionEntity.builder()
                .generation(carDescriptionDto.getGeneration())
                .rudder(carDescriptionDto.getRudder())
                .description(carDescriptionDto.getDescription())
                .body(carDescriptionDto.getBody())
                .transmission(carDescriptionDto.getTransmission())
                .color(carDescriptionDto.getColor())
                .drive(carDescriptionDto.getDrive())
                .engineVolume(carDescriptionDto.getEngineVolume())
                .customsCleared(carDescriptionDto.getCustomsCleared())
                .sellerComment(carDescriptionDto.getSellerComment())
                .build();
        carDescriptionRepository.save(carDescription);
    }

    @Override
    public boolean isExist(String carTitle) {
        List<CarEntity> cars = carRepository.findAll();
        for (CarEntity k : cars){
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

package kz.kolesa.parsingkolesa.service;

import kz.kolesa.parsingkolesa.mapper.CarMapper;
import kz.kolesa.parsingkolesa.model.dao.CarsEntity;
import kz.kolesa.parsingkolesa.model.dto.CarDto;
import kz.kolesa.parsingkolesa.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public void save(CarDto carDto) {
        CarsEntity car = CarsEntity.builder()
                .brand(carDto.getBrand())
                .location(carDto.getLocation())
                .price(carDto.getPrice())
                .generation(carDto.getGeneration())
                .rudder(carDto.getRudder())
                .body(carDto.getBody())
                .transmission(carDto.getTransmission())
                .color(carDto.getColor())
                .drive(carDto.getDrive())
                .engineVolume(carDto.getEngineVolume())
                .customsCleared(carDto.getCustomsCleared())
                .linkCar(carDto.getLinkCar())
                .linksImage(carDto.getLinksImage())
                .description(carDto.getDescription())
                .sellerComment(carDto.getSellerComment())
                .build();
        carRepository.save(car);
    }

    @Override
    public List<CarsEntity> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<CarsEntity> findById(Long id) {
        return carRepository.findById(id);
    }
}

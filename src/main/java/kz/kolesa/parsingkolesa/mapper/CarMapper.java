package kz.kolesa.parsingkolesa.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarMapper {
    private final ModelMapper modelMapper;

//    public CarEntity mapDtoToEntity(CarDto carDto) {
//        return modelMapper.map(carDto, CarEntity.class);
//    }

//    public CarDescriptionEntity mapDtoToEntity(CarDescriptionDto carDescriptionDto) {
//        return modelMapper.map(carDescriptionDto, CarDescriptionEntity.class);
//    }
}

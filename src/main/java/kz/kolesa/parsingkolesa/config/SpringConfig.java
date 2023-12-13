package kz.kolesa.parsingkolesa.config;

import kz.kolesa.parsingkolesa.mapper.CarMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SpringConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}

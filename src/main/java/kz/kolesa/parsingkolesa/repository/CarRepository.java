package kz.kolesa.parsingkolesa.repository;

import kz.kolesa.parsingkolesa.model.dao.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByIdAndAndTitle(Long id, String title);
}

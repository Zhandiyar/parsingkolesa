package kz.kolesa.parsingkolesa.repository;

import kz.kolesa.parsingkolesa.model.dao.CarsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarsEntity, Long> {
    Optional<CarsEntity> findById(Long id);
}

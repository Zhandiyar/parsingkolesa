package kz.kolesa.parsingkolesa.repository;

import kz.kolesa.parsingkolesa.model.dao.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    CarEntity findByIdAndAndTitle(Long id, String title);
}

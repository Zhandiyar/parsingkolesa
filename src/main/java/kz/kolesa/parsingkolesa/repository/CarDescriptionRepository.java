package kz.kolesa.parsingkolesa.repository;

import kz.kolesa.parsingkolesa.model.dao.CarDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDescriptionRepository extends JpaRepository<CarDescriptionEntity, Long> {
}
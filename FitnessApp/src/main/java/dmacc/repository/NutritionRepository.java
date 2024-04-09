package dmacc.repository;

import dmacc.beans.Nutrition;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NutritionRepository extends JpaRepository<Nutrition, Long> {
}
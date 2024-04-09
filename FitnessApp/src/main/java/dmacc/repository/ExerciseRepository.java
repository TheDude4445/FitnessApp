package dmacc.repository;

import dmacc.beans.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}
package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.WeightBMI;

@Repository
public interface WeightBMIRepository extends JpaRepository<WeightBMI, Long> {
}

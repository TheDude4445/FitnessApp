package dmacc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Client;
import dmacc.beans.WeightBMI;

@Repository
public interface WeightBMIRepository extends JpaRepository<WeightBMI, Long> {
	List<WeightBMI> findByClient(Client client);
}

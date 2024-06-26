package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}

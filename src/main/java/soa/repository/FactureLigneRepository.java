package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Client;
import soa.entities.FactureLigne;

public interface FactureLigneRepository extends JpaRepository<FactureLigne, Long> {
}

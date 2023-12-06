package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Client;
import soa.entities.Reglement;

public interface ReglementRepository extends JpaRepository<Reglement, Long> {
}

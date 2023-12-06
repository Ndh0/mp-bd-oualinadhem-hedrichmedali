package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.FactureLigne;
import soa.entities.ReglementLigne;

public interface ReglementLigneRepository  extends JpaRepository<ReglementLigne, Long> {
}

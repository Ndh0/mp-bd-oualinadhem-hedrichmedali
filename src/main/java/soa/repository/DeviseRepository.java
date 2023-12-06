package soa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soa.entities.Client;
import soa.entities.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Long> {
}

package soa.metier;

import org.springframework.stereotype.Service;
import soa.entities.Facture;
import soa.entities.Reglement;
import soa.entities.ReglementLigne;
import soa.metier.ReglementLigneMetierInterface;
import soa.repository.ReglementLigneRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReglementLigneMetierImpl implements ReglementLigneMetierInterface {

    private final ReglementLigneRepository reglementLigneRepository;

    public ReglementLigneMetierImpl(ReglementLigneRepository reglementLigneRepository) {
        this.reglementLigneRepository = reglementLigneRepository;
    }

    @Override
    public void ajouterReglementLigne(ReglementLigne rl ) {

        reglementLigneRepository.save(rl);
    }

    @Override
    public void modifierReglementLigne(ReglementLigne rl) {
        reglementLigneRepository.save(rl);
    }

    @Override
    public ReglementLigne getReglementLigne(Long id) {
        return reglementLigneRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerReglementLigne(Long id) {
        reglementLigneRepository.deleteById(id);
    }

    @Override
    public List<ReglementLigne> getAllReglementLignes() {
        return reglementLigneRepository.findAll();
    }
}

package soa.metier;

import org.springframework.stereotype.Service;
import soa.entities.Reglement;
import soa.entities.ReglementLigne;
import soa.repository.ReglementRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ReglementMetierImpl implements ReglementMetierInterface{
    private final ReglementRepository reglementRepository;


    public ReglementMetierImpl(ReglementRepository reglementRepository) {
        this.reglementRepository = reglementRepository;

    }

    @Override
    public void ajouterReglement(Reglement r, Collection<ReglementLigne> reglementLignes) {
        r.setReglementLignes((List<ReglementLigne>) reglementLignes);
        reglementRepository.save(r);
    }

    @Override
    public void modifierReglement(Reglement r) {
        reglementRepository.save(r);
    }

    @Override
    public Reglement getReglement(Long id) {
        return reglementRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerReglement(Long id) {
        reglementRepository.deleteById(id);
    }

    @Override
    public List<ReglementLigne> getReglementLignes(Long idReglement) {
        Optional<Reglement> reglementOptional = reglementRepository.findById(idReglement);
        return reglementOptional.map(Reglement::getReglementLignes).orElse(null);
    }
}

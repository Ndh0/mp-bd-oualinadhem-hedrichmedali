package soa.metier;

import org.springframework.stereotype.Service;
import soa.entities.Facture;
import soa.entities.FactureLigne;
import soa.repository.FactureLigneRepository;

import java.util.List;

@Service
public class FactureLigneMetierImpl implements FactureLigneMetierInterface{
    private final FactureLigneRepository factureLigneRepository;

    public FactureLigneMetierImpl(FactureLigneRepository factureLigneRepository) {
        this.factureLigneRepository = factureLigneRepository;
    }

    @Override
    public void ajouterFactureLigne(Facture f, FactureLigne fr) {
        fr.setFacture(f);
        factureLigneRepository.save(fr);
    }

    @Override
    public void modifierFactureLigne(FactureLigne fr) {
        factureLigneRepository.save(fr);
    }

    @Override
    public FactureLigne getFactureLigne(Long id) {
        return factureLigneRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerFactureLigne(Long id) {
        factureLigneRepository.deleteById(id);
    }

    @Override
    public List<FactureLigne> getAllFacturesLignes() {
        return factureLigneRepository.findAll();
    }
}

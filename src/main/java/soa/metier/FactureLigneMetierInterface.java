package soa.metier;

import soa.entities.Facture;
import soa.entities.FactureLigne;

import java.util.List;

public interface FactureLigneMetierInterface {
    void ajouterFactureLigne(Facture f,FactureLigne fr);
    void modifierFactureLigne(FactureLigne fr);
    FactureLigne getFactureLigne(Long id);
    void supprimerFactureLigne(Long id);
    List<FactureLigne> getAllFacturesLignes();
}

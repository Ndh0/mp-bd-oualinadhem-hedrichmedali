package soa.metier;

import soa.entities.Facture;
import soa.entities.Reglement;
import soa.entities.ReglementLigne;

import java.util.List;

public interface ReglementLigneMetierInterface {
    void ajouterReglementLigne(ReglementLigne rl);
    void modifierReglementLigne(ReglementLigne rl);
    ReglementLigne getReglementLigne(Long id);
    void supprimerReglementLigne(Long id);
    List<ReglementLigne> getAllReglementLignes();
}

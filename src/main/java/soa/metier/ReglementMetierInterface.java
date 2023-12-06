package soa.metier;

import soa.entities.Reglement;
import soa.entities.ReglementLigne;

import java.util.Collection;
import java.util.List;

public interface ReglementMetierInterface {
    void ajouterReglement(Reglement r, Collection<ReglementLigne> reglementLignes);
    void modifierReglement(Reglement r);
    Reglement getReglement(Long id);
    void supprimerReglement(Long id);
    List<ReglementLigne> getReglementLignes(Long idReglement);

}

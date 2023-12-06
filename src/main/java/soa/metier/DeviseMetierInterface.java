package soa.metier;

import soa.entities.Devise;

import java.util.List;

public interface DeviseMetierInterface {
    void ajouterDevise(Devise d);
    void modifierDevise(Devise devise);
    void supprimerDevise(Long id);
    List<Devise> getAllDevises();
    Devise getDevise(Long id);
}

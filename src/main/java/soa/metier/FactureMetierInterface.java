package soa.metier;

import soa.entities.Client;
import soa.entities.Facture;
import soa.entities.FactureLigne;
import soa.entities.ReglementLigne;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface FactureMetierInterface {
    void ajouterFacture(Facture f, Client c, ArrayList<FactureLigne> fr,ArrayList<ReglementLigne> rl);
    void modifierFacture(Facture f);
    Facture getFacture(Long id);
    void supprimerFacture(Long id);

    List<Facture> listesFactures();
    List<FactureLigne> getFacturesLignes(Long idFact);
    List<ReglementLigne> getReglementsLignes(Long idFact);
    ///////////////////////
    Float totalFacturesClient(Long clientId);
    Float totalFacturesClients();
    Float totalPaiementClient(Long id); //Totfacture-totReglement
    Float totalPaiementClients();

    Float totalDettesClient(Client c);
    Float totalDettesClients();
    Float chiffreAnnuelPrevueClient(Date d,Client c);
    Float chiffreAnnuelPrevueClients(Date d);

    Float chiffreMensuellePrevueClient(Date d,Client c);
    Float chiffreMensuellePrevueClients(Date d);
    Float chiffreHebdomadairePrevueClient(Date d,Client c);
    Float chiffreHebdomadairePrevueClients(Date d);


    Float chiffreAffaireMax();
    Float chiffreAffaireMin();
}

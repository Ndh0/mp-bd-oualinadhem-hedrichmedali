package soa.metier;

import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;

import java.util.List;

public interface ClientMetierInterface {
    void ajouterClient(Client c);
    public void ajouterClient(Client c,Devise d);
    void modifierClient(Client c);
    void supprimerClient(Long id);
    Client getClient(Long id);
    List<Client> listeClients();
    List<Facture> getFacturesClient(Long clientId);
    Devise getDeviseClient(Client c);


}

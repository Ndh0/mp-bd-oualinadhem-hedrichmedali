package soa.metier;

import soa.entities.Client;
import soa.entities.Devise;
import soa.entities.Facture;
import soa.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.repository.DeviseRepository;

import java.util.List;

@Service
public class ClientMetierImpl implements ClientMetierInterface {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DeviseRepository devise;


    @Override
    public void ajouterClient(Client c) {
        clientRepository.save(c);
    }
    @Override
    public void ajouterClient(Client c,Devise d) {
        devise.save(d);
        c.setDevise(d);
        clientRepository.save(c);
    }

    @Override
    public void modifierClient(Client c) {
        clientRepository.save(c); // Assuming save can handle both adding and updating
    }

    @Override
    public void supprimerClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> listeClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Facture> getFacturesClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            return (List<Facture>) client.getFactures();
        }
        return null;
    }

    @Override
    public Devise getDeviseClient(Client c) {
        if (c==null)
            return null;
        else
            return c.getDevise();
    }
}

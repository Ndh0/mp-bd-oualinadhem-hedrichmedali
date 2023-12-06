package soa.metier;

import org.springframework.stereotype.Service;
import soa.entities.Devise;
import soa.repository.DeviseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviseMetierImpl implements DeviseMetierInterface{


    private final DeviseRepository deviseRepository;

    public DeviseMetierImpl(DeviseRepository deviseRepository) {
        this.deviseRepository = deviseRepository;
    }

    @Override
    public void ajouterDevise(Devise d) {
        deviseRepository.save(d);
    }

    @Override
    public void modifierDevise(Devise devise) {

            // Implement the modification logic here based on your requirements
            // For example, you can update attributes of the Devise object and save it.
            deviseRepository.save(devise);

    }

    @Override
    public void supprimerDevise(Long id) {
        deviseRepository.deleteById(id);
    }

    @Override
    public List<Devise> getAllDevises() {
        return deviseRepository.findAll();
    }

    @Override
    public Devise getDevise(Long id) {
        return deviseRepository.findById(id).orElse(null);
    }
}

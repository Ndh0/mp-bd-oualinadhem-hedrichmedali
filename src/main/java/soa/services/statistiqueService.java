package soa.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import soa.metier.FactureMetierInterface;
import soa.repository.FactureRepository;

@Service
@Transactional
public class statistiqueService {
    private final FactureMetierInterface factureRepository;

@Autowired
    public statistiqueService(FactureMetierInterface factureRepository) {
        this.factureRepository = factureRepository;
    }

    public  Float TotFactures(){
        return factureRepository.totalFacturesClients();
    }

    public  Float TotFacturesClient( Long id){
        System.out.println(id);
        return factureRepository.totalFacturesClient(id);
    }

    public Float totalPaiments(){
        return factureRepository.totalPaiementClients();
    }
}

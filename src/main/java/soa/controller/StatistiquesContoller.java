package soa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Client;
import soa.entities.Facture;
import soa.entities.Reponse;
import soa.entities.clt;
import soa.metier.FactureMetierImpl;
import soa.repository.ClientRepository;
import soa.repository.FactureRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/statistiques")
@CrossOrigin(origins ="*")
public class StatistiquesContoller {
    @Autowired
    private FactureMetierImpl factureRepository;
    @Autowired
    private ClientRepository clientRepository;
    private Reponse r=new Reponse();

    @GetMapping(value="/index",produces = {MediaType.APPLICATION_JSON_VALUE })
    public  Reponse index(){

        String message="Bonjour";
        r.setValeur(message);
        return r;
    }



    @GetMapping(value="/getAllFactsClts", produces = {MediaType.APPLICATION_JSON_VALUE })
    public  Reponse TotFactures(){


        return r;
    }
    @GetMapping(value="/getAllFactsClt", produces = {MediaType.APPLICATION_JSON_VALUE })
    public  Reponse TotFacturesClient(){
        r.setValeur("BONJOUR");

        r.setTotpays(factureRepository.totalPaiementClients());
        r.setTotfacts(factureRepository.totalFacturesClients());
        List<clt> lcl=new ArrayList<clt>();
        List<Client> l=new ArrayList<Client>();
        l=clientRepository.findAll();
        for (Client c: l) {
            clt cl=new clt();
            cl.setId(c.getId());
            cl.setNp(c.getNomCl()+"  "+c.getPrenomCl());
            lcl.add(cl);
        }
        r.setClts(lcl);
        for (clt c:lcl) {
            c.setTotFactClt(factureRepository.totalFacturesClient(c.getId()));
            c.setTotPayClt(factureRepository.totalPaiementClient(c.getId()));
            c.setTotD(c.getTotFactClt()-c.getTotPayClt());
        }

        r.setTotDe(r.getTotfacts()-r.getTotpays());
        return r;
    }
    @GetMapping(value="/totalPaiments",produces = {MediaType.APPLICATION_JSON_VALUE})
    public Reponse totalPaiments(){


        return r;
    }







}

package soa.controller;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import soa.entities.Produit;
import soa.repository.ProduitRepository;

import java.util.List;

@RestController // pour déclarer un service web de type REST
@RequestMapping("/produits") // http://localhost:8080/produits
@CrossOrigin(origins ="http://localhost:4200/")
public class ProduitRESTController {
    @Autowired // pour l'injection de dépendances
    private ProduitRepository produitRepos;
    // Message d'accueil
    // http://localhost:8080/produits/index (GET)

    @GetMapping(value ="/index" )
    public String accueil() {
        return "BienVenue au service Web REST 'produits'.....";
    }
    //Afficher la liste des produits
    // http://localhost:8080/produits/ (GET)
    @GetMapping(value= "/", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public List<Produit> getAllProduits() {
        return produitRepos.findAll();
    }
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE , MediaType.APPLICATION_JSON_VALUE })
    public Produit getProduit(@PathVariable Long id ){
        Produit p = produitRepos.findById(id).get();
        return p ;
    }
    @GetMapping(
// spécifier le path de la méthode
            value = "/delete/{id}")
    public void deleteProduit(@PathVariable Long id)
    {
        produitRepos.deleteById(id);
    }
    @PostMapping(
// spécifier le path de la méthode
            value = "/" ,
//spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE }
    )
    public Produit saveProduit(@RequestBody Produit p)
    {
        return produitRepos.save(p);
    }
    @PutMapping(
// spécifier le path de la méthode
            value = "/" ,
//spécifier le format de retour
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public Produit updateProduit(@RequestBody Produit p)
    {
        return produitRepos.save(p);
    }
    @DeleteMapping(
// spécifier le path de la méthode
            value = "/")
    public void deleteProduit(@RequestBody Produit p)
    {
        produitRepos.delete(p);
    }
}

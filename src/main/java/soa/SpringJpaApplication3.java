package soa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import soa.entities.*;
import soa.metier.*;
import org.springframework.web.filter.CorsFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication


public class SpringJpaApplication3 {
    static ClientMetierInterface clientMetier;
    static DeviseMetierInterface deviseMetier;
    static ProduitMetierInterface produitMetier;
    static CategorieMetierInterface categorieMetier;
    static FactureMetierInterface factureMetier;
    static FactureLigneMetierInterface factureLigneMetier;
    static ReglementMetierInterface reglementMetier;
    static ReglementLigneMetierInterface reglementLigneMetier;

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = null;
        java.util.Date date2 = null;
        java.util.Date date3 = null;
        java.util.Date date4 = null;
        java.util.Date dateFinPromotion = null;
        //trois objets de type date
        try
        {
            date1 = sdf.parse("2021-04-15");
            date2 = sdf.parse("2022-02-20");
            date3 = sdf.parse("2023-01-10");
            date4 = sdf.parse("2023-05-15");
            dateFinPromotion = sdf.parse("2023-01-01");
        }
        catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("---------Injection de dépendances----------");
        //Commencer par réaliser les injections de dépendances pour les objets de type Repository
        // référencer le contexte
        ApplicationContext contexte = SpringApplication.run(SpringJpaApplication3.class, args);
        // Récupérer une implémentation de l'interface "ProduitRepository" par injection de dépendance
        produitMetier = contexte.getBean(ProduitMetierInterface.class);
        categorieMetier = contexte.getBean(CategorieMetierInterface.class);
        factureMetier = contexte.getBean(FactureMetierInterface.class);
        factureLigneMetier = contexte.getBean(FactureLigneMetierInterface.class);
        reglementMetier = contexte.getBean(ReglementMetierInterface.class);
        reglementLigneMetier = contexte.getBean(ReglementLigneMetierInterface.class);
        clientMetier = contexte.getBean(ClientMetierInterface.class);
        deviseMetier = contexte.getBean(DeviseMetierInterface.class);

        /////ajout categorie
        Categorie c1=new Categorie( "INF","Informatique");
        Categorie c2=new Categorie( "PL","Plastique");
        categorieMetier.ajouterCategorie(c1);
        categorieMetier.ajouterCategorie(c2);

        ///ajout produits

        Produit pOrdinateur =new Produit("Ordinateur", 2400, 1, date1 );
        Produit tablette =new Produit("Tablette", 700, 2, date2 );
        Produit helicopter =new Produit("Helicopter", 1000, 50, date3 );
        produitMetier.ajouterProduit(pOrdinateur,c1);
        produitMetier.ajouterProduit(tablette,c1);
        produitMetier.ajouterProduit(helicopter,c2);
        afficherTousLesProduits();

        //ajout devise

        Devise d1 =new Devise("euro",3.4F,null);
        Devise d2 =new Devise("dollar",3.3F,null);
        Devise d3 =new Devise("dt",1F,null);


        //Ajout client

        Client cl1=new Client("Ayadi","slim",134845L,"sfax");
        Client cl2=new Client("ben Omar","Ali",134845L,"beja");
        Client cl3=new Client("Sallemi","Hama",134845L,"tunis");
        clientMetier.ajouterClient(cl1,d1);
        clientMetier.ajouterClient(cl2,d2);
        clientMetier.ajouterClient(cl3,d3);
        afficherTousLesClients();

        //Factures
        Collection<FactureLigne> fla=new ArrayList<FactureLigne>();
        Collection<ReglementLigne> rl=new ArrayList<ReglementLigne>();
        Collection<FactureLigne> fla2=new ArrayList<FactureLigne>();
        Collection<ReglementLigne> rl2=new ArrayList<ReglementLigne>();
        Collection<FactureLigne> fla3=new ArrayList<FactureLigne>();
        Collection<ReglementLigne> rl3=new ArrayList<ReglementLigne>();
        ///ajout reglement
        Reglement r1= new Reglement(250F,date3,rl);
        Reglement r2=new Reglement(800F,date4,rl2);
        Reglement r3=new Reglement(700F,date4,rl2);
        reglementMetier.ajouterReglement(r1,rl);
        reglementMetier.ajouterReglement(r2,rl2);
        reglementMetier.ajouterReglement(r3,rl3);


        Facture f1=new Facture(date1,500F,cl1,fla,rl);
        Facture f2=new Facture(date3,800F,cl2,fla2,rl2);
        Facture f3=new Facture(date3,1050F,cl3,fla3,rl3);
        factureMetier.ajouterFacture(f1,cl1, (ArrayList<FactureLigne>) fla, (ArrayList<ReglementLigne>) rl);
        factureMetier.ajouterFacture(f2,cl2, (ArrayList<FactureLigne>) fla2, (ArrayList<ReglementLigne>) rl2);
        factureMetier.ajouterFacture(f3,cl3, (ArrayList<FactureLigne>) fla3, (ArrayList<ReglementLigne>) rl3);
        Collection<Facture> ff=new ArrayList<Facture>();
        ff.add(f1);
        ff.add(f2);
        ff.add(f3);
        ReglementLigne regl1= new ReglementLigne(date3,250F,r1,f1);
        ReglementLigne regl2= new ReglementLigne(date3,800F,r2,f2);

        ((ArrayList<ReglementLigne>) rl).add(regl1);
        ((ArrayList<ReglementLigne>) rl).add(regl2);

        reglementLigneMetier.ajouterReglementLigne(regl1);
        reglementLigneMetier.ajouterReglementLigne(regl2);
       

        r1.setReglementLignes((List<ReglementLigne>) rl);
        r2.setReglementLignes((List<ReglementLigne>) rl2);
        ///Factures Lignes

        FactureLigne fl1=new FactureLigne(2,15F,30F,pOrdinateur);
        FactureLigne fl2=new FactureLigne(3,15F,45F,tablette);
        FactureLigne fl3=new FactureLigne(4,15F,60F,helicopter);
        factureLigneMetier.ajouterFactureLigne(f1,fl1);
        factureLigneMetier.ajouterFactureLigne(f2,fl2);
        factureLigneMetier.ajouterFactureLigne(f3,fl3);

        fla.add(fl1);
        fla.add(fl2);
        fla2.add(fl3);
        f1.setFactureLignes((ArrayList<FactureLigne>) fla);
        f2.setFactureLignes((ArrayList<FactureLigne>) fla2);
        cl1.setFactures(ff);
       // factureMetier.modifierFacture(f1);
       // factureMetier.modifierFacture(f2);
        float totalPrice = 0.0f;
        System.out.println( f1.getClient());
        for (Facture facture : cl1.getFactures()) {
            totalPrice+=facture.getSomme();
        }
        System.out.println( totalPrice);
        System.out.println(r1.getMontantpaye());
        System.out.println(reglementLigneMetier.getAllReglementLignes());

    }
    static void afficherTousLesProduits()
    {
        System.out.println("********************Début**********************");
        System.out.println("Afficher tous les produits...");
        System.out.println("***********************************************");

        List<Produit> lp = produitMetier.listeProduits();
        for (Produit p : lp)
        {
            System.out.println(p);
        }
        System.out.println("********************Fin************************");
    }
    static void afficherTousLesClients()
    {
        System.out.println("********************Début**********************");
        System.out.println("Afficher tous les produits...");
        System.out.println("***********************************************");

        List<Client> lp = clientMetier.listeClients();
        for (Client p : lp)
        {
            System.out.println(p);
        }
        System.out.println("********************Fin************************");
    }






}

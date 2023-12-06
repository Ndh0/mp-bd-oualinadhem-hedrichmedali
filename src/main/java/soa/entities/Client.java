package soa.entities;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 20)
    private String NomCl;
    @Column(length = 20)
    private String PrenomCl;
    @Column
    private Long numCl;
    @Column(length = 50)
    private String adresseCl;
    @OneToOne(mappedBy = "client")
    private Devise devise;
    @OneToMany (mappedBy = "client" ,cascade = {CascadeType.REMOVE  , CascadeType.MERGE , CascadeType.PERSIST},fetch = FetchType.EAGER)
    private Collection<Facture> factures = new ArrayList<Facture>();

    public Client(String nomCl, String prenomCl, Long numCl, String adresseCl) {

        NomCl = nomCl;
        PrenomCl = prenomCl;
        this.numCl = numCl;
        this.adresseCl = adresseCl;


    }

    public Client() {

    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", NomCl='" + NomCl + '\'' +
                ", PrenomCl='" + PrenomCl + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCl() {
        return NomCl;
    }

    public void setNomCl(String nomCl) {
        NomCl = nomCl;
    }

    public String getPrenomCl() {
        return PrenomCl;
    }

    public void setPrenomCl(String prenomCl) {
        PrenomCl = prenomCl;
    }

    public Long getNumCl() {
        return numCl;
    }

    public void setNumCl(Long numCl) {
        this.numCl = numCl;
    }

    public String getAdresseCl() {
        return adresseCl;
    }

    public void setAdresseCl(String adresseCl) {
        this.adresseCl = adresseCl;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public Collection<Facture> getFactures() {
        return factures;
    }

    public void setFactures(Collection<Facture> factures) {
        this.factures = factures;
    }


}

package soa.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import jakarta.persistence.*;

@Entity
public class Facture
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private java.util.Date date;

    @Column()
    private Float somme;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Client client;
    @OneToMany(mappedBy = "facture" ,cascade = {CascadeType.REMOVE  , CascadeType.MERGE , CascadeType.PERSIST})
    private Collection<FactureLigne> factureLignes = new ArrayList<FactureLigne>();
    @OneToMany(mappedBy = "facture" ,cascade = {CascadeType.REMOVE  , CascadeType.MERGE , CascadeType.PERSIST})
    private Collection<ReglementLigne> reglementLignes = new ArrayList<ReglementLigne>();


    public Facture(Date date, Float somme, Client client, Collection<FactureLigne> factureLignes, Collection<ReglementLigne> reglementLignes) {

        this.date = date;
        this.somme = somme;
        this.client = client;
        this.factureLignes = factureLignes;
        this.reglementLignes = reglementLignes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getSomme() {
        return somme;
    }

    public Facture() {
    }

    public void setSomme(Float somme) {
        this.somme = somme;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Collection<FactureLigne> getFactureLignes() {
        return factureLignes;
    }

    public void setFactureLignes(ArrayList<FactureLigne> factureLignes) {
        this.factureLignes = factureLignes;
    }

    public Collection<ReglementLigne> getReglementLignes() {
        return reglementLignes;
    }

    public void setReglementLignes(Collection<ReglementLigne> reglementLignes) {
        this.reglementLignes = reglementLignes;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "id=" + id +
                ", date=" + date +
                ", somme=" + somme +
                ", client=" + client +
                '}';
    }
}
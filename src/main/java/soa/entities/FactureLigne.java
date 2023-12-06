package soa.entities;

import jakarta.persistence.*;

@Entity
public class FactureLigne {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private int quantite;
    @Column
    private Float prix;
    @Column
    private Float prixTot;
    @ManyToOne (cascade = {CascadeType.MERGE})
    private Produit produit;

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    @ManyToOne (cascade = {CascadeType.MERGE})
    private Facture facture;


    public FactureLigne() {
    }

    public FactureLigne(int quantite, Float prix, Float prixTot, Produit produit) {

        this.quantite = quantite;
        this.prix = prix;
        this.prixTot = prixTot;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getPrixTot() {
        return prixTot;
    }

    public void setPrixTot(Float prixTot) {
        this.prixTot = prixTot;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "FactureLigne{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", prix=" + prix +
                ", prixTot=" + prixTot +
                ", produit=" + produit +
                '}';
    }
}

package soa.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ReglementLigne {
    @Id
    @GeneratedValue
    private Long id;

    public ReglementLigne() {
    }

    @Temporal(TemporalType.DATE)
    private Date dateReglement;

    @Column
    private float montantpaye;

    @ManyToOne (cascade = {CascadeType.MERGE})
    private Reglement reglement;
    @ManyToOne (cascade = {CascadeType.MERGE})
    private Facture facture;

    @Override
    public String toString() {
        return "ReglementLigne{" +
                "id=" + id +
                ", dateReglement=" + dateReglement +
                ", montantpaye=" + montantpaye +
                '}';
    }

    public ReglementLigne(Date dateReglement, float montantpaye, Reglement reglement, Facture facture) {

        this.dateReglement = dateReglement;
        this.montantpaye = montantpaye;
        this.reglement = reglement;
        this.facture = facture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Date dateReglement) {
        this.dateReglement = dateReglement;
    }

    public float getMontantpaye() {
        return montantpaye;
    }

    public void setMontantpaye(float montantpaye) {
        this.montantpaye = montantpaye;
    }

    public Reglement getReglement() {
        return reglement;
    }

    public void setReglement(Reglement reglement) {
        this.reglement = reglement;
    }

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
}

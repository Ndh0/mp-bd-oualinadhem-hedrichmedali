package soa.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Reglement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private float montantpaye;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToMany(mappedBy = "reglement", cascade = CascadeType.ALL)
    private Collection<ReglementLigne> reglementLignes;

    public Reglement() {
    }

    public Reglement(float montantpaye, Date date, Collection<ReglementLigne> reglementLignes) {
        this.reglementLignes=reglementLignes;
        this.montantpaye = montantpaye;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMontantpaye() {
        return montantpaye;
    }

    public void setMontantpaye(float montantpaye) {
        this.montantpaye = montantpaye;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ReglementLigne> getReglementLignes() {
        return (List<ReglementLigne>) reglementLignes;
    }

    public void setReglementLignes(List<ReglementLigne> reglementLignes) {
        this.reglementLignes = reglementLignes;
    }

    @Override
    public String toString() {
        return "Reglement{" +
                "id=" + id +
                ", montantpaye=" + montantpaye +
                ", date=" + date +
                '}';
    }
}
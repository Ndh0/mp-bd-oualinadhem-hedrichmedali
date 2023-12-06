package soa.entities;

import jakarta.persistence.*;

@Entity
public class Devise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column
    private String nom;
@Column
    private float tauxexchange;

    @OneToOne(cascade= {CascadeType.PERSIST})
    private Client client;

    // Constructors


    public Devise() {
    }

    public Devise(String nom, float tauxexchange, Client client) {

        this.nom = nom;
        this.tauxexchange = tauxexchange;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getTauxexchange() {
        return tauxexchange;
    }

    public void setTauxexchange(float tauxexchange) {
        this.tauxexchange = tauxexchange;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "Devise{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", tauxexchange=" + tauxexchange +
                ", client=" + client +
                '}';
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
package soa.entities;


import java.util.List;

public class Reponse {
    private String valeur;
    private Float totfacts;
    private Float totpays;
    private List<clt> clts;

    private
    Float totDe;

    public Float getTotDe() {
        return totDe;
    }

    public void setTotDe(Float totDe) {
        this.totDe = totDe;
    }

    public String getValeur() {
        return valeur;
    }

    public Float getTotfacts() {
        return totfacts;
    }

    public void setTotfacts(Float totfacts) {
        this.totfacts = totfacts;
    }

    public Reponse() {
    }

    public Float getTotpays() {
        return totpays;
    }

    public void setTotpays(Float totpays) {
        this.totpays = totpays;
    }



    public List<clt> getClts() {
        return clts;
    }

    public void setClts(List<clt> clts) {
        this.clts = clts;
    }


    public Reponse(String valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Reponse{" +
                "valeur='" + valeur + '\'' +
                '}';
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}

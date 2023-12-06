package soa.entities;

public class clt {
    private Long id;
    private String np;
    private Float totFactClt;
    private Float totPayClt;

    private Float totD;

    public Float getTotD() {
        return totD;
    }

    public void setTotD(Float totD) {
        this.totD = totD;
    }

    public clt() {
    }

    @Override
    public String toString() {
        return "clt{" +
                "id=" + id +
                ", np='" + np + '\'' +
                ", totFactClt=" + totFactClt +
                ", totPayClt=" + totPayClt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public Float getTotFactClt() {
        return totFactClt;
    }

    public void setTotFactClt(Float totFactClt) {
        this.totFactClt = totFactClt;
    }

    public Float getTotPayClt() {
        return totPayClt;
    }

    public void setTotPayClt(Float totPayClt) {
        this.totPayClt = totPayClt;
    }
}

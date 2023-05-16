package hr.java.vjezbe.entitet;

public non-sealed class PoslovniKorisnik extends Korisnik implements Recenzent{
    private String naziv;
    private String web;

    public PoslovniKorisnik(String naziv, String web, String email, String telefon) {
        super(email, telefon);
        this.naziv = naziv;
        this.web = web;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Override
    public String dohvatiKontakt() {
        return getNaziv() + " " + getWeb() + " - " + getEmail() + " - " + getTelefon();
    }
}

package hr.java.vjezbe.vrdoljak7.entitet;

public final class PoslovniKorisnik extends Korisnik implements Recenzent {

    private String naziv;
    private String web;

    public PoslovniKorisnik(Long id, String naziv, String web, String email, String telefon) {
        super(id, email, telefon);
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
        return "Naziv tvrtke: "+naziv+", mail: "+getEmail()+", Web: "+web+", telefon: "+getTelefon();
    }
}

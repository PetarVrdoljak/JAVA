package hr.java.vjezbe.entitet;

public final class PrivatniKorisnik extends Korisnik implements Recenzent {

    private String ime;
    private String prezime;

    public PrivatniKorisnik(Long id, String ime, String prezime, String email, String telefon) {
        super(id, email, telefon);
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String dohvatiKontakt() {
        return "Osobni podaci prodavatelja: "+ime+" "+prezime+", mail: "+getEmail()+", telefon: "+getTelefon();
    }
}

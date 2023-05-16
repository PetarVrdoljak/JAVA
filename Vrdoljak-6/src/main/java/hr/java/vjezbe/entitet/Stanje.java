package hr.java.vjezbe.entitet;

public enum Stanje {

    Novo(1,"novo"),
    Izvrsno(2,"izvrsno"),
    Rabljeno(3,"rabljeno"),
    Neispravno(4,"neispravno");
    private int broj;
    private String name;

    Stanje(int broj, String name) {
        this.broj = broj;
        this.name = name;
    }

    public int getBroj() {
        return broj;
    }


    public String getName() {
        return name;
    }
    public static Stanje fromBroj(int broj) {
        for (Stanje stanje : Stanje.values()) {
            if (stanje.broj == broj) {
                return stanje;
            }
        }
        throw new IllegalArgumentException("Nepoznat broj stanja: " + broj);
    }
}


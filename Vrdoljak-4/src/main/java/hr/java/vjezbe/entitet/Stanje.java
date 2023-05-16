package hr.java.vjezbe.entitet;

public enum Stanje {

    Novo("novo"),
    Izvrsno("izvrsno"),
    Rabljeno("rabljeno"),
    Neispravno("neispravno");
    private String name;

    Stanje(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


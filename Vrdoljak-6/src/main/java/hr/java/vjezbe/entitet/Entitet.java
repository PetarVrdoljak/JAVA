package hr.java.vjezbe.entitet;

public abstract class Entitet {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entitet(Long id) {
        this.id = id;
    }
}

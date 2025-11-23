package enums;

public enum Tipo {
    CACHORRO("Cachorro"),
    GATO("Gato");

    private final String TIPO;

    Tipo(String tipo) {
        this.TIPO = tipo;
    }

    public String getTIPO() {
        return TIPO;
    }
}

package enums;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String SEXO;

    Sexo(String sexo) {
        this.SEXO = sexo;
    }

    public String getSEXO() {
        return SEXO;
    }

}

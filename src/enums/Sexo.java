package enums;

public enum Sexo {
    MACHO("Macho"),
    FEMEA("Femea");

    private final String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }

}

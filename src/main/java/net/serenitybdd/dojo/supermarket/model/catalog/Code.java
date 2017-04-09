package net.serenitybdd.dojo.supermarket.model.catalog;

public class Code {

    private String code;

    public Code(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        return code != null ? code.equals(code1.code) : code1.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}

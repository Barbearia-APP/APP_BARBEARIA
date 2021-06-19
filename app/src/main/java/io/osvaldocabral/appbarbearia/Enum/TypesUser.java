package io.osvaldocabral.appbarbearia.Enum;

public enum TypesUser {
    CLIENT("Cliente"),FACTORY("Empresa");

    private String description;

    TypesUser(String cliente) {
        this.description = cliente;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

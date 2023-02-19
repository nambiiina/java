package com.etech.microservice.micro_a.donnee.constants;

public enum WalletActionHistory {
    AJOUTER("ajouter"),
    SUPPRIMER("supprimer");

    private String value;

    WalletActionHistory(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static WalletActionHistory fromString(String text) {
        if (text != null) {
            for (WalletActionHistory role : WalletActionHistory.values()) {
                if (text.equalsIgnoreCase(role.value)) {
                    return role;
                }
            }
        }
        return null;
    }
}

package com.dusanweb.beba.enumeration;

public enum AllergyType {
    GLUTEN("Blé"),
    COWS_MILK("Lait de vache"),
    NUTS("Oléagineux"),
    FISH("Poisson"),
    SOYBEAN("Soja"),
    EGG("Oeuf");

    private final String allergen;

    private AllergyType(String allergen) {
        this.allergen = allergen;
    }

    public String getAllergen() {
        return this.allergen;
    }
}

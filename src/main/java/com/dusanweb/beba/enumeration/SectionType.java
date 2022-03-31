package com.dusanweb.beba.enumeration;

/*
    Type de section par tranche d'age
 */
public enum SectionType {
    BABY("tout-petit"),
    MIDDLE("moyen"),
    BIG("grand");

    private final String age;

    private SectionType(String age) {
        this.age = age;
    }

    public String getAge() {
        return this.age;
    }
}

package com.dusanweb.beba.enumeration;

public enum RoleType {
    USER("Parent"),
    ADMIN("Administrateur"),
    ASSISTANT("Auxiliaire de crèche");

    private final String role;

    private RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

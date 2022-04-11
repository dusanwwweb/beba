package com.dusanweb.beba.enumeration;

public enum RoleType {
    ROLE_USER("Parent"),
    ROLE_ADMIN("Administrateur"),
    ROLE_ASSISTANT("Auxiliaire de crèche");

    private final String role;

    private RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

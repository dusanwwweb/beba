package com.dusanweb.beba.enumeration;

public enum EmployeeRole {
    ADMIN("Administrateur"),
    ASSISTANT("Auxiliaire de crèche");

    private final String role;

    private EmployeeRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.dusanweb.beba.enumeration;

public enum ActivityType {
    /*
    AWAKE("Éveil"),
    REST("Repos"),
    EAT("Répas"),
    STOOL("Selle"),
    CRY("Pleurs"),
    CHANGE("Change");

     */
    EVEIL("Awake"),
    REPOS("Rest"),
    REPAS("Meal"),
    SELLE("Stool"),
    PLEURS("Cry"),
    CHANGE("Change");

    private final String activity;

    private ActivityType(String activity) {
        this.activity = activity;
    }

    public String getActivity(String key) {
        for (ActivityType item : ActivityType.values()){
            if (item.activity.equals(key)){
                return item.activity;
            }
        }
        return null;
    }
}

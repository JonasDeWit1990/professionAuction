package org.example.blizzardapi.Model;

class realm {
    private String name;
    private String slug;

    public realm(realm Realm) {
        this.name = Realm.getName();
        this.slug = Realm.getSlug();
    }

    public realm(String name, String slug) {
        this.name = name;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
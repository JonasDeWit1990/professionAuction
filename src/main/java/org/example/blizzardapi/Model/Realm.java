package org.example.blizzardapi.Model;

public class Realm {
    private String name;
    private String slug;

    public Realm(Realm realm) {
        this.name = realm.getName();
        this.slug = realm.getSlug();
    }

    public Realm() {

    }

    public Realm(String name, String slug) {
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
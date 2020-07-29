package org.test.shortner.model;

import javax.persistence.*;

/**
 *  Shortner entity classed that is mapped with DB
 */
@Entity
@Table(name = "shortner")
public class Shortner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long uniqueId;

    private String longUrl;

    public Shortner(){}
    public Shortner(Long uniqueId, String longUrl) {
        this.uniqueId = uniqueId;
        this.longUrl = longUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}

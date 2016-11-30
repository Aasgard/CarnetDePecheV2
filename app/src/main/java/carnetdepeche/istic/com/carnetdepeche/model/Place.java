package carnetdepeche.istic.com.carnetdepeche.model;

import java.util.Collection;

/**
 * Created by finnt on 30/11/2016.
 */

public class Place {
    private long id;
    private String nom;
    private GPSCoord gps;
    private String commentary;
    private Collection<Photo> photos;
    private User creator;

    public Place(long id, String nom, GPSCoord gps, String commentary, Collection<Photo> photos, User creator) {
        this.id = id;
        this.nom = nom;
        this.gps = gps;
        this.commentary = commentary;
        this.photos = photos;
        this.creator = creator;
    }

    public Place() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public GPSCoord getGps() {
        return gps;
    }

    public void setGps(GPSCoord gps) {
        this.gps = gps;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}


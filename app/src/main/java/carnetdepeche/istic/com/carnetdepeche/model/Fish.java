package carnetdepeche.istic.com.carnetdepeche.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by finnt on 30/11/2016.
 */

public class Fish {
    private long id;
    private Species species;
    private Collection<Photo> photos;
    private int size;
    private double weight;
    private String commentary;
    private User fisherMan;
    private Place place;

    public Fish(long id, Species species, Collection<Photo> photos,int size, double weight, String commentary, User fisherMan, Place place) {
        this.id = id;
        this.species = species;
        this.photos = photos;
        this.size = size;
        this.weight = weight;
        this.commentary = commentary;
        this.fisherMan = fisherMan;
        this.place = place;
    }

    public Fish() {
        this.photos = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public User getFisherMan() {
        return fisherMan;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public void setFisherMan(User fisherMan) {
        this.fisherMan = fisherMan;

    }
}


package carnetdepeche.istic.com.carnetdepeche.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by finnt on 30/11/2016.
 */

public class Fish {
    private long id;
    private Species species;
    private String photo;
    private int size;
    private double weight;
    private String commentary;
    private User fisherMan;
    private Place place;

    public Fish(long id, Species species, String photo,int size, double weight, String commentary, User fisherMan, Place place) {
        this.id = id;
        this.species = species;
        this.photo = photo;
        this.size = size;
        this.weight = weight;
        this.commentary = commentary;
        this.fisherMan = fisherMan;
        this.place = place;
    }

    public Fish() {

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

    public String getPhotos() {
        return photo;
    }

    public void setPhotos(String photo) {
        this.photo = photo;
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


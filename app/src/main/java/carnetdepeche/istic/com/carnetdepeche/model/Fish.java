package carnetdepeche.istic.com.carnetdepeche.model;

/**
 * Created by finnt on 30/11/2016.
 */

public class Fish {
    private String id;
    private String species;
    private String photo;
    private long size;
    private long weight;
    private String commentaries;
    private String fisherMan;
    private String placeName;

    public Fish(String id, String species, String photo,long size, long weight, String commentaries, String fisherMan, String placeName) {
        this.id = id;
        this.species = species;
        this.photo = photo;
        this.size = size;
        this.weight = weight;
        this.commentaries = commentaries;
        this.fisherMan = fisherMan;
        this.placeName = placeName;
    }

    public Fish() {

    }

    public String getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getCommentaries() {
        return commentaries;
    }

    public void setCommentaries(String commentaries) {
        this.commentaries = commentaries;
    }

    public String getFisherMan() {
        return fisherMan;
    }

    public String getPhotoPath() {
        return photo;
    }

    public void setPhotoPath(String photo) {
        this.photo = photo;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setFisherMan(String fisherMan) {
        this.fisherMan = fisherMan;
    }
}


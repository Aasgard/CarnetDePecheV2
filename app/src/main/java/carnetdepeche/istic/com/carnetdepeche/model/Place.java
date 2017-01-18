package carnetdepeche.istic.com.carnetdepeche.model;

/**
 * Created by finnt on 30/11/2016.
 */

public class Place {
    private String id;
    private String nom;
    private GPSCoord gps;
    private String commentary;
    private String photo;
    private String creatorId;

    public Place(String id, String nom, GPSCoord gps, String commentary, String photo, String creatorId) {
        this.id = id;
        this.nom = nom;
        this.gps = gps;
        this.commentary = commentary;
        this.photo = photo;
        this.creatorId = creatorId;
    }

    public Place() {
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", gps=" + gps +
                ", commentary='" + commentary + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public void setId(String id) {
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

    public String getPhotoPath() {
        return photo;
    }

    public void setPhotoPath(String photo) {
        this.photo = photo;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}


package carnetdepeche.istic.com.carnetdepeche.model;

/**
 * Created by finnt on 30/11/2016.
 */

public class Species {

    private long id;
    private String name;

    public Species(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Species() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

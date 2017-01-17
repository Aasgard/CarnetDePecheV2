package carnetdepeche.istic.com.carnetdepeche.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by finnt on 30/11/2016.
 */

public class User {

    private long  id;
    private String mail;
    private char[] pwd;
    private String firstName;
    private String lastName;
    private String token;
    private Collection<Fish> fishes;
    private Collection<Place> places;
    private String profilPicture;

    public User(String profilPicture, long id, String mail, char[] pwd, String firstName, String lastName, String token, Collection<Fish> fishes, Collection<Place> places) {
        this.profilPicture = profilPicture;
        this.id = id;
        this.mail = mail;
        this.pwd = pwd;
        this.firstName = firstName;
        this.lastName = lastName;
        this.token = token;
        this.fishes = fishes;
        this.places = places;
    }



    public User() {
        this.fishes = new LinkedList<>();
        this.places = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public char[] getPwd() {
        return pwd;
    }

    public void setPwd(char[] pwd) {
        this.pwd = pwd;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(Collection<Fish> fishes) {
        this.fishes = fishes;
    }

    public Collection<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<Place> places) {
        this.places = places;
    }

    public String getProfilPicture() {
        return profilPicture;
    }

    public void setProfilPicture(String profilPicture) {
        this.profilPicture = profilPicture;
    }
}

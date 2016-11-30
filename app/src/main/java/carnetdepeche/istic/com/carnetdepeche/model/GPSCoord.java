package carnetdepeche.istic.com.carnetdepeche.model;

/**
 * Created by finnt on 30/11/2016.
 */

public class GPSCoord {

    private double latitude;
    private double longitude;

    public GPSCoord(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GPSCoord() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

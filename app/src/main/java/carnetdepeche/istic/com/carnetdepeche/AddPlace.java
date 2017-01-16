package carnetdepeche.istic.com.carnetdepeche;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AddPlace extends AppCompatActivity implements OnMapReadyCallback {

    private MapFragment map;
    private GoogleMap gmap;
    private LocationManager locationManager;
    private Location location;
    private FloatingActionButton addPlaceValidate;
    private MarkerOptions lastMarkerPosition;
    private LatLng lastLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_place);
        getSupportActionBar().setTitle("Ajouter un coin de pêche");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            return;
        }
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        this.lastMarkerPosition = null;
        this.lastLatLng = null;

        addPlaceValidate = (FloatingActionButton) findViewById(R.id.add_place_validate);
        addPlaceValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Traitement et vérification pour insertion Firebase
                if (lastLatLng != null) {
                    Toast.makeText(AddPlace.this, lastLatLng.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "GPS Permission : OK", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "GPS Permission : Problem!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.gmap = googleMap;
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();
                lastMarkerPosition = new MarkerOptions().position(latLng);
                lastLatLng = latLng;
                googleMap.addMarker(lastMarkerPosition);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(17.0f).build();
                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                googleMap.moveCamera(cameraUpdate);
            }
        });
        LatLng local = new LatLng(this.location.getLatitude(), this.location.getLongitude());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(local).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.moveCamera(cameraUpdate);
    }

}

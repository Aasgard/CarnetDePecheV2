package carnetdepeche.istic.com.carnetdepeche;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import carnetdepeche.istic.com.carnetdepeche.model.GPSCoord;
import carnetdepeche.istic.com.carnetdepeche.model.Place;

public class ViewSinglePlace extends AppCompatActivity {

    private String single_place_id;
    private Place place_single;

    private EditText vsp_nom;
    private EditText vsp_commentaires;
    private EditText vsp_latitude;
    private EditText vsp_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_place);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        single_place_id = i.getStringExtra("id_place");

        vsp_nom = (EditText) findViewById(R.id.view_single_place_name);
        vsp_commentaires = (EditText) findViewById(R.id.view_single_place_commentaires);
        vsp_latitude = (EditText) findViewById(R.id.view_single_place_latitude);
        vsp_longitude = (EditText) findViewById(R.id.view_single_place_longitude);

        FirebaseDatabase.getInstance().getReference("place/" + this.single_place_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, String> placeMap = (Map<String, String>) dataSnapshot.getValue();
                place_single = new Place(dataSnapshot.getKey(), placeMap.get("nom"), new GPSCoord((Double) dataSnapshot.child("gps/latitude").getValue(), (Double) dataSnapshot.child("gps/longitude").getValue()), placeMap.get("commentary"), placeMap.get("photoPath"), placeMap.get("creatorId"));
                setTitle(place_single.getNom());

                vsp_nom.setText(place_single.getNom());
                vsp_commentaires.setText(place_single.getCommentary());
                vsp_latitude.setText(Double.toString(place_single.getGps().getLatitude()));
                vsp_longitude.setText(Double.toString(place_single.getGps().getLongitude()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        return true;
    }
}

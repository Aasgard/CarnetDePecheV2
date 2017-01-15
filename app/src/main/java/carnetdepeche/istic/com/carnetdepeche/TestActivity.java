package carnetdepeche.istic.com.carnetdepeche;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import carnetdepeche.istic.com.carnetdepeche.model.Fish;
import carnetdepeche.istic.com.carnetdepeche.model.GPSCoord;

public class TestActivity extends AppCompatActivity {

    FirebaseDatabase fbDb;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        GPSCoord gps1 = new GPSCoord(-1.14614516, 13.556746187);
        GPSCoord gps2 = new GPSCoord(12.14614516, -14.556746187);

        this.fbDb = FirebaseDatabase.getInstance();
        this.dbRef = this.fbDb.getReference("positions");

        dbRef.push().setValue(gps2);
        dbRef.push().setValue(gps1);
    }
}

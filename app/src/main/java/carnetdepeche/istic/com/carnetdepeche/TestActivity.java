package carnetdepeche.istic.com.carnetdepeche;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FirebaseDatabase dbDb = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = dbDb.getReference();

        Toast.makeText(this, FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), Toast.LENGTH_LONG).show();

        FirebaseDatabase.getInstance().getReference("positions").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(TestActivity.this, "Child added : " + dataSnapshot.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(TestActivity.this, "Child changed : " + dataSnapshot.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Toast.makeText(TestActivity.this, "Child removed : " + dataSnapshot.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

    }

}

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

import carnetdepeche.istic.com.carnetdepeche.model.Fish;

public class ViewSingleFish extends AppCompatActivity {

    private String single_fish_id;
    private Fish fish_single;

    private EditText vsf_species;
    private EditText vsf_coin;
    private EditText vsf_taille;
    private EditText vsf_poids;
    private EditText vsf_commentaires;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_fish);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        single_fish_id = i.getStringExtra("id_fish");

        vsf_species = (EditText) findViewById(R.id.view_single_fish_specie);
        vsf_coin = (EditText) findViewById(R.id.view_single_fish_coin);
        vsf_taille = (EditText) findViewById(R.id.view_single_fish_taille);
        vsf_poids = (EditText) findViewById(R.id.view_single_fish_poids);
        vsf_commentaires = (EditText) findViewById(R.id.view_single_fish_commentaires);

        FirebaseDatabase.getInstance().getReference("fish/" + this.single_fish_id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fish_single = dataSnapshot.getValue(Fish.class);
                fish_single.setId(dataSnapshot.getKey());
                setTitle(fish_single.getSpecies());

                vsf_species.setText(fish_single.getSpecies());
                vsf_coin.setText(fish_single.getPlaceName());
                vsf_taille.setText(String.valueOf(fish_single.getSize()) + " cm");
                vsf_poids.setText(Double.toString(fish_single.getWeight()) + " grammes");
                vsf_commentaires.setText(fish_single.getCommentaries());

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

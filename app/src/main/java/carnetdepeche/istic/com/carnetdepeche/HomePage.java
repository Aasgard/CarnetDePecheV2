package carnetdepeche.istic.com.carnetdepeche;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import carnetdepeche.istic.com.carnetdepeche.dao.DAO_Fish;
import carnetdepeche.istic.com.carnetdepeche.model.Fish;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tv_drawer_user_name;
    private TextView tv_drawer_user_email;
    ListView fishListView;
    private ArrayList<Fish> areas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        setTitle("Liste des prises");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddFish.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        View drawerHeaderView = navigationView.getHeaderView(0);

        this.tv_drawer_user_name = (TextView) drawerHeaderView.findViewById(R.id.drawer_display_name);
        this.tv_drawer_user_email = (TextView) drawerHeaderView.findViewById(R.id.drawer_display_email);

        this.tv_drawer_user_name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        this.tv_drawer_user_email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        fishListView = (ListView) findViewById(R.id.listView);

        DAO_Fish dao = new DAO_Fish();
        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        dao.getDatabaseReference().child("fish").orderByChild("fisherMan").equalTo(userUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    Fish fish = areaSnapshot.getValue(Fish.class);
                    fish.setId(areaSnapshot.getKey());
                    areas.add(fish);
                }
                fishListView.setAdapter(new FishAdapter(HomePage.this, areas));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fishListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ViewSingleFish.class);
                i.putExtra("id_fish", areas.get(position).getId());
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } /*else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if  (id == R.id.drawer_add_fish) {
            Intent i = new Intent(getApplicationContext(), AddFish.class);
            startActivity(i);
        } else if (id == R.id.drawer_add_place) {
            Intent i = new Intent(getApplicationContext(), AddPlace.class);
            startActivity(i);
        } else if (id == R.id.drawer_get_fishes) {
            Intent i = new Intent(getApplicationContext(), HomePage.class);
            startActivity(i);
        } else if (id == R.id.drawer_get_places) {
            Intent i = new Intent(getApplicationContext(), ViewPlaces.class);
            startActivity(i);
        } else if (id == R.id.drawer_logoff) {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, Login.class);
            ProgressDialog dialog = new ProgressDialog(this); // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Chargement ...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

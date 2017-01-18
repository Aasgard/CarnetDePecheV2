package carnetdepeche.istic.com.carnetdepeche;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import carnetdepeche.istic.com.carnetdepeche.dao.DAO_Fish;
import carnetdepeche.istic.com.carnetdepeche.model.Fish;
import carnetdepeche.istic.com.carnetdepeche.model.Place;
import carnetdepeche.istic.com.carnetdepeche.utility.Utility;

public class AddFish extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    /**
     * Maps Attributs
     */
    private MapFragment map;
    private GoogleMap gmap;
    private LocationManager locationManager;
    private Location location;
    private MarkerOptions lastMarkerPosition;
    private LatLng lastLatLng;

    /**
     * GUI attributs
     */
    private FloatingActionButton addFishValidate;
    private FloatingActionButton addFishPhoto;
    private Spinner placeName;
    private Spinner species;
    private EditText size;
    private EditText weight;
    private EditText commentaries;
    private View ivImage;

    /**
     * Photos manager attibute
     */
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String userChoosenTask;
    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_fish);
        getSupportActionBar().setTitle("Ajouter une prise");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placeName = (Spinner) findViewById(R.id.add_fish_place_choice);
        species = (Spinner) findViewById(R.id.add_fish_choice_specie);
        size = (EditText) findViewById(R.id.add_fish_size);
        weight = (EditText) findViewById(R.id.add_fish_weight);
        commentaries = (EditText) findViewById(R.id.add_fish_commentaries);

        String userUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Fill spinner with place
        DAO_Fish daoFish = new DAO_Fish();
        daoFish.getDatabaseReference().child("place").orderByChild("creatorId").equalTo(userUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> areas = new ArrayList<String>();
                areas.add("");
                for (DataSnapshot areaSnapshot: dataSnapshot.getChildren()) {
                    Place placeList = areaSnapshot.getValue(Place.class);
                    areas.add(placeList.getNom());
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(AddFish.this, android.R.layout.simple_spinner_item, areas);
                areasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                placeName.setAdapter(areasAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        this.lastMarkerPosition = null;
        this.lastLatLng = null;

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60, 0, this);

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addFishValidate = (FloatingActionButton) findViewById(R.id.add_fish_validate);
        addFishValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Traitement et vérification pour insertion Firebase
                if (lastMarkerPosition != null) {
                    if(size.getText().toString().trim().length()!=0 ){
                        Fish fish = new Fish();

                        DAO_Fish daoFish = new DAO_Fish();

                        fish.setFisherMan(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        fish.setPhotoPath(photoPath);
                        fish.setSpecies(species.getSelectedItem().toString());
                        fish.setSize(Long.valueOf(size.getText().toString()));
                        fish.setWeight(Long.valueOf(weight.getText().toString()));
                        fish.setCommentaries(commentaries.getText().toString());
                        fish.setPlaceName(placeName.getSelectedItem().toString());

                        daoFish.create(fish);

                        Toast.makeText(AddFish.this, "Prise créée", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(AddFish.this, "Veuillez renseigner le champs \"Taille (cm)\"", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(AddFish.this, "Veuillez positionner un marqueur sur la carte", Toast.LENGTH_LONG).show();
                }
            }
        });

        ivImage = (View) findViewById(R.id.add_fish_header_photos);

        addFishPhoto = (FloatingActionButton) findViewById(R.id.add_fish_photo);
        addFishPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage(){
        final CharSequence[] items = { "Prendre une photo", "Importer à partir de la Gallery",
                "Retour" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddFish.this);
        builder.setTitle("Ajouter une photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result= Utility.checkPermission(AddFish.this);

                if (items[item].equals("Prendre une photo")) {
                    userChoosenTask="Prendre une photo";
                    if(result)
                        cameraIntent();

                } else if (items[item].equals("Importer à partir de la Gallery")) {
                    userChoosenTask="Importer à partir de la Gallery";
                    if(result)
                        galleryIntent();

                } else if (items[item].equals("Retour")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        photoPath = getRealPathFromURI(data.getData());

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (android.os.Build.VERSION.SDK_INT >= 16){
            setBackgroundV16Plus(ivImage, thumbnail);
        }
        else{
            setBackgroundV16Minus(ivImage, thumbnail);
        }
    }

    @TargetApi(16)
    private void setBackgroundV16Plus(View view, Bitmap bitmap) {
        view.setBackground(new BitmapDrawable(this.getResources(), bitmap));

    }

    @SuppressWarnings("deprecation")
    private void setBackgroundV16Minus(View view, Bitmap bitmap) {
        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                photoPath = getRealPathFromURI(getImageUri(AddFish.this, bm));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (android.os.Build.VERSION.SDK_INT >= 16){
            setBackgroundV16Plus(ivImage, bm);
        }
        else{
            setBackgroundV16Minus(ivImage, bm);
        }
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
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoosenTask.equals("Prendre une photo"))
                        cameraIntent();
                    else if(userChoosenTask.equals("Importer à partir de la Galerie"))
                        galleryIntent();
                } else {
                    //code for deny
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

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        this.lastLatLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}

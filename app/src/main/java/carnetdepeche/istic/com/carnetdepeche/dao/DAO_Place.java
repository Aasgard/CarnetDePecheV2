package carnetdepeche.istic.com.carnetdepeche.dao;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carnetdepeche.istic.com.carnetdepeche.AddFish;
import carnetdepeche.istic.com.carnetdepeche.R;
import carnetdepeche.istic.com.carnetdepeche.model.Place;

/**
 * Created by marvi on 17/01/2017.
 */

public class DAO_Place {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private List<Place> areas;

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }

    public DAO_Place(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        areas = new ArrayList<>();
    }

    public void create(Place place){
        String key = databaseReference.child("place").push().getKey();
        databaseReference.child("place").child(key).setValue(place);
        Uri file = Uri.fromFile(new File(place.getPhotoPath()));
        storageReference.child("place").child(key).putFile(file);
    }

}

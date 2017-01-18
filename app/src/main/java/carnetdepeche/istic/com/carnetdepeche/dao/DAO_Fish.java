package carnetdepeche.istic.com.carnetdepeche.dao;

import android.app.Activity;
import android.net.Uri;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import carnetdepeche.istic.com.carnetdepeche.model.Fish;
import carnetdepeche.istic.com.carnetdepeche.model.Place;

/**
 * Created by Savak on 18/01/2017.
 */

public class DAO_Fish {
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    public DAO_Fish(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void create(Fish fish){
        String key = databaseReference.child("fish").push().getKey();
        databaseReference.child("fish").child(key).setValue(fish);
        Uri file = Uri.fromFile(new File(fish.getPhotoPath()));
        storageReference.child("fish").child(key).putFile(file);
    }

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }
}

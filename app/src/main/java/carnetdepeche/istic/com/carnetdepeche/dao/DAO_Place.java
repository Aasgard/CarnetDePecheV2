package carnetdepeche.istic.com.carnetdepeche.dao;

import android.net.Uri;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import carnetdepeche.istic.com.carnetdepeche.model.Place;

/**
 * Created by marvi on 17/01/2017.
 */

public class DAO_Place {

    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    public DAO_Place(){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void create(Place place){
        String key = databaseReference.child("place").push().getKey();
        databaseReference.child("place").child(key).setValue(place);
        Uri file = Uri.fromFile(new File(place.getPhotoPath()));
        storageReference.child("place").child(key).putFile(file);
    }
}

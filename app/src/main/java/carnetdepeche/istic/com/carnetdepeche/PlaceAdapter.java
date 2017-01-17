package carnetdepeche.istic.com.carnetdepeche;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import carnetdepeche.istic.com.carnetdepeche.model.Fish;
import carnetdepeche.istic.com.carnetdepeche.model.Place;

/**
 * Created by Chouan on 17/01/2017.
 */

public class PlaceAdapter extends BaseAdapter {


    List<Place> result;
    Context context;

    private static LayoutInflater inflater=null;

    public PlaceAdapter(ViewPlaces placeListActivity, List<Place> places){
        context = placeListActivity;
        result = places;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        PlaceAdapter.PlaceViewHolder holder=new PlaceAdapter.PlaceViewHolder();
        View rowView;
        rowView = inflater.inflate(R.layout.content_fish_list, null);

        holder.name_place=(TextView) rowView.findViewById(R.id.name_place);
        //holder.avatar=(ImageView) rowView.findViewById(R.id.avatar);
        holder.gps_long=(TextView) rowView.findViewById(R.id.gps_long);
        holder.gps_lat=(TextView) rowView.findViewById(R.id.gps_lat);
        holder.commentary=(TextView) rowView.findViewById(R.id.commentary);


        Place place = result.get(position);

        holder.name_place.setText(place.getNom());
        //holder.avatar.setImageResource(fish.getPhotos());
        holder.gps_long.setText(place.getGps().getLongitude()+"");
        holder.gps_lat.setText(place.getGps().getLatitude()+"");
        holder.commentary.setText(place.getCommentary());

        return rowView;
    }

    private class PlaceViewHolder{
        public ImageView avatar;
        public TextView name_place;
        public TextView gps_long;
        public TextView gps_lat;
        public TextView commentary;
    }
}
package carnetdepeche.istic.com.carnetdepeche;

/**
 * Created by Chouan on 17/01/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import carnetdepeche.istic.com.carnetdepeche.model.Fish;

public class FishAdapter extends BaseAdapter {


    List<Fish> result;
    Context context;

    private static LayoutInflater inflater=null;

    public FishAdapter(HomePage fishListActivity, List<Fish> fishs){
        context = fishListActivity;
        result = fishs;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        FishViewHolder holder=new FishViewHolder();
        View rowView;
        rowView = inflater.inflate(R.layout.content_fish_list, null);

        holder.species=(TextView) rowView.findViewById(R.id.species);
        holder.avatar=(ImageView) rowView.findViewById(R.id.photo_fish);
        holder.size_fish=(TextView) rowView.findViewById(R.id.size_fish);
        holder.weight_fish=(TextView) rowView.findViewById(R.id.weight_fish);


        Fish fish = result.get(position);

        holder.species.setText(fish.getSpecies());
        holder.avatar.setImageResource(R.drawable.ic_menu_fish);
        holder.size_fish.setText(String.valueOf(fish.getSize()) + " cm");
        holder.weight_fish.setText(fish.getWeight() + " g");
        //holder.commentary.setText(fish.getCommentaries());

        return rowView;
    }

    private class FishViewHolder{
        public ImageView avatar;
        public TextView species;
        public TextView size_fish;
        public TextView weight_fish;
        public TextView commentary;
    }
}

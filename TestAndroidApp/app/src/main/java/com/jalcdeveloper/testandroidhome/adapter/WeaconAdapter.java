package com.jalcdeveloper.testandroidhome.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalcdeveloper.testandroidhome.R;
import com.jalcdeveloper.testandroidhome.weacon.WeaconNode;

import java.util.ArrayList;
import java.util.List;

public class WeaconAdapter extends RecyclerView.Adapter<WeaconAdapter.WeaconAdapterViewHolder> {

    private static final String TAG = WeaconAdapter.class.getSimpleName();
    private Context context;
    private List<WeaconNode> weaconList;

    public WeaconAdapter(Context context){
        this.context = context;
        weaconList = new ArrayList<>();
    }

    public void setWeaconList(List<WeaconNode> weaconList){
        this.weaconList = weaconList;
    }

    @Override
    public WeaconAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewGroup instanceof RecyclerView){
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_sensors, viewGroup, false);
            view.setFocusable(true);
            return new WeaconAdapterViewHolder(view);
        }else{
            throw new RuntimeException("Not bound to RecycleViewSelection");
        }
    }

    @Override
    public void onBindViewHolder(WeaconAdapterViewHolder weaconAdapterViewHolder, int position) {

        WeaconNode weacon = weaconList.get(position);

        weaconAdapterViewHolder.mTemp.setText(weacon.getChannel());
        weaconAdapterViewHolder.mHumidity.setText(weacon.getMessage());
    }

    @Override
    public int getItemCount() {
        return weaconList.size();
    }

    public class WeaconAdapterViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mIconView;
        public final TextView mTemp;
        public final TextView mHumidity;

        public WeaconAdapterViewHolder(View view) {
            super(view);
            mIconView = (ImageView) view.findViewById(R.id.list_item_icon);
            mTemp = (TextView) view.findViewById(R.id.list_item_temperature_textview);
            mHumidity = (TextView) view.findViewById(R.id.list_item_humidity_textview);
        }
    }


}

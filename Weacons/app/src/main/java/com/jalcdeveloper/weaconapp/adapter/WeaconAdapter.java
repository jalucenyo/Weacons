package com.jalcdeveloper.weaconapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;

import java.util.ArrayList;
import java.util.List;

public class WeaconAdapter extends RecyclerView.Adapter<WeaconAdapter.ViewHolder> {

    private static final String TAG = WeaconAdapter.class.getSimpleName();
    private Context context;
    private List<WeaconNode> weaconList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mIconView;
        public final TextView mText;
        public final Button mIncrementButton;
        public final Button mDecrementButton;
        public final Button mSettingsButton;

        public ViewHolder(View view) {
            super(view);

            mIconView = (ImageView) view.findViewById(R.id.list_item_icon);
            mText = (TextView) view.findViewById(R.id.list_item_textview);
            mIncrementButton = (Button) view.findViewById(R.id.list_item_button_increment);
            mDecrementButton = (Button) view.findViewById(R.id.list_item_button_decrement);
            mSettingsButton = (Button) view.findViewById(R.id.list_item_button_settings);

        }

        public ImageView getImageView(){
            return mIconView;
        }

        public TextView getTextView(){
            return mText;
        }

        public Button getIncrementButton(){
            return mIncrementButton;
        }

        public Button getDecrementButton(){
            return mDecrementButton;
        }

        public Button getSettingsButton(){
            return mSettingsButton;
        }

    }

    public WeaconAdapter(Context context){
        this.context = context;
        weaconList = new ArrayList<>();
    }

    public void setWeaconList(List<WeaconNode> weaconList){
        this.weaconList = weaconList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        if(viewGroup instanceof RecyclerView){

            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_weacon, viewGroup, false);
            view.setFocusable(true);
            return new ViewHolder(view);

        }else{
            throw new RuntimeException("Not bound to RecycleViewSelection");
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        final WeaconNode weacon = weaconList.get(position);

        if(weacon.getType() != null && weacon.getAttributes() != null){
            if(weacon.getType().equals(WeaconHelper.TYPE_AMBIENT)){

                viewHolder.getDecrementButton().setVisibility(View.INVISIBLE);
                viewHolder.getIncrementButton().setVisibility(View.INVISIBLE);
                viewHolder.getTextView().setText(
                        "" + weacon.getDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE)
                                + " ºC  /  " +
                                weacon.getDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_HUMIDITY)
                                + " % ");

            }else if(weacon.getType().equals(WeaconHelper.TYPE_CONTROL)){

                viewHolder.getDecrementButton().setVisibility(View.VISIBLE);
                viewHolder.getIncrementButton().setVisibility(View.VISIBLE);
                viewHolder.getTextView().setText(weacon.getChannel());

                viewHolder.getTextView().setText("Value: " +
                    weacon.getDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS));

                viewHolder.getDecrementButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        double value = weacon.getDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS);
                        weacon.setDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS, value - 1);
                        WeaconManager.publishWeacon(weacon);

                    }
                });

                viewHolder.getIncrementButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        double value = weacon.getDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS);
                        weacon.setDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS, value + 1);
                        WeaconManager.publishWeacon(weacon);

                    }
                });

            }
        }

    }

    @Override
    public int getItemCount() {
        return weaconList.size();
    }


}

package com.jalcdeveloper.weaconapp.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.jalcdeveloper.weaconapp.database.Sensor;

public class WeaconPresenter extends Presenter {

    private static final String TAG = WeaconPresenter.class.getSimpleName();
    private static int CARD_WIDTH = 200;
    private static int CARD_HEIGHT = 200;
    private static Context mContext;

//    static class ViewHolder extends Presenter.ViewHolder{
//
//        private ImageCardView mCardView;
//
//        public ViewHolder(View view) {
//            super(view);
//            mCardView = (ImageCardView) view;
//
//        }
//
//        public ImageCardView getCardView(){
//            return mCardView;
//        }
//
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Log.d(TAG, "onCreateViewHolder");

        mContext = viewGroup.getContext();
        ImageCardView cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {

        ImageCardView cardView = (ImageCardView) viewHolder.view;
        //TODO: CMMATA - Cargar datos de la base de datos.
        Sensor sensor = (Sensor) o;
        // http://developer.android.com/intl/es/training/basics/data-storage/databases.html
        cardView.setTitleText(sensor.get_nombre());
        cardView.setContentText(sensor.get_descripcion());
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);

        //TODO: CMMATA - Image load from Picasso
        //http://androidtv-codelabs.appspot.com/static/codelabs/1-androidtv-adding-leanback/#3 -> Create Picasso Target
        //cardView.setMainImage();

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

}

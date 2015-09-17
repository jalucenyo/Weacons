package com.jalcdeveloper.weaconapp.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Weacon;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.jalcdeveloper.weaconapp.weacon.WeaconManager;
import com.jalcdeveloper.weaconapp.weacon.WeaconNode;
import com.jalcdeveloper.weaconapp.weacon.WeaconNodeListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class WeaconPresenter extends Presenter implements WeaconNodeListener {

    private static final String TAG = WeaconPresenter.class.getSimpleName();
    private static int CARD_WIDTH = 200;
    private static int CARD_HEIGHT = 200;
    private static Context mContext;
    //private ImageCardView cardView;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Log.d(TAG, "onCreateViewHolder");

        mContext = viewGroup.getContext();
        ImageCardView cardView = new ImageCardView(mContext);
        //cardView = new ImageCardView(mContext);
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        ((TextView)cardView.findViewById(R.id.content_text)).setTextColor(Color.LTGRAY);

        return new ViewHolder(cardView);
    }

    @Override
    public void onUpdate(final WeaconNode weacon) {
        Log.d(TAG, "onUpdate WeaconNode: " + weacon.getChannel());
        ((Activity)mContext).runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object o) {
        Log.d(TAG, "onBindViewHolder");
        Weacon sensor = (Weacon) o;
        Log.d(TAG, "onBindViewHolder : Weacon " + sensor.get_canal());

        WeaconNode weacon = WeaconManager.getWeaconNode(sensor.get_canal());
        if (weacon != null && weacon.getType().equals(WeaconHelper.TYPE_AMBIENT)) {
            ((ViewHolder) viewHolder).mCardView
                    .setContentText(mContext.getResources().getText(R.string.temp_actual) +
                            String.valueOf(weacon.getDoubleAttribute(WeaconHelper.ATTR_AMBIENT_SENSOR_TEMPERATURE)) + "C");
            Log.d(TAG, "Weacon suscribe : " + weacon.getChannel());
            //WeaconManager.suscribeWeacon(weacon, this);
        }

        if (weacon != null && weacon.getType().equals(WeaconHelper.TYPE_CONTROL)) {
            ((ViewHolder) viewHolder).mCardView
                    .setContentText(mContext.getResources().getText(R.string.lum_actual) +
                            String.valueOf(weacon.getDoubleAttribute(WeaconHelper.ATTR_STRIP_VAlUE_BRIGHTNESS)));
            Log.d(TAG, "Weacon suscribe : " + weacon.getChannel());
            //WeaconManager.suscribeWeacon(weacon, this);
        }


        //Ejemplo en http://androidtv-codelabs.appspot.com/static/codelabs/1-androidtv-adding-leanback/#3 -> Create Picasso Target
        ((ViewHolder) viewHolder).mCardView.setTitleText(sensor.get_nombre());
        ((ViewHolder) viewHolder).mCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        int image = WeaconHelper.getImage(sensor);
        Log.d(TAG, "Imagen " + image);
        ((ViewHolder) viewHolder).updateCardViewImage(image);

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

    //He tenido que ponerlo static porque me daba error el Studio (linea 92)
    public static class PicassoImageCardViewTarget implements Target {
        private ImageCardView mImageCardView;

        public PicassoImageCardViewTarget(ImageCardView mImageCardView) {
            this.mImageCardView = mImageCardView;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            Drawable bitmapDrawable = new BitmapDrawable(mContext.getResources(), bitmap);
            mImageCardView.setMainImage(bitmapDrawable);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            mImageCardView.setMainImage(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    }

    static class ViewHolder extends Presenter.ViewHolder {
        private ImageCardView mCardView;
        private Drawable mDefaultCardImage;
        private PicassoImageCardViewTarget mImageCardViewTarget;

        public ViewHolder(View view) {
            super(view);
            mCardView = (ImageCardView) view;
            mImageCardViewTarget = new PicassoImageCardViewTarget(mCardView);
            mDefaultCardImage = mContext
                    .getResources()
                    .getDrawable(R.drawable.light_off);
        }

        public ImageCardView getCardView() {
            return mCardView;
        }

        protected void updateCardViewImage(int url) {
           Picasso.with(mContext)
                    .load(url)
                    .resize(CARD_WIDTH * 2, CARD_HEIGHT * 2)
                    .centerCrop()
                    .error(mDefaultCardImage)
                    .into(mImageCardViewTarget);
        }
    }

}

package com.jalcdeveloper.weaconapp.presenter;

import android.content.Context;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

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
        cardView.setTitleText("Hola Mundo");
        cardView.setContentText("Esto es una prueba");
        cardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);

        //TODO: Image load from Picasso
        //cardView.setMainImage();

    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }

}

package com.jalcdeveloper.weaconapp.ui.tv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v17.leanback.app.DetailsFragment;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.DetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Sensor;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeaconDetailsFragment extends DetailsFragment {


    private Sensor selectedWeacon;
    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;
    private static final int ACTION_ONE = 1;
    private static final int ACTION_TWO = 2;

    public WeaconDetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weacon_details, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedWeacon = (Sensor) getActivity()
                .getIntent()
                .getSerializableExtra(Sensor.INTENT_EXTRA_WEACON);
    }

    private class DetailRowBuilderTask extends AsyncTask<Sensor, Integer, DetailsOverviewRow> {
        @Override
        protected DetailsOverviewRow doInBackground(Sensor... sensors) {
            DetailsOverviewRow row = new DetailsOverviewRow(sensors[0]);
            Bitmap poster = null;
            String imageUri;
            try {
                // the Picasso library helps us dealing with images
                if (sensors[0].get_tipo().equals(WeaconHelper.TYPE_CONTROL)) {
                    imageUri = "drawable://" + R.drawable.temp;
                } else if (sensors[0].get_tipo().equals(WeaconHelper.TYPE_AMBIENT)) {
                    imageUri = "drawable://" + R.drawable.light_on;
                } else {
                    imageUri = "drawable://" + R.drawable.sensor;
                }
                poster = Picasso.with(getActivity())
                        .load(sensors[0].get_tipo())
                        .resize(dpToPx(DETAIL_THUMB_WIDTH, getActivity().getApplicationContext()),
                                dpToPx(DETAIL_THUMB_HEIGHT, getActivity().getApplicationContext()))
                        .centerCrop()
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            row.setImageBitmap(getActivity(), poster);
            row.addAction(new Action(ACTION_ONE, getResources().getString(R.string.action_play)));
            row.addAction(new Action(ACTION_TWO, getResources().getString(R.string.action_watch_later)));
            return row;
        }

        @Override
        protected void onPostExecute(DetailsOverviewRow detailRow) {
            ClassPresenterSelector ps = new ClassPresenterSelector();
            DetailsOverviewRowPresenter dorPresenter = new DetailsOverviewRowPresenter(
                    new DetailsDescriptionPresenter());
            dorPresenter.setBackgroundColor(getResources().getColor(R.color.primary));
            dorPresenter.setStyleLarge(true);
            dorPresenter.setOnActionClickedListener(new OnActionClickedListener() {
                @Override
                public void onActionClicked(Action action) {
                    if (action.getId() == ACTION_ONE) {
                        Intent intent = new Intent(getActivity(), MainTvActivity.class);
                        intent.putExtra(Sensor.INTENT_EXTRA_WEACON, (Serializable) selectedWeacon);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), action.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ps.addClassPresenter(DetailsOverviewRow.class, dorPresenter);
            ArrayObjectAdapter adapter = new ArrayObjectAdapter(ps);
            adapter.add(detailRow);
            setAdapter(adapter);
        }
    }


    public static int dpToPx(int dp, Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}

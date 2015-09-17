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
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.widget.Toast;

import com.jalcdeveloper.weaconapp.R;
import com.jalcdeveloper.weaconapp.database.Weacon;
import com.jalcdeveloper.weaconapp.weacon.WeaconHelper;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeaconDetailsFragment extends DetailsFragment {


    private Weacon selectedWeacon;
    private static final int DETAIL_THUMB_WIDTH = 274;
    private static final int DETAIL_THUMB_HEIGHT = 274;
    private static final int ACTION_ONE = 1;
    private static final int ACTION_TWO = 2;

    private DetailRowBuilderTask mRowBuilderTask;

    public WeaconDetailsFragment() {
    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weacon_details, container, false);
    }
*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedWeacon = (Weacon) getActivity()
                .getIntent()
                .getSerializableExtra(Weacon.INTENT_EXTRA_WEACON);
        (mRowBuilderTask = new DetailRowBuilderTask()).execute(selectedWeacon);
    }

    @Override
    public void onStop() {
        mRowBuilderTask.cancel(true);
        super.onStop();
    }

    private class DetailRowBuilderTask extends AsyncTask<Weacon, Integer, DetailsOverviewRow> {
        @Override
        protected DetailsOverviewRow doInBackground(Weacon... weacons) {
            DetailsOverviewRow row = new DetailsOverviewRow(weacons[0]);
            try {
                // the Picasso library helps us dealing with images
                Bitmap poster = Picasso.with(getActivity())
                        .load(WeaconHelper.getImage(weacons[0]))
                        .resize(dpToPx(DETAIL_THUMB_WIDTH, getActivity().getApplicationContext()),
                                dpToPx(DETAIL_THUMB_HEIGHT, getActivity().getApplicationContext()))
                        .centerCrop()
                        .get();
                row.setImageBitmap(getActivity(), poster);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (weacons[0].get_tipo().equals(WeaconHelper.TYPE_CONTROL)) {
                row.addAction(new Action(ACTION_ONE, getResources().getString(R.string.menos_luz)));
                row.addAction(new Action(ACTION_TWO, getResources().getString(R.string.mas_luz)));
            }
            return row;
        }

        @Override
        protected void onPostExecute(DetailsOverviewRow detailRow) {
            ClassPresenterSelector ps = new ClassPresenterSelector();
            DetailsOverviewRowPresenter dorPresenter = new DetailsOverviewRowPresenter(
                    new DetailsDescriptionPresenter(getActivity()));
            dorPresenter.setBackgroundColor(getResources().getColor(R.color.primary));
            dorPresenter.setStyleLarge(true);
            dorPresenter.setOnActionClickedListener(new OnActionClickedListener() {
                @Override
                public void onActionClicked(Action action) {
                    if (action.getId() == ACTION_ONE) {
                        /*Intent intent = new Intent(getActivity(), MainTvActivity.class);
                        intent.putExtra(Weacon.INTENT_EXTRA_WEACON, (Serializable) selectedWeacon);
                        startActivity(intent);*/
                        Toast.makeText(getActivity(), action.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), action.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            ps.addClassPresenter(DetailsOverviewRow.class, dorPresenter);
            ps.addClassPresenter(ListRow.class, new ListRowPresenter());
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

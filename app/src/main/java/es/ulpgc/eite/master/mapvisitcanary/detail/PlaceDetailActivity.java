package es.ulpgc.eite.master.mapvisitcanary.detail;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import es.ulpgc.eite.master.mapvisitcanary.R;
import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseAnnotatedActivity;
import es.ulpgc.mvp.arch.Viewable;


@Viewable(presenter = PlaceDetailPresenter.class, layout = R.layout.activity_place_detail)
public class PlaceDetailActivity
    extends BaseAnnotatedActivity<PlaceDetailContract.View, PlaceDetailContract.Presenter>
    //extends BaseActivity<PlaceDetailContract.View, PlaceDetailContract.Presenter>
    implements PlaceDetailContract.View {


    @Override
    protected PlaceDetailContract.Presenter initPresenter() {
        return new PlaceDetailPresenter();
    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
    }
    */

    public Context getManagedContext(){
        return getBaseContext();
    }

    public void setupUI(PlaceStore.Place place){
        if (place != null) {
            setupToolbar(place.title);

            TextView placeDetail = findViewById(R.id.place_detail);
            placeDetail.setText(place.description);
            ImageView placePicture = findViewById(R.id.place_picture);

            int resId= getResources().getIdentifier(
                place.picture, "drawable", getPackageName());
            placePicture.setImageResource(resId);
        }
    }


    private void setupToolbar(String title) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(title);
        }
    }

}

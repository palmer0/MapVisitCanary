package es.ulpgc.eite.master.mapvisitcanary;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class PlaceDetailActivity extends AppCompatActivity {

    public static final String PARAM_PLACE_ID = "place_to_visit_id";

    private PlaceStore placeStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillPlaceStore();
        setupUI();

    }

    private void setupUI(){
        setContentView(R.layout.activity_place_detail);

        String placeId = getIntent().getStringExtra(PARAM_PLACE_ID);
        PlaceStore.Place place = placeStore.getPlaceById(placeId);

        if (place != null) {
            setupToolbar(place.title);

            TextView placeDetail = (TextView) findViewById(R.id.place_detail);
            placeDetail.setText(place.details);
            ImageView placePicture = (ImageView) findViewById(R.id.place_picture);

            int resId= getResources().getIdentifier(place.picture, "drawable", getPackageName());
            placePicture.setImageResource(resId);
        }
    }

    private void fillPlaceStore(){
        Resources res = getResources();
        List<String> titles = Arrays.asList(res.getStringArray(R.array.places_titles));
        List<String> details = Arrays.asList(res.getStringArray(R.array.places_details));
        List<String> pictures = Arrays.asList(res.getStringArray(R.array.places_pictures));
        List<String> locations = Arrays.asList(res.getStringArray(R.array.places_locations));

        placeStore = new PlaceStore(titles, details, pictures, locations);
    }

    private void setupToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(title);
        }
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //navigateUpTo(new Intent(this, PlaceListActivity.class));
            //return true;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    */
}

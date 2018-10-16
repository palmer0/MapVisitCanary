package es.ulpgc.eite.master.mapvisitcanary.map;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import es.ulpgc.eite.master.mapvisitcanary.R;
import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.eite.master.mapvisitcanary.detail.PlaceDetailActivity;
import es.ulpgc.eite.master.mapvisitcanary.list.PlaceListActivity;
import es.ulpgc.mvp.arch.BaseAnnotatedActivity;
import es.ulpgc.mvp.arch.Viewable;

@Viewable(presenter = PlaceMapPresenter.class, layout = R.layout.activity_place_map)
public class PlaceMapActivity
    extends BaseAnnotatedActivity<PlaceMapContract.View, PlaceMapContract.Presenter>
    //extends BaseActivity<PlaceMapContract.View, PlaceMapContract.Presenter>
    implements PlaceMapContract.View, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap googleMap;

    @Override
    protected PlaceMapContract.Presenter initPresenter() {
        return new PlaceMapPresenter();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_map);
    }
    */

    public Context getManagedContext(){
        return getBaseContext();
    }


    public void setupUI() {
        setupToolbar();

        // Obtain the Map Fragment and get notified when the map is ready to be used.
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(getString(R.string.title_place_map));
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //googleMap.setOnMarkerClickListener(MainMapActivity.this);
        googleMap.setOnInfoWindowClickListener(PlaceMapActivity.this);

        this.googleMap = googleMap;
        presenter.mapReady();

    }

    public void updateUI(List<PlaceStore.Place> places){

        // Add all markers and move the camera
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (PlaceStore.Place place: places) {

            try {
                String[] locations = place.location.split(",");
                double latitude = Double.parseDouble(locations[0]);
                double longitude = Double.parseDouble(locations[1]);
                LatLng location = new LatLng(latitude, longitude);
                MarkerOptions marker = new MarkerOptions().position(location)
                    .title(place.title)
                    .snippet(place.id);
                googleMap.addMarker(marker);
                builder.include(marker.getPosition());

            } catch (Exception error){
                //Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }

        if(!places.isEmpty()){

            LatLngBounds bounds = builder.build();
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.12); // offset from edges of the map 12% of screen

            CameraUpdate camera =
                CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
            googleMap.moveCamera(camera);
            //googleMap.animateCamera(camera);
        }
    }


    public void openDetailActivity() {
        openActivity(PlaceDetailActivity.class);
    }

    public void openListActivity() {
        openActivity(PlaceListActivity.class);
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        String placeId = marker.getSnippet();
        presenter.placeClicked(placeId);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_list_button) {
            presenter.menuClicked();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

}

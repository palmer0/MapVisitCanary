package es.ulpgc.eite.master.mapvisitcanary.list;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.ulpgc.eite.master.mapvisitcanary.R;
import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.eite.master.mapvisitcanary.detail.PlaceDetailActivity;
import es.ulpgc.eite.master.mapvisitcanary.map.PlaceMapActivity;
import es.ulpgc.mvp.arch.BaseAnnotatedActivity;
import es.ulpgc.mvp.arch.Viewable;


@Viewable(presenter = PlaceListPresenter.class, layout = R.layout.activity_place_list)
public class PlaceListActivity
    extends BaseAnnotatedActivity<PlaceListContract.View, PlaceListContract.Presenter>
    //extends BaseActivity<PlaceListContract.View, PlaceListContract.Presenter>
    implements PlaceListContract.View {


    //private PlaceStore placeStore;

    @Override
    protected PlaceListContract.Presenter initPresenter() {
        return new PlaceListPresenter();
    }

    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);

        //fillPlaceStoreFromResources();
        fillPlaceStoreFromAssets();
        setupUI();

    }
    */

    public Context getManagedContext(){
        return getBaseContext();
    }


    public void setupUI(List<PlaceStore.Place> places) {
        setupToolbar();
        setupAdapter(places);
    }


    /*
    private void setupUI(){

        setupToolbar();
        setupAdapter();
    }
    */

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(getString(R.string.title_place_list));
        }
    }

    /*
    private void setupAdapter() {

        RecyclerView recyclerView = findViewById(R.id.place_list);
        recyclerView.setAdapter(new PlaceListAdapter(placeStore.getPlaces()));
    }
    */

    private void setupAdapter(List<PlaceStore.Place> places) {

        RecyclerView recyclerView = findViewById(R.id.place_list);
        recyclerView.setAdapter(new PlaceListAdapter(places));
    }

    public void openDetailActivity() {
        openActivity(PlaceDetailActivity.class);
    }


    public void openMapActivity() {
        openActivity(PlaceMapActivity.class);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_map_button) {
            //goToPlaceList();
            presenter.menuClicked();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    /*
    private void openDetailActivity(String placeId ) {
        Intent intent =
            new Intent(PlaceListActivity.this, PlaceDetailActivity.class);
        intent.putExtra(PlaceDetailActivity.PARAM_PLACE_ID, placeId);
        startActivity(intent);
    }
    */

    /*
    private JSONArray loadJSONFromAssets(String filename) {
        try {

            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONArray array = new JSONArray(json);
            return array;

        } catch (Exception ex) {

        }

        //return new JSONArray();
        return null;
    }

    private void fillPlaceStoreFromAssets(){
        List<String> titles = new ArrayList();
        List<String> descriptions = new ArrayList();
        List<String> pictures = new ArrayList();
        List<String> locations = new ArrayList();

        JSONArray array = loadJSONFromAssets("places.json");
        for(int index=0; index < array.length(); index++){
            try {

                JSONObject obj = array.getJSONObject(index);
                String title = obj.getString(PlaceStore.Place.KEY_TITLE);
                String description = obj.getString(PlaceStore.Place.KEY_DESC);
                String picture = obj.getString(PlaceStore.Place.KEY_PIC);
                String location = obj.getString(PlaceStore.Place.KEY_LOC);

                locations.add(location);
                titles.add(title);
                pictures.add(picture);
                descriptions.add(description);

            } catch (JSONException e) {

            }
        }

        placeStore = new PlaceStore(titles, descriptions, pictures, locations);

        //Log.d("JSONArray", placeStore.toJSONArray().toString());
    }

    private void fillPlaceStoreFromResources(){
        Resources res = getResources();
        List<String> titles =
                Arrays.asList(res.getStringArray(R.array.places_titles));
        List<String> descriptions =
                Arrays.asList(res.getStringArray(R.array.places_descriptions));
        List<String> pictures =
                Arrays.asList(res.getStringArray(R.array.places_pictures));
        List<String> locations =
                Arrays.asList(res.getStringArray(R.array.places_locations));

        placeStore = new PlaceStore(titles, descriptions, pictures, locations);

        //Log.d("JSONArray", placeStore.toJSONArray().toString());
    }
    */

    class PlaceListAdapter
            extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {

        private List<PlaceStore.Place> places;

        public PlaceListAdapter(List<PlaceStore.Place> places) {
            this.places = places;
        }

        @Override
        public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.place_list_content, parent, false);
            return new PlaceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final PlaceViewHolder holder, int position) {
            holder.placeItem = places.get(position);
            holder.placeTitleView.setText(places.get(position).title);

            //holder.placeView.setOnClickListener(view -> openDetailActivity(holder.placeItem.id));
            holder.placeView.setOnClickListener(view ->
                presenter.placeClicked(holder.placeItem.id)
            );
        }

        @Override
        public int getItemCount() {
            return places.size();
        }

        class PlaceViewHolder extends RecyclerView.ViewHolder {
            public final View placeView;
            public final TextView placeTitleView;
            public PlaceStore.Place placeItem;

            public PlaceViewHolder(View view) {
                super(view);
                placeView = view;
                placeTitleView = view.findViewById(R.id.place_title);
            }

            @Override
            public String toString() {
                return placeTitleView.getText().toString();
            }
        }
    }




}

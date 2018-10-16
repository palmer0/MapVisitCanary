package es.ulpgc.eite.master.mapvisitcanary;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class PlaceListActivity extends AppCompatActivity {

    private PlaceStore placeStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fillPlaceStore();
        setupUI();

    }

    private void setupUI(){
        setContentView(R.layout.activity_place_list);

        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setTitle(getTitle());

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(getString(R.string.title_place_list));
        }

    }

    private void setupRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.place_list);
        recyclerView.setAdapter(new PlaceRecyclerViewAdapter(placeStore.getPlaces()));
    }


    private void fillPlaceStore(){
        Resources res = getResources();
        List<String> titles =
                Arrays.asList(res.getStringArray(R.array.places_titles));
        List<String> details =
                Arrays.asList(res.getStringArray(R.array.places_details));
        List<String> pictures =
                Arrays.asList(res.getStringArray(R.array.places_pictures));
        List<String> locations =
                Arrays.asList(res.getStringArray(R.array.places_locations));

        placeStore = new PlaceStore(titles, details, pictures, locations);
    }

    class PlaceRecyclerViewAdapter
            extends RecyclerView.Adapter<PlaceRecyclerViewAdapter.PlaceViewHolder> {

        private List<PlaceStore.Place> places;

        public PlaceRecyclerViewAdapter(List<PlaceStore.Place> places) {
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

            holder.placeView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    goToPlaceDetails(holder.placeItem.id);

                    /*
                    Context context = view.getContext();
                    Intent intent = new Intent(context, PlaceDetailActivity.class);
                    intent.putExtra(PlaceDetailActivity.PARAM_PLACE_ID, holder.placeItem.id);
                    context.startActivity(intent);
                    */
                }
            });
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

    private void goToPlaceDetails(String placeId ) {
        Intent intent = new Intent(
                PlaceListActivity.this, PlaceDetailActivity.class);
        intent.putExtra(PlaceDetailActivity.PARAM_PLACE_ID, placeId);
        startActivity(intent);
    }

    private void goToPlaceMap( ) {
        Intent intent = new Intent(
                PlaceListActivity.this, PlaceMapActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_map_button) {
            goToPlaceMap();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

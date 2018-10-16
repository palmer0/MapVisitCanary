package es.ulpgc.eite.master.mapvisitcanary.detail;

import android.content.Context;
import android.util.Log;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceRepository;
import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseModel;

public class PlaceDetailModel
    extends BaseModel<PlaceDetailContract.Presenter> implements PlaceDetailContract.Model {


  //private PlaceStore store;
  //private PlaceRepository repository;

  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.List.Model", "onPresenterCreated");
  }

  /*
  public void initStore(Context managedContext){
    Log.d("VisitCanary.List.Model", "initStore");

    store = new PlaceStore(managedContext);
  }
  */

  /*
  public void init(Context managedContext){
    repository = PlaceRepository.getInstance(managedContext);
  }

  public PlaceStore.Place getPlace(String placeId) {
    //return store.getPlaceById(placeId);
    return repository.getPlace(placeId);
  }
  */

  @Override
  public PlaceStore.Place getPlace(Context managedContext, String placeId) {
    return PlaceRepository.getInstance(managedContext).getPlace(placeId);
  }


  /*
  public void fillPlaceStoreFromAssets(Context managedContext){
    List<String> titles = new ArrayList();
    List<String> descriptions = new ArrayList();
    List<String> pictures = new ArrayList();
    List<String> locations = new ArrayList();

    JSONArray array = loadJSONFromAssets(managedContext, "places.json");
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

    store = new PlaceStore(titles, descriptions, pictures, locations);

    Log.d("VisitCanary.List.Model", "fillPlaceStoreFromAssets: " + store.toJSONArray());
  }

  public void fillPlaceStoreFromResources(Context managedContext){
    Resources res = managedContext.getResources();
    List<String> titles =
        Arrays.asList(res.getStringArray(R.array.places_titles));
    List<String> descriptions =
        Arrays.asList(res.getStringArray(R.array.places_descriptions));
    List<String> pictures =
        Arrays.asList(res.getStringArray(R.array.places_pictures));
    List<String> locations =
        Arrays.asList(res.getStringArray(R.array.places_locations));

    store = new PlaceStore(titles, descriptions, pictures, locations);

    Log.d("VisitCanary.List.Model", "fillPlaceStoreFromResources: " + store.toJSONArray());
  }

  private JSONArray loadJSONFromAssets(Context managedContext, String filename) {
    try {

      InputStream is = managedContext.getAssets().open(filename);
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
  */

}

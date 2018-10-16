package es.ulpgc.eite.master.mapvisitcanary.map;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceMapContract {

  interface Presenter extends BaseContract.Presenter<View> {

    void placeClicked(String placeId);
    void menuClicked();
    void mapReady();
  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void updateUI(List<PlaceStore.Place> places);
    void setupUI();
    void openDetailActivity();
    void openListActivity();
  }

  interface Model extends BaseContract.Model {

    //void init(Context managedContext);
    //void initStore(Context managedContext);
    //List<PlaceStore.Place> getPlaces();
    List<PlaceStore.Place> getPlaces(Context managedContext);
    //void fillPlaceStoreFromAssets(Context managedContext);
    //void fillPlaceStoreFromResources(Context managedContext);
  }

}

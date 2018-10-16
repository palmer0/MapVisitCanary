package es.ulpgc.eite.master.mapvisitcanary.list;

import android.content.Context;

import java.util.List;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceListContract {

  interface Presenter extends BaseContract.Presenter<View> {

    void placeClicked(String placeId);
    void menuClicked();
  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void setupUI(List<PlaceStore.Place> places);
    void openDetailActivity();
    void openMapActivity();
  }

  interface Model extends BaseContract.Model {

    void initRepository(Context managedContext);
    List<PlaceStore.Place> getPlaces();
    //List<PlaceStore.Place> getPlaces(Context managedContext);
  }

}

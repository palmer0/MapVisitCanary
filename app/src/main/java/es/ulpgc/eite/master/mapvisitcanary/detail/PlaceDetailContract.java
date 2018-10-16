package es.ulpgc.eite.master.mapvisitcanary.detail;

import android.content.Context;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseContract;


interface PlaceDetailContract {

  interface Presenter extends BaseContract.Presenter<View> {

  }

  interface View extends BaseContract.View {

    Context getManagedContext();
    void setupUI(PlaceStore.Place place);
  }

  interface Model extends BaseContract.Model {

    void initRepository(Context managedContext);
    PlaceStore.Place getPlace(String placeId);
    //PlaceStore.Place getPlace(Context managedContext, String placeId);
  }

}

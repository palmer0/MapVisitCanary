package es.ulpgc.eite.master.mapvisitcanary.detail;

import android.annotation.SuppressLint;
import android.util.Log;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BasePresenter;

/**
 * Created by Luis on 16/10/17.
 */

public class PlaceDetailPresenter
    extends BasePresenter<PlaceDetailContract.View, PlaceDetailContract.Model>
    implements PlaceDetailContract.Presenter {

  public static final String PARAM_PLACE_ID = "place_to_visit_id";


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.List.Presenter", "onPresenterCreated");

    if(isViewAttached()) {
      model.initRepository(getView().getManagedContext());
    }
  }

  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterResumed() {
    super.onPresenterResumed();
    Log.d("VisitCanary.List.Presenter", "onPresenterResumed");

    setupUI();
  }


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterDestroy() {
    super.onPresenterDestroy();
    Log.d("VisitCanary.List.Presenter", "onPresenterDestroy");
  }


  @Override
  protected PlaceDetailContract.Model initModel() {
    return new PlaceDetailModel();
  }

  private void setupUI(){
    if(isViewAttached()) {

      String placeId = getInStateBundle().getString(PARAM_PLACE_ID);
      //PlaceStore.Place place = model.getPlace(getView().getManagedContext(), placeId);
      PlaceStore.Place place = model.getPlace( placeId);

      if (place != null) {
        getView().setupUI(place);
      }

    }
  }


}

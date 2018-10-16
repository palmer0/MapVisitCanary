package es.ulpgc.eite.master.mapvisitcanary.list;

import android.annotation.SuppressLint;
import android.util.Log;

import es.ulpgc.eite.master.mapvisitcanary.detail.PlaceDetailPresenter;
import es.ulpgc.mvp.arch.BasePresenter;


/**
 * Created by Luis on 16/10/17.
 */

public class PlaceListPresenter
    extends BasePresenter<PlaceListContract.View, PlaceListContract.Model>
    implements PlaceListContract.Presenter {


  @SuppressLint("LongLogTag")
  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.List.Presenter", "onPresenterCreated");

    /*
    if(isViewAttached()) {
      //model.initStore(getView().getManagedContext());

      //model.fillPlaceStoreFromResources(getView().getManagedContext());
      //model.fillPlaceStoreFromAssets(getView().getManagedContext());
      model.init(getView().getManagedContext());
    }
    */
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
  protected PlaceListContract.Model initModel() {
    return new PlaceListModel();
  }

  
  private void setupUI(){
    if(isViewAttached()) {
      //getView().setupUI(model.getPlaces());
      getView().setupUI(model.getPlaces(getView().getManagedContext()));
    }
  }

  public void placeClicked(String placeId) {
    if(isViewAttached()) {
      getOutStateBundle().putString(PlaceDetailPresenter.PARAM_PLACE_ID, placeId);
      getView().openDetailActivity();
    }
  }

  public void menuClicked() {
    if(isViewAttached()) {
      getView().openMapActivity();
    }
  }

}

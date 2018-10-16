package es.ulpgc.eite.master.mapvisitcanary.map;

import android.content.Context;
import android.util.Log;

import java.util.List;

import es.ulpgc.eite.master.mapvisitcanary.data.PlaceRepository;
import es.ulpgc.eite.master.mapvisitcanary.data.PlaceStore;
import es.ulpgc.mvp.arch.BaseModel;

public class PlaceMapModel
    extends BaseModel<PlaceMapContract.Presenter> implements PlaceMapContract.Model {

  private PlaceRepository repository;

  @Override
  public void onPresenterCreated() {
    super.onPresenterCreated();
    Log.d("VisitCanary.Map.Model", "onPresenterCreated");
  }

  public void initRepository(Context managedContext){
    repository = PlaceRepository.getInstance(managedContext);
  }

  public List<PlaceStore.Place> getPlaces() {
    return repository.getPlaces();
  }

  /*
  public List<PlaceStore.Place> getPlaces(Context managedContext) {
    return PlaceRepository.getInstance(managedContext).getPlaces();
  }
  */

}

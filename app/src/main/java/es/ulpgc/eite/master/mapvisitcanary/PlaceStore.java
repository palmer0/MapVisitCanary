package es.ulpgc.eite.master.mapvisitcanary;

import java.util.ArrayList;
import java.util.List;


public class PlaceStore {


    private List<Place> places = new ArrayList();


    public PlaceStore(List<String> titles, List<String> descs, List<String> pics, List<String> locs) {
        for (int position = 0; position < titles.size(); position++) {
            String title = titles.get(position);
            String detail = descs.get(position);
            String picture = pics.get(position);
            String location = locs.get(position);
            addPlace(createPlace(position, title, detail, picture, location));
        }
    }


    private void addPlace(Place place) {
        places.add(place);
    }

    private Place createPlace(int position, String title, String desc, String pic, String loc ) {
        return new Place(String.valueOf(position), title, desc, pic, loc);
    }


    public List<Place> getPlaces(){
        return places;
    }

    public Place getPlaceByPosition(int position) {
        return places.get(position);
    }

    public Place getPlaceById(String id) {
        Integer position = Integer.valueOf(id);
        return getPlaceByPosition(position);
    }

    public class Place {
        public final String id;
        public final String title;
        public final String details;
        public final String picture;
        public final String location;


        public Place(String id, String title, String details, String picture, String location) {
            this.id = id;
            this.title = title;
            this.details = details;
            this.picture = picture;
            this.location = location;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}

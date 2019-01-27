package kandy.com.locationtracking.util;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SuggestionHolder {

   /* private static SuggestionHolder ourInstance = new SuggestionHolder();

    private ArrayList<LocatorSuggestionResult> lsr;

    public static SuggestionHolder getInstance() {
        return ourInstance;
    }

    private SuggestionHolder() {
        lsr = new ArrayList<>();
    }

    //This ensures that the arraylist holds the correct data to be accessed outside the content provider
    public void PopulateSuggestions(List<LocatorSuggestionResult> lsrArray) {
        lsr.clear();
        for(LocatorSuggestionResult ls : lsrArray) {
            lsr.add(ls);
        }
    }

    //Called from the MainActivity so we can interact with the search suggestions directly.
    public LocatorGeocodeResult FindFeatureFromSearch(int index) {
        LocatorGeocodeResult lgr = new LocatorGeocodeResult();
        try {
            lgr = new GetLocatorResult().execute(index).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return lgr;
    }

    //Runs geocoding async so we do not block the thread.
    private class GetLocatorResult extends AsyncTask<Integer, Void, LocatorGeocodeResult> {

        List<String> outFields = new ArrayList<>();

        @Override
        protected LocatorGeocodeResult doInBackground(Integer... indexs) {
            List<LocatorGeocodeResult> lgr = new ArrayList<>();
            try {
                outFields.add("*");
                lgr = Locator.createOnlineLocator().find(lsr.get(indexs[0]),1, outFields, SpatialReference.create(SpatialReference.WKID_WGS84_WEB_MERCATOR));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return lgr.get(0);
        }
    }
*/
}

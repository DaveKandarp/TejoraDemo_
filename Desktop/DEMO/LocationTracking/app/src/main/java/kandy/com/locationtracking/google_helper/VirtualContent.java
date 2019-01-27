package kandy.com.locationtracking.google_helper;

import android.app.SearchManager;
import android.database.MatrixCursor;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.Locator;

import java.util.concurrent.ExecutionException;

public class VirtualContent {

    /*//This is what is invoked when the query in the AGOLContentProvider is called.
    //This returns a new cursor so we can see the search suggestions in our widget.
    public static MatrixCursor getSuggestions(String query) {

        MatrixCursor mC = new MatrixCursor(new String[] {"_id", SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA});

        try {
            mC = new SuggestionsGet().execute(query).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return mC;
    }

    //This runs the LocatorSuggestions asynchronously as to avoid ANR errors.
    private static class SuggestionsGet extends AsyncTask<String, Void, MatrixCursor> {

        private final String TAG = "ESS";

        @Override
        protected MatrixCursor doInBackground(String... strings) {

            MatrixCursor mC = new MatrixCursor(new String[]{"_id", SearchManager.SUGGEST_COLUMN_TEXT_1, SearchManager.SUGGEST_COLUMN_INTENT_DATA});

            for(String query : strings) {

                Locator locator = Locator.createOnlineLocator();
                List<LocatorSuggestionResult> lsr = null;
                try {
                    lsr = locator.suggest(new LocatorSuggestionParameters(query));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int i = 0;
                for (LocatorSuggestionResult result : lsr) {
                    Log.d(TAG, result.getText());
                    mC.addRow(new Object[]{i, result.getText(), i});
                    i++;
                }
//                SuggestionHolder.getInstance().PopulateSuggestions(lsr);
            }
            return mC;
        }

    }*/

}

package kandy.com.locationtracking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import kandy.com.locationtracking.R;
import kandy.com.locationtracking.database.DBHelper;
import kandy.com.locationtracking.pojo.TripPOJO;

import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class MakeTripFragment extends Fragment implements PlaceSelectionListener {
    EditText autocomplete_fragment_destination;
    EditText autocomplete_fragment_source,trip_name;
    Button trip_add;
    TripPOJO tripPOJO;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.maketrip, container, false);
        trip_name = view.findViewById(R.id.trip_name);
        autocomplete_fragment_source = view.findViewById(R.id.autocomplete_fragment_source);
        autocomplete_fragment_destination = view.findViewById(R.id.autocomplete_fragment_destination);
        trip_add = view.findViewById(R.id.trip_add);
        tripPOJO = new TripPOJO();
        trip_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(autocomplete_fragment_source.getText().toString().length() < 1) {
                    autocomplete_fragment_source.setError("Add Start trip Location");
                }
                else if(autocomplete_fragment_destination.getText().toString().length() < 1) {
                    autocomplete_fragment_destination.setError("Add end trip Location");
                }
                else if(trip_name.getText().toString().trim().length() < 1 ){
                    trip_name.setError("Add end trip Location");
                }
                else if(trip_name.getText().toString().trim().length() > 0 )
                {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeZone(TimeZone.getTimeZone("IST"));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
                    String trip_date = sdf.format(cal.getTime());
                    tripPOJO.setTrip_date_time(trip_date);

                    tripPOJO.setName(trip_name.getText().toString());
                    DBHelper helper = DBHelper.getInstance(getActivity());
                    helper.insertTripName(tripPOJO);


                    /*TripActivitiesPOJO tripActivitiesPOJO = new TripActivitiesPOJO();
                    tripActivitiesPOJO.setTrip_activity_address(tripPOJO.getDestination_address());
                    tripActivitiesPOJO.setTrip_activity_latitude(tripPOJO.getSource_latitude());
                    tripActivitiesPOJO.setTrip_activity_longitude(tripPOJO.getSource_longitude());
                    tripActivitiesPOJO.setTrip_activity_date_time(tripPOJO.getTrip_date_time());
                    tripActivitiesPOJO.setTrip_description(tripPOJO.getName());
                    helper.insertTripName(tripActivitiesPOJO);*/

                    trip_name.setText("");
                    autocomplete_fragment_source.setText("");
                    autocomplete_fragment_destination.setText("");

                    Toast.makeText(getActivity(),"Your Trip Successfully created",Toast.LENGTH_LONG).show();
                    tripPOJO = null;
                    tripPOJO = new TripPOJO();
                    new Runnable() {
                        @Override
                        public void run() {
                            File newFile = new File(Environment.getExternalStorageDirectory(), "DIRE_BACKUP");
                            FileChannel outputChannel = null;
                            FileChannel inputChannel = null;
                            try {
                                outputChannel = new FileOutputStream(newFile).getChannel();
                                inputChannel = new FileInputStream(Environment.getDataDirectory()+File.separator+"databases"+File.separator+DBHelper.DB_NAME).getChannel();
                                inputChannel.transferTo(0, inputChannel.size(), outputChannel);
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    if (inputChannel != null && inputChannel.isOpen())
                                        inputChannel.close();
                                    if (outputChannel != null && outputChannel.isOpen())
                                        outputChannel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    };
                    notifyAll();

                }

            }
        });



        autocomplete_fragment_source.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getActivity());
                    startActivityForResult(intent, 100);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        autocomplete_fragment_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getActivity());
                    startActivityForResult(intent, 101);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });




        return view;
    }

    /**
     * Callback invoked when a place has been selected from the PlaceAutocompleteFragment.
     */
    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place Selected: " + place.getName());
    }

    @Override
    public void onError(Status status) {

    }

    // A place has been received; use requestCode to track the request.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(data == null)
        {
            if(requestCode == 100)
            {
                autocomplete_fragment_source.setText("");
            }else if(requestCode == 101)
            {
                autocomplete_fragment_destination.setText("");

            }
            return;

        }
        Place place = PlaceAutocomplete.getPlace(getActivity(), data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                autocomplete_fragment_source.setText(place.getAddress());
                tripPOJO.setSource_address(String.valueOf(place.getAddress()));
                tripPOJO.setSource_latitude(place.getLatLng().latitude);
                tripPOJO.setSource_longitude(place.getLatLng().longitude);

            }
        }
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                autocomplete_fragment_destination.setText(place.getAddress());
                tripPOJO.setDestination_address(String.valueOf(place.getAddress()));
                tripPOJO.setDestination_latitude(place.getLatLng().latitude);
                tripPOJO.setDestination_longitude(place.getLatLng().longitude);
            }
        }
    }

}

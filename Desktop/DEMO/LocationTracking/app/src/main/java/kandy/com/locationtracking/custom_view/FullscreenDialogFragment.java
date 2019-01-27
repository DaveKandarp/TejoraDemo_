package kandy.com.locationtracking.custom_view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import kandy.com.locationtracking.R;
import kandy.com.locationtracking.database.DBHelper;
import kandy.com.locationtracking.listners.CallBack;
import kandy.com.locationtracking.pojo.TripActivitiesPOJO;
import kandy.com.locationtracking.pojo.TripPOJO;

import static android.app.Activity.RESULT_OK;

public class FullscreenDialogFragment extends DialogFragment {

    private TextInputEditText act_description;
    private TextInputEditText address;
    private TripActivitiesPOJO tripActivitiesPOJO;
    private int trip_id;
    CallBack callBack;
    private DBHelper dbHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_trip_dialog, container, false);
        Bundle bundle = getArguments();
        trip_id = bundle.getInt("TRIP_ID");
        String trip_name = bundle.getString("TRIP_NAME");
        dbHelper = new DBHelper(getContext());


        TextView trip_title = rootView.findViewById(R.id.trip_title);
        trip_title.setText(trip_name);
        tripActivitiesPOJO = new TripActivitiesPOJO();

        ImageButton button_close = rootView.findViewById(R.id.button_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onError();
                dismiss();
            }
        });
        Button add_destination = rootView.findViewById(R.id.add_destination);
        add_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Are you sure Finished your Trip ?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                TripPOJO tripPOJO = new TripPOJO();

                                tripPOJO.setID(tripActivitiesPOJO.getTrip_id());
                                tripPOJO.setTrip_date_time(tripActivitiesPOJO.getTrip_activity_date_time());
                                tripPOJO.setDestination_latitude(tripActivitiesPOJO.getTrip_activity_latitude());
                                tripPOJO.setDestination_longitude(tripActivitiesPOJO.getTrip_activity_longitude());
                                tripPOJO.setDestination_address(tripActivitiesPOJO.getTrip_activity_address());

                                    dbHelper.updateTripCompleted(tripPOJO);
                                    dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });



        Button add_activity = rootView.findViewById(R.id.add_activity);
        add_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHelper.insertTripName(tripActivitiesPOJO);
                callBack.onSuccess();
                dismiss();

            }
        });



        act_description = rootView.findViewById(R.id.act_description);
        address = rootView.findViewById(R.id.address);

        /*address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getActivity());
                    startActivityForResult(intent, 300);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                return true;
            }
        });*/


        Button location_btn = rootView.findViewById(R.id.location_btn);
        location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(getActivity());
                    startActivityForResult(intent, 300);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null)
        {
            if(requestCode == 300)
            {
                address.setText("");
                act_description.setText("");
            }
            dismiss();
            return;
        }
        Place place = PlaceAutocomplete.getPlace(getActivity(), data);
        if (requestCode == 300) {
            if (resultCode == RESULT_OK) {

                Calendar cal = Calendar.getInstance();
                cal.setTimeZone(TimeZone.getTimeZone("IST"));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
                String trip_date = sdf.format(cal.getTime());

                address.setText(place.getAddress());
                tripActivitiesPOJO.setTrip_activity_date_time(trip_date);
                tripActivitiesPOJO.setTrip_id(trip_id);
                tripActivitiesPOJO.setTrip_description(act_description.getText().toString());
                tripActivitiesPOJO.setTrip_activity_address(String.valueOf(place.getAddress()));
                tripActivitiesPOJO.setTrip_activity_latitude(place.getLatLng().latitude);
                tripActivitiesPOJO.setTrip_activity_longitude(place.getLatLng().longitude);

            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getAttributes().alpha = 0.9f;

        return dialog;
    }

    public void setCallback(CallBack callBack) {
        this.callBack = callBack;
    }
}

package kandy.com.locationtracking.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kandy.com.locationtracking.database.DBHelper;
import kandy.com.locationtracking.R;
import kandy.com.locationtracking.adapters.ExpandableAdapter;
import kandy.com.locationtracking.pojo.TripModel;

public class RunningTripFragment extends Fragment {

    RecyclerView rvList;
    ExpandableAdapter adapter;
    DBHelper helper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.running_trip,container,false);
        helper = DBHelper.getInstance(getActivity());
        rvList = view.findViewById(R.id.list);
        adapter = new ExpandableAdapter(getActivity());
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        runningtrip();
        rvList.setAdapter(adapter);

        return view;
    }


    private void runningtrip()
    {
        adapter.setData(helper.getRunningTripList("N"));
    }


}

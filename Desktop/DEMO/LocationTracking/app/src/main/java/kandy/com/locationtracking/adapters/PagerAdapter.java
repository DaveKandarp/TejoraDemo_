package kandy.com.locationtracking.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kandy.com.locationtracking.activities.CompletedTripFragment;
import kandy.com.locationtracking.activities.MakeTripFragment;
import kandy.com.locationtracking.activities.RunningTripFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MakeTripFragment makeTrip = new MakeTripFragment();
                return makeTrip;
            case 1:
                RunningTripFragment runningTripFragment= new RunningTripFragment();
                return runningTripFragment;
            case 2:
                CompletedTripFragment completedTripFragment = new CompletedTripFragment();
                return completedTripFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

}

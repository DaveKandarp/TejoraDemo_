package kandy.com.locationtracking.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

    SharedPreferenceUtil sharedPreferenceUtil;
    String shared_DB = "shared_DB";

    static SharedPreferences preferences;

    public SharedPreferenceUtil getInstance(Context context)
    {
        if (sharedPreferenceUtil == null) {
            preferences = context.getSharedPreferences(shared_DB,Context.MODE_PRIVATE);
            sharedPreferenceUtil = new SharedPreferenceUtil();

        }
        return sharedPreferenceUtil;
    }


    public String getString(String KEY)
    {
        if(!KEY.equalsIgnoreCase(""))
        {
            KEY = preferences.getString(KEY,"");
        }
        return KEY;
    }

    public void putString(String KEY,String VALUE)
    {
        if(!KEY.equalsIgnoreCase("") && preferences != null)
        {
            preferences.edit().putString(KEY,VALUE);
            preferences.edit().commit();
        }
    }


    public boolean getBoolean(String KEY)
    {
        boolean val = false;;
        if(!KEY.equalsIgnoreCase("") && preferences != null)
        {
            val = preferences.getBoolean(KEY,false);
        }
        return val;
    }

    public void putBoolean(String KEY,boolean VALUE)
    {
        if(!KEY.equalsIgnoreCase("") && preferences != null)
        {
            preferences.edit().putBoolean(KEY,VALUE);
            preferences.edit().commit();
        }
    }

}

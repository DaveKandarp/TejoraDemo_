package kandy.com.locationtracking.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TripActivityTable {

    public static String TABLE_TRIPMASTER_ACTIVITY = "TripActivityMaster";
    public static String COL_ID = "_ID";
    public static String COL_ACT_ID = "ACT_ID";
    public static String COL_STAY_ACTIVITY_LATITUDE = "stay_activity_latitude";
    public static String COL_STAY_ACTIVITY_DATE_TIME = "stay_activity_dateTime";
    public static String COL_STAY_ACTIVITY_LONGITUDE = "stay_activity_longitude";
    public static String COL_STAY_ACTIVITY_DESCRIPTION = "stay_activity_Description";
    public static String COL_STAY_ACTIVITY_ADDRESS = "stay_activity_Addressx";

    private static String DB_CREATE = "CREATE TABLE " + TABLE_TRIPMASTER_ACTIVITY +
            "('"
            + COL_ACT_ID  + "' INTEGER , '"
            + COL_STAY_ACTIVITY_DESCRIPTION + "' TEXT,'"
            + COL_STAY_ACTIVITY_ADDRESS + "' TEXT,'"
            + COL_STAY_ACTIVITY_LATITUDE + "' DOUBLE,'"
            + COL_STAY_ACTIVITY_DATE_TIME + "' TEXT,'"
            + COL_STAY_ACTIVITY_LONGITUDE + "' DOUBLE "
            + "" +
            ")";

    public static void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DB_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }



}

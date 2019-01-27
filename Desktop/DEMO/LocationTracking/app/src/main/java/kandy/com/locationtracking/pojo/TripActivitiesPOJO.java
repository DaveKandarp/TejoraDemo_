package kandy.com.locationtracking.pojo;

public class TripActivitiesPOJO {
    private Integer trip_id;
    private String trip_description;
    private String trip_activity_date_time;
    private Double trip_activity_latitude;
    private Double trip_activity_longitude;
    private String trip_activity_address;
    int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(Integer trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_description() {
        return trip_description;
    }

    public void setTrip_description(String trip_description) {
        this.trip_description = trip_description;
    }

    public String getTrip_activity_date_time() {
        return trip_activity_date_time;
    }

    public void setTrip_activity_date_time(String trip_activity_date_time) {
        this.trip_activity_date_time = trip_activity_date_time;
    }

    public Double getTrip_activity_latitude() {
        return trip_activity_latitude;
    }

    public void setTrip_activity_latitude(Double trip_activity_latitude) {
        this.trip_activity_latitude = trip_activity_latitude;
    }

    public Double getTrip_activity_longitude() {
        return trip_activity_longitude;
    }

    public void setTrip_activity_longitude(Double trip_activity_longitude) {
        this.trip_activity_longitude = trip_activity_longitude;
    }

    public String getTrip_activity_address() {
        return trip_activity_address;
    }

    public void setTrip_activity_address(String trip_activity_address) {
        this.trip_activity_address = trip_activity_address;
    }
}

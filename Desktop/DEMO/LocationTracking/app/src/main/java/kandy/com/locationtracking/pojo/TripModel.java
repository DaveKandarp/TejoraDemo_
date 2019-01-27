package kandy.com.locationtracking.pojo;

import java.util.ArrayList;
import java.util.List;

public class TripModel {

    public enum STATE {
        CLOSED,
        OPENED
    }



    public STATE state = STATE.CLOSED;
    private Integer ID;
    private String name;
    private String trip_date_time;
    private String source_address;
    private String destination_address;
    private Double source_latitude;
    private Double source_longitude;
    private Double destination_latitude;
    private Double destination_longitude;
    List<TripActivitiesPOJO> activitiesPOJOS;
    int level;

    public ArrayList<TripModel> models = new ArrayList<>();

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrip_date_time() {
        return trip_date_time;
    }

    public void setTrip_date_time(String trip_date_time) {
        this.trip_date_time = trip_date_time;
    }

    public String getSource_address() {
        return source_address;
    }

    public void setSource_address(String source_address) {
        this.source_address = source_address;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public Double getSource_latitude() {
        return source_latitude;
    }

    public void setSource_latitude(Double source_latitude) {
        this.source_latitude = source_latitude;
    }

    public Double getSource_longitude() {
        return source_longitude;
    }

    public void setSource_longitude(Double source_longitude) {
        this.source_longitude = source_longitude;
    }

    public Double getDestination_latitude() {
        return destination_latitude;
    }

    public void setDestination_latitude(Double destination_latitude) {
        this.destination_latitude = destination_latitude;
    }

    public Double getDestination_longitude() {
        return destination_longitude;
    }

    public void setDestination_longitude(Double destination_longitude) {
        this.destination_longitude = destination_longitude;
    }

    public List<TripActivitiesPOJO> getActivitiesPOJOS() {
        return activitiesPOJOS;
    }

    public void setActivitiesPOJOS(List<TripActivitiesPOJO> activitiesPOJOS) {
        this.activitiesPOJOS = activitiesPOJOS;
    }
}

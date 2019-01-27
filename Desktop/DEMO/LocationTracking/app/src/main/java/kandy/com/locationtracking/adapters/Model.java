package kandy.com.locationtracking.adapters;

import java.util.ArrayList;
import java.util.List;

import kandy.com.locationtracking.pojo.TripActivitiesPOJO;

public class Model {

    STATE state = STATE.CLOSED;
    public enum STATE {
        CLOSED,
        OPENED

    }
    String name;
    int level;
    String designation;
    Integer ID;
    String trip_date_time;
    String source_address;
    String destination_address;
    Double source_latitude;
    Double source_longitude;
    Double destination_latitude;
    Double destination_longitude;
    List<TripActivitiesPOJO> activitiesPOJOS;

    ArrayList<Model> models = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
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

    public ArrayList<Model> getModels() {
        return models;
    }

    public void setModels(ArrayList<Model> models) {
        this.models = models;
    }
}

package at.tuwien.sbc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class DoodleSchedule implements Serializable {
    
    private String id;
    
    private int day;
    
    private List<Integer> hours;
    
    public DoodleSchedule() {
        this.hours = new ArrayList<Integer>();
    }
    
    @SpaceId(autoGenerate=true)
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }
    
    public List<Integer> getHours() {
        return hours;
    }
    
    public void setDay(int day) {
        this.day = day;
    }
    
    public int getDay() {
        return day;
    }
    
    public String toString() {
        return "D: " + day + " h: " + Arrays.deepToString(hours.toArray());
    }
}

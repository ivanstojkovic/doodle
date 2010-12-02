package at.tuwien.sbc.model;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

@SpaceClass
public class Notification implements DoodleSpaceObject {

    private String id;
    
    private String receipient;
    
    private String regarding;
    
    public Notification() {
        super();
    }
    
    public Notification(String userId) {
        this.receipient = userId;
    }
    
    public Notification(String userId, String message) {
        this.receipient = userId;
        this.regarding = message;
    }

    @SpaceId(autoGenerate = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceipient() {
        return receipient;
    }

    public void setReceipient(String receipient) {
        this.receipient = receipient;
    }

    public String getRegarding() {
        return regarding;
    }

    public void setRegarding(String regarding) {
        this.regarding = regarding;
    }
    
}

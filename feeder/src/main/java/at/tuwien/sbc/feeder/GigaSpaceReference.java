package at.tuwien.sbc.feeder;

import org.openspaces.core.GigaSpace;

public class GigaSpaceReference {

    private static GigaSpaceReference uniqueInstance;
    
    private GigaSpace gigaSpace;
    
    public static synchronized GigaSpaceReference getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GigaSpaceReference();
        }
        
        return uniqueInstance;
    }
    
    private GigaSpaceReference() {
        
    }

    public void setGigaSpace(GigaSpace gigaSpace) {
        this.gigaSpace = gigaSpace;
    }

    public GigaSpace getGigaSpace() {
        return gigaSpace;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singletons cannot be cloned");
    }
}

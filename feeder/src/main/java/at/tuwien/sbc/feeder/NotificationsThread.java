package at.tuwien.sbc.feeder;

import org.openspaces.core.GigaSpace;

import at.tuwien.sbc.feeder.gui.frames.MainFrame;
import at.tuwien.sbc.model.Notification;
import at.tuwien.sbc.model.Peer;

public class NotificationsThread extends Thread {

    private boolean running;

    private int count;

    private GigaSpace space;
    
    private MainFrame main;

    public NotificationsThread(GigaSpace space, MainFrame main) {
        this.space = space;
        this.main = main;
        this.setRunning(true);
    }

    public void run() {
        while (isRunning()) {
            this.count = this.retrieveCount();
            this.main.setNotificationsCount(this.count);
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            
            }
        }
    }

    public void setRunning(boolean run) {
        this.running = run;
    }

    public boolean isRunning() {
        return running;
    }

    public int getCount() {
        return count;
    }

    private int retrieveCount() {
        Peer user = ControllerReference.getInstance().getUser();
        if (user != null) {
            Notification template = new Notification(user.getId());
            return this.space.readMultiple(template, Integer.MAX_VALUE).length;
        }
        
        return 0;
    }
}

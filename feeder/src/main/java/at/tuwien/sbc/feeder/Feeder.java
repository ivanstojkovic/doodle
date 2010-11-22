package at.tuwien.sbc.feeder;

import javax.swing.SwingUtilities;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import at.tuwien.sbc.feeder.gui.frames.LoginFrame;
import at.tuwien.sbc.feeder.gui.frames.MainFrame;

public class Feeder implements InitializingBean, DisposableBean {
    
    @GigaSpaceContext
    private GigaSpace gigaSpace;
    
    
    public Feeder() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
    
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        ControllerReference.getInstance().setGigaSpace(this.gigaSpace);
    }
    
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}

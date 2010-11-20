package at.tuwien.sbc.feeder;

import javax.swing.SwingUtilities;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.context.GigaSpaceContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import at.tuwien.sbc.feeder.gui.LoginFrame;

public class Feeder implements InitializingBean, DisposableBean {
    
    @GigaSpaceContext
    private GigaSpace gigaSpace;
    
    
    public Feeder() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginFrame inst = new LoginFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
    
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        GigaSpaceReference.getInstance().setGigaSpace(this.gigaSpace);
    }
    
    public void destroy() throws Exception {
        System.out.println("destroy");
    }
}

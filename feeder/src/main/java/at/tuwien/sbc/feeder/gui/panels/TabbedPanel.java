package at.tuwien.sbc.feeder.gui.panels;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JPanel {
    
    private JTabbedPane tabs;
    
    public TabbedPanel() {
        super(new GridLayout(1, 1));
        
        setTabs(new JTabbedPane());
        ImageIcon icon = createImageIcon("images/organize.jpg");
        
        EventOrganizationPanel pnlOrg = new EventOrganizationPanel();
        this.getTabs().addTab("Organization", icon, pnlOrg, "Organize Events");
        this.getTabs().setMnemonicAt(0, KeyEvent.VK_1);
        
        icon = createImageIcon("images/overview.jpg");
        PeerEventsPanel pnlPE = new PeerEventsPanel();
        this.getTabs().addTab("Overview", icon, pnlPE, "Overview over your events");
        this.getTabs().setMnemonicAt(1, KeyEvent.VK_2);
        
        // Add the tabbed pane to this panel.
        add(this.getTabs());
        
        // The following line enables to use scrolling tabs.
        this.getTabs().setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    public void enableTab(int tab, boolean enable) {
        if (tab == -1) {
            this.setEnabled(enable);
            for (Component c : this.getTabs().getComponents()) {
                this.recursiveEnabling((Container) c, enable);
                this.recursiveEnabling((Container) c, enable);
            }
        } else {
            Component t = this.getTabs().getComponentAt(tab);
            t.setEnabled(enable);
            this.recursiveEnabling((Container) this.getTabs().getComponentAt(tab), enable);
        }
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        return new ImageIcon(ClassLoader.getSystemResource(path));
    }
    
    private void recursiveEnabling(Container c, boolean enable) {
        c.setEnabled(enable);
        for (Component comp : c.getComponents()) {
            comp.setEnabled(enable);
            if (comp instanceof Container) {
                this.recursiveEnabling((Container) comp, enable);
            }
        }
    }

    public void setTabs(JTabbedPane tabs) {
        this.tabs = tabs;
    }

    public JTabbedPane getTabs() {
        return tabs;
    }
    
}
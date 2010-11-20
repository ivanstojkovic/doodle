package at.tuwien.sbc.feeder.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainFrame extends javax.swing.JFrame {
    private JTabbedPane tbPnl;
    private JPanel pnlOverview;
    private JPanel pnlMain;
    private JLabel lbl2;
    private JLabel lbl;
    private JPanel pnlOrg;

    public MainFrame() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(thisLayout);
            {
                getContentPane().add(new TabbedPanel(), BorderLayout.CENTER);
            }
 
            pack();
            this.setSize(735, 432);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

}

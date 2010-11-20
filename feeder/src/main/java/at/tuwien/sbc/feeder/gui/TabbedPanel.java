package at.tuwien.sbc.feeder.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TabbedPanel extends JPanel {
   public TabbedPanel() {
       super(new GridLayout(1, 1));
       
       JTabbedPane tabbedPane = new JTabbedPane();
       ImageIcon icon = createImageIcon("images/middle.gif");
       
       JComponent panel1 = makeTextPanel("Panel #1");
       tabbedPane.addTab("Organization", icon, panel1,
               "Does nothing");
       tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
       
       JComponent panel2 = makeTextPanel("Panel #2");
       tabbedPane.addTab("Overview", icon, new SearchPanel(),
               "Does twice as much nothing");
       tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
       
       //Add the tabbed pane to this panel.
       add(tabbedPane);
       
       //The following line enables to use scrolling tabs.
       tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
   }
   
   protected JComponent makeTextPanel(String text) {
       JPanel panel = new JPanel(false);
       JLabel filler = new JLabel(text);
       filler.setHorizontalAlignment(JLabel.CENTER);
       panel.setLayout(new GridLayout(1, 1));
       panel.add(filler);
       return panel;
   }
   
   /** Returns an ImageIcon, or null if the path was invalid. */
   protected static ImageIcon createImageIcon(String path) {
       java.net.URL imgURL = TabbedPanel.class.getResource(path);
       if (imgURL != null) {
           return new ImageIcon(imgURL);
       } else {
           System.err.println("Couldn't find file: " + path);
           return null;
       }
   }
   
   /**
    * Create the GUI and show it.  For thread safety,
    * this method should be invoked from
    * the event dispatch thread.
    */
   private static void createAndShowGUI() {
       //Create and set up the window.
       JFrame frame = new JFrame("TabbedPanel");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       //Add content to the window.
       frame.add(new TabbedPanel(), BorderLayout.CENTER);
       
       //Display the window.
       frame.pack();
       frame.setVisible(true);
   }
}
package at.tuwien.sbc.feeder.gui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import at.tuwien.sbc.feeder.GigaSpaceReference;
import at.tuwien.sbc.model.Peer;

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
public class SearchPanel extends javax.swing.JPanel implements ActionListener {
    private JPanel resultPnl;
    private JScrollPane scroller;
    private JTextArea resultTA;
    private JButton searchBtn;
    private JPanel searchPnl;
    private JTextField txtSearch;

    /**
    * Auto-generated main method to display this 
    * JPanel inside a new JFrame.
    */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new SearchPanel());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    public SearchPanel() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            setPreferredSize(new Dimension(400, 300));
            {
                searchPnl = new JPanel();
                this.add(searchPnl, BorderLayout.NORTH);
                {
                    txtSearch = new JTextField();
                    searchPnl.add(getTxtSearch());
                    txtSearch.setPreferredSize(new java.awt.Dimension(186, 22));
                    txtSearch.setText("name");
                }
                {
                    searchBtn = new JButton();
                    searchPnl.add(searchBtn);
                    searchBtn.setText("Search");
                    searchBtn.addActionListener(this);
                    searchBtn.setActionCommand("search");
                }
            }
            {
                resultPnl = new JPanel();
                this.add(resultPnl, BorderLayout.CENTER);
                resultPnl.setPreferredSize(new java.awt.Dimension(400, 244));
                resultPnl.setSize(400, 300);
                {
                    
                    {
                        resultTA = new JTextArea();
                        resultTA.setPreferredSize(new java.awt.Dimension(381, 253));
                        resultTA.setEditable(false);
                        scroller = new JScrollPane(resultTA, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    }
                    resultPnl.add(scroller);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JTextField getTxtSearch() {
        return txtSearch;
    }
    
    public JTextArea getResultTA() {
        return resultTA;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("search")) {
            Peer template = new Peer();
            template.setName(txtSearch.getName());
            Peer[] peers = GigaSpaceReference.getInstance().getGigaSpace().readMultiple(template, 100); // search for 100;
            
            System.out.println(Arrays.deepToString(peers));
            if (peers == null || peers.length == 0) {
                resultTA.setText("No Peers Found... \n");
            } else {
                final StringBuffer buf = new StringBuffer(42);
                for (Peer p : peers) {
                    buf.append(p.toString());
                    buf.append("\n");
                }
                
                resultTA.setText(buf.toString());
            }
        }
    }

}

package at.tuwien.sbc.feeder.gui.panels;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.model.Peer;

public class SearchPanel extends javax.swing.JPanel implements ActionListener, FocusListener {
    private JButton searchBtn;
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
        	FlowLayout thisLayout = new FlowLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(288, 34));
            {
            	txtSearch = new JTextField();
            	this.add(txtSearch);
            	txtSearch.setPreferredSize(new java.awt.Dimension(186, 22));
            	txtSearch.setText("name");
            	txtSearch.addFocusListener(this);
            }
            {
            	searchBtn = new JButton();
            	this.add(searchBtn);
            	searchBtn.setText("Search");
            	searchBtn.addActionListener(this);
            	searchBtn.setActionCommand("search");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public JTextField getTxtSearch() {
        return txtSearch;
    }
    

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("search")) {
        	String regex = this.txtSearch.getText();
            List<Peer> peers = ControllerReference.getInstance().searchByName(regex);
            String msg;
            if (peers.size() == 0) {
                msg = "No peers found!...";
            } else {
                final StringBuffer buf = new StringBuffer(42);
                buf.append("Peers matching: " + regex);
                for (Peer p : peers) {
                	buf.append("\n");
                	buf.append(p.toString());
                }
                
                msg = buf.toString();
            }
            
            JOptionPane.showMessageDialog(this, msg);
        }
    }

	public void focusGained(FocusEvent evt) {
		this.txtSearch.selectAll();
	    
    }

	public void focusLost(FocusEvent evt) {
		this.txtSearch.select(0, 0);
	    
    }

}

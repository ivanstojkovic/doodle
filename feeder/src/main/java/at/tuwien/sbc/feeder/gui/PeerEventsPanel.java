package at.tuwien.sbc.feeder.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import at.tuwien.sbc.feeder.common.Constants;

public class PeerEventsPanel extends javax.swing.JPanel implements ActionListener {
    private JPanel pnlSelection;
    private JTextArea txtArea;
    private JScrollPane scroller;
    private JButton btnSelect;
    private JComboBox cmbType;
    
    public PeerEventsPanel() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            setPreferredSize(new Dimension(400, 300));
            {
                pnlSelection = new JPanel();
                this.add(pnlSelection, BorderLayout.NORTH);
                pnlSelection.setPreferredSize(new java.awt.Dimension(400, 32));
                {
                    ComboBoxModel cmbTypeModel = new DefaultComboBoxModel(new String[] {"Organizer", "Participant"});
                    cmbType = new JComboBox();
                    pnlSelection.add(cmbType);
                    cmbType.setModel(cmbTypeModel);
                    cmbType.setPreferredSize(new java.awt.Dimension(105, 22));
                }
                {
                    btnSelect = new JButton();
                    pnlSelection.add(btnSelect);
                    btnSelect.setText("Show");
                    btnSelect.setActionCommand(Constants.CMD_BTN_SHOW);
                    btnSelect.addActionListener(this);
                }
            }
            {
                {
                    txtArea = new JTextArea();
                    txtArea.setPreferredSize(new java.awt.Dimension(388, 265));
                }
                scroller = new JScrollPane(txtArea);
                this.add(scroller, BorderLayout.CENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals(Constants.CMD_BTN_SHOW)) {
            if (cmbType.getSelectedItem().equals("Organizer")) {
                this.txtArea.setText(this.retrieveOrganizedEvents());
            } else if (cmbType.getSelectedItem().equals("Participant")) {
                this.txtArea.setText(this.retrieveParticipantEvents());
            } else {
                this.txtArea.setText("An Error occurred");
            }
        }
    }

    private String retrieveParticipantEvents() {
        
        return "participatns";
    }

    private String retrieveOrganizedEvents() {
        return "organized";
    }
    
}

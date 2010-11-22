package at.tuwien.sbc.feeder.gui.panels;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;


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
public class EventOrganizationPanel extends javax.swing.JPanel {
    private JPanel pnlNorth;
    private JScrollPane scrlInvites;
    private JPanel jPanel1;
    private SingleSchedulePanel pnlSchedule2;
    private SingleSchedulePanel pnlSchedule;
    private JList lstInvites;
    private JList ListParicipants;
    private JScrollPane scrlParticipants;
    private JLabel lblParticipants;
    private JLabel lblInvites;
    private JScrollPane pnlSchedules;
    private JPanel pnlPeers;
    private JButton btnEdit;
    private JButton btnCreate;
    private JPanel pnlButtons;
    private JComboBox cmbEvent;

    public EventOrganizationPanel() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(470, 320));
            {
                pnlNorth = new JPanel();
                FormLayout pnlNorthLayout = new FormLayout(
                    "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)", 
                    "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
                pnlNorth.setLayout(pnlNorthLayout);
                this.add(pnlNorth, BorderLayout.NORTH);
                pnlNorth.setPreferredSize(new java.awt.Dimension(400, 37));
                {
                    ComboBoxModel cmbEventModel = 
                        new DefaultComboBoxModel(
                            new String[] { "Item One", "Item Two" });
                    cmbEvent = new JComboBox();
                    pnlNorth.add(cmbEvent, new CellConstraints("2, 2, 1, 1, default, default"));
                    cmbEvent.setModel(cmbEventModel);
                    cmbEvent.setPreferredSize(new java.awt.Dimension(102, 27));
                }
            }
            {
                pnlPeers = new JPanel();
                pnlPeers.setLayout(null);
                this.add(pnlPeers, BorderLayout.EAST);
                pnlPeers.setPreferredSize(new java.awt.Dimension(160, 252));
                pnlPeers.setBorder(BorderFactory.createTitledBorder("Peers"));
                {
                    lblInvites = new JLabel();
                    pnlPeers.add(lblInvites);
                    lblInvites.setText("Invitations:");
                    lblInvites.setPreferredSize(new java.awt.Dimension(100, 15));
                    lblInvites.setBounds(5, 20, 68, 15);
                }
                {
                    scrlInvites = new JScrollPane();
                    pnlPeers.add(scrlInvites);
                    scrlInvites.setBounds(5, 36, 134, 77);
                    {
                        ListModel lstInvitesModel = 
                            new DefaultComboBoxModel(
                                new String[] { "Item One", "Item Two" });
                        lstInvites = new JList();
                        scrlInvites.setViewportView(lstInvites);
                        lstInvites.setModel(lstInvitesModel);
                    }
                }
                {
                    lblParticipants = new JLabel();
                    pnlPeers.add(lblParticipants);
                    lblParticipants.setText("Participants:");
                    lblParticipants.setBounds(5, 117, 94, 18);
                }
                {
                    scrlParticipants = new JScrollPane();
                    pnlPeers.add(scrlParticipants);
                    scrlParticipants.setBounds(5, 141, 134, 74);
                    {
                        ListModel ListParicipantsModel = 
                            new DefaultComboBoxModel(
                                new String[] { "Item One", "Item Two" });
                        ListParicipants = new JList();
                        scrlParticipants.setViewportView(ListParicipants);
                        ListParicipants.setModel(ListParicipantsModel);
                    }
                }
            }
            {
                {
                    jPanel1 = new JPanel();
                    BoxLayout jPanel1Layout = new BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS);
                    jPanel1.setLayout(jPanel1Layout);
                    {
                        pnlSchedule = new SingleSchedulePanel();
                        jPanel1.add(pnlSchedule);
                        pnlSchedule.setPreferredSize(new java.awt.Dimension(300, 61));
                    }
                    {
                        pnlSchedule2 = new SingleSchedulePanel();
                        jPanel1.add(pnlSchedule2);
                        pnlSchedule2.setPreferredSize(new java.awt.Dimension(300, 115));
                    }
                }
                
                pnlSchedules = new JScrollPane();
                pnlSchedules.setViewportView(jPanel1);
                this.add(pnlSchedules, BorderLayout.CENTER);
                pnlSchedules.setBorder(BorderFactory.createTitledBorder("Schedules"));
                pnlSchedules.setPreferredSize(new java.awt.Dimension(262, 252));
            }
            {
                pnlButtons = new JPanel();
                this.add(pnlButtons, BorderLayout.SOUTH);
                pnlButtons.setLayout(null);
                pnlButtons.setPreferredSize(new java.awt.Dimension(400, 31));
                {
                    btnCreate = new JButton();
                    pnlButtons.add(btnCreate, new CellConstraints("4, 1, 1, 1, default, default"));
                    btnCreate.setText("Create");
                    btnCreate.setBounds(310, 4, 92, 23);
                }
                {
                    btnEdit = new JButton();
                    pnlButtons.add(btnEdit);
                    btnEdit.setText("Edit");
                    btnEdit.setBounds(405, 4, 65, 23);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

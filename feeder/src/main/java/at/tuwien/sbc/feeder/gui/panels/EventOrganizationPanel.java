package at.tuwien.sbc.feeder.gui.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.DoodleSchedule;
import at.tuwien.sbc.model.Peer;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class EventOrganizationPanel extends javax.swing.JPanel implements ActionListener {
    private JPanel pnlNorth;
    private JScrollPane scrlInvites;
    private JPanel jPanel1;
    private SingleSchedulePanel pnlSchedule;
    private JList lstInvites;
    private JList ListParicipants;
    private JScrollPane scrlParticipants;
    private JLabel lblParticipants;
    private JLabel lblInvites;
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
                jPanel1 = new JPanel();
                this.add(jPanel1, BorderLayout.CENTER);
                BorderLayout jPanel1Layout = new BorderLayout();
                jPanel1.setLayout(jPanel1Layout);
                {
                    pnlSchedule = new SingleSchedulePanel();
                    jPanel1.add(pnlSchedule, BorderLayout.NORTH);
                    pnlSchedule.setPreferredSize(new java.awt.Dimension(300, 61));
                }
            }
            {
                pnlNorth = new JPanel();
                FormLayout pnlNorthLayout = new FormLayout("max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)",
                    "max(p;5dlu), max(p;5dlu), max(p;5dlu), max(p;5dlu)");
                pnlNorth.setLayout(pnlNorthLayout);
                this.add(pnlNorth, BorderLayout.NORTH);
                pnlNorth.setPreferredSize(new java.awt.Dimension(400, 37));
                {
                    ComboBoxModel cmbEventModel = new DefaultComboBoxModel(new String[] {"Item One", "Item Two"});
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
                        ListModel lstInvitesModel = new DefaultComboBoxModel(new String[] {"Item One", "Item Two"});
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
                        ListModel ListParicipantsModel = new DefaultComboBoxModel(new String[] {"Item One", "Item Two"});
                        ListParicipants = new JList();
                        scrlParticipants.setViewportView(ListParicipants);
                        ListParicipants.setModel(ListParicipantsModel);
                    }
                }
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
                    btnCreate.setActionCommand(Constants.CMD_BTN_CREATE);
                    btnCreate.addActionListener(this);
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
    
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        
        if (cmd.equals(Constants.CMD_BTN_CREATE)) {
            try {
                Date start = this.pnlSchedule.getTxtStart().getDate();
                Date end = this.pnlSchedule.getTxtEnd().getDate();
                Calendar endCal = Calendar.getInstance();
                Calendar startCal = Calendar.getInstance();
                endCal.setTime(end);
                startCal.setTime(start);
                
                if (start.before(end) && (startCal.get(Calendar.HOUR_OF_DAY) < endCal.get(Calendar.HOUR_OF_DAY))) {
                    
                    String name = JOptionPane.showInputDialog("Please type in a name for this event.");
                    
                    if (name != null) {
                        
                        DoodleEvent event = new DoodleEvent();
                        event.setName(name);
                        
                        for (int d = startCal.get(Calendar.DAY_OF_YEAR); d <= endCal.get(Calendar.DAY_OF_YEAR); d++) {
                            DoodleSchedule day = new DoodleSchedule();
                            day.setDay(d);
                            for (int h = startCal.get(Calendar.HOUR_OF_DAY); h < endCal.get(Calendar.HOUR_OF_DAY); h++) {
                                day.getHours().add(h);
                            }
                            
                            event.getSchedules().add(day);
                        }
                        
                        System.out.println(event.toString());
                        ControllerReference.getInstance().createEvent(event);
                        Peer user = ControllerReference.getInstance().getUser();
                        user.getOrganized().add(event);
                        ControllerReference.getInstance().updateObject(user);
                    } 
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Idiot!");
                }
                
            } catch (ParseException e) {
                
            }
        }
        
    }
    
}

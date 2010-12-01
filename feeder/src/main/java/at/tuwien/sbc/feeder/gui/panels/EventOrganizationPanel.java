package at.tuwien.sbc.feeder.gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import org.apache.log4j.Logger;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.DoodleSchedule;
import at.tuwien.sbc.model.DoodleSpaceObject;
import at.tuwien.sbc.model.Peer;

import com.jgoodies.forms.layout.CellConstraints;

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
/**
 * TODO add refresh button for participants.
 */
public class EventOrganizationPanel extends javax.swing.JPanel implements ActionListener, MouseListener {

    private static final Logger logger = Logger.getLogger(EventOrganizationPanel.class);
    private JPanel pnlNorth;
    private JScrollPane scrlInvites;
    private JButton removeParticipantBtn;
    private JPanel jPanel1;
    private SingleSchedulePanel pnlSchedule;
    private JList lstInvites;
    private JList lstParicipants;
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
                pnlPeers = new JPanel();
                pnlPeers.setLayout(null);
                this.add(pnlPeers, BorderLayout.EAST);
                pnlPeers.setPreferredSize(new java.awt.Dimension(229, 252));
                pnlPeers.setBorder(BorderFactory.createTitledBorder("Peers"));
                {
                    lblInvites = new JLabel();
                    pnlPeers.add(lblInvites);
                    lblInvites.setText("Invitations:");
                    lblInvites.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/refresh.png")));
                    lblInvites.setBounds(5, 20, 134, 15);
                    lblInvites.addMouseListener(this);
                }
                {
                    scrlInvites = new JScrollPane();
                    pnlPeers.add(scrlInvites);
                    scrlInvites.setBounds(5, 36, 134, 77);
                    {
                        ListModel lstInvitesModel = new DefaultComboBoxModel(ControllerReference.getInstance()
                                .getAllPeers());
                        lstInvites = new JList();
                        scrlInvites.setViewportView(lstInvites);
                        lstInvites.setModel(lstInvitesModel);
                        lstInvites.setSize(208, 75);
                        lstInvites.setPreferredSize(new java.awt.Dimension(0, 0));
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
                        ListModel lstParticipantsModel = new DefaultComboBoxModel(getParticipantsForSelectedEvent()
                                .toArray());
                        lstParicipants = new JList();
                        scrlParticipants.setViewportView(lstParicipants);
                        lstParicipants.setModel(lstParticipantsModel);
                        lstParicipants.setSize(208, 75);
                    }
                }
                {
                    removeParticipantBtn = new JButton();
                    pnlPeers.add(removeParticipantBtn);
                    removeParticipantBtn.setText("remove");
                    removeParticipantBtn.setBounds(151, 148, 57, 22);
                    removeParticipantBtn.addActionListener(this);
                    removeParticipantBtn.setActionCommand(Constants.CMD_BTN_REMOVE_PARTICIPANT);
                }
            }
            {
                pnlNorth = new JPanel();
                this.add(pnlNorth, BorderLayout.NORTH);
                pnlNorth.setLayout(null);
                pnlNorth.setPreferredSize(new java.awt.Dimension(400, 37));
                {
                    cmbEvent = new JComboBox();
                    pnlNorth.add(cmbEvent, new CellConstraints("2, 2, 1, 1, default, default"));
                    cmbEvent.setBounds(9, 6, 181, 27);
                    cmbEvent.setModel(this.getEventsModel(ControllerReference.getInstance().getUser()));
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
                    btnCreate.setBounds(253, 4, 92, 23);
                    btnCreate.setActionCommand(Constants.CMD_BTN_CREATE);
                    btnCreate.addActionListener(this);
                }
                {
                    btnEdit = new JButton();
                    pnlButtons.add(btnEdit);
                    btnEdit.setText("Update");
                    btnEdit.setBounds(345, 4, 90, 23);
                    btnEdit.setActionCommand(Constants.CMD_BTN_UPDATE);
                    btnEdit.addActionListener(this);
                    btnEdit.setEnabled(this.isUpdateAllowed());
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isUpdateAllowed() {
        if (cmbEvent.getItemCount() == 0) {
            return false;
        } else {
            String event = (String) cmbEvent.getSelectedItem();
            return this.isEventEditPossible(event);
        }
    }

    private boolean isEventEditPossible(String name) {
        DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(name);
        
        if (event.retrieveParticipants().isEmpty()) {
            return true;
        }
        
        return false;
    }

    private List<String> getParticipantsForSelectedEvent() {
        if (cmbEvent != null) {
            String e = (String) cmbEvent.getSelectedItem();
            
            DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(e);
            
            if (event != null) {
                return event.retrieveParticipants();
            }
        }
        return new ArrayList<String>();
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();

        if (cmd.equals(Constants.CMD_BTN_CREATE)) {
          this.createEvent();
        } else if (cmd.equals(Constants.CMD_BTN_UPDATE)) {
            this.updateEvent();
            
        } else if (cmd.equals(Constants.CMD_BTN_ADD_INVITATION)) {

                DoodleEvent event = (DoodleEvent) cmbEvent.getSelectedItem();
                if (event != null) {

                    Object[] peers = lstInvites.getSelectedValues();

                    for (Object p : peers) {
                        Peer peer = (Peer) p;
                        event.addInvite(peer);
                        event.setAction("processIt");
                        ControllerReference.getInstance().getGigaSpace().write(event);
                    }
                }
            } else if (cmd.equals(Constants.CMD_BTN_REMOVE_INVITATION)) {
                // TODO Remove Invitation if the user has not already participated
        } else if (cmd.equals(Constants.CMD_BTN_REMOVE_PARTICIPANT)) {

            // TODO We must notify the participant

            DoodleEvent event = (DoodleEvent) cmbEvent.getSelectedItem();
            if (event != null) {

                Object[] peers = lstParicipants.getSelectedValues();

                for (Object p : peers) {
                    lstParicipants.remove((Component) p);
                }
            }
        }
    }

    private void updateEvent() {
        String name = (String) this.cmbEvent.getSelectedItem();
        DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(name);
        if (event != null) {
            logger.info("updating event with id: " + event.getId());
        } else {
            logger.error("NULL");
        }
        
    }

    private void createEvent() {
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

                    Peer current = ControllerReference.getInstance().getUser();
                    DoodleEvent event = new DoodleEvent();
                    event.setName(name);
                    event.setAction("new");
                    event.setOwner(current.getName());
                    event.setAction("processIt");

                    Object[] peers = lstInvites.getSelectedValues();

                    for (Object p : peers) {
                        Peer peer = (Peer) p;
                        event.addInvite(peer);
                    }

                    for (int d = startCal.get(Calendar.DAY_OF_YEAR); d <= endCal.get(Calendar.DAY_OF_YEAR); d++) {
                        for (int h = startCal.get(Calendar.HOUR_OF_DAY); h < endCal.get(Calendar.HOUR_OF_DAY); h++) {
                            DoodleSchedule day = new DoodleSchedule(current.getName(), event.getId());
                            day.setDay(d + "");
                            day.setHour(h + "");
                            ControllerReference.getInstance().getGigaSpace().write(day);
                            event.retrieveSchedules().add(day.getId());
                            for (int i = 0; i < lstInvites.getSelectedValues().length; i++) {
                                if (lstInvites.getSelectedValues()[i].equals(current)) {
                                    continue;
                                }
                                Peer p = (Peer) lstInvites.getSelectedValues()[i];
                                DoodleSchedule forPeer = new DoodleSchedule(d + "", h + "", p.getName(), event
                                        .getId());
                                ControllerReference.getInstance().getGigaSpace().write(forPeer);
                                event.retrieveSchedules().add(forPeer.getId());
                            }
                        }
                    }

                    ControllerReference.getInstance().getGigaSpace().write(event);
                    this.refresh();
                      
                }

            } else {
                JOptionPane.showMessageDialog(this, "Idiot!");
            }

        } catch (ParseException e) {
            // TODO Something
        }
        
    }

    private DefaultComboBoxModel getEventsModel(Peer user) {
        if (user == null) {
            return new DefaultComboBoxModel();
        } else {
            List<String> names = ControllerReference.getInstance().getEventNamesFromIds(user.retrieveOrganized());
            return new DefaultComboBoxModel(names.toArray());
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == lblInvites) {
            lstInvites.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getAllPeers()));
        }

    }

    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

//    public void refresh() {
//        lstInvites.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getAllPeers()));
//        String e = (String) cmbEvent.getSelectedItem();
//        if (e != null) {
//            lstParicipants.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getParticipantsForEvent(
//                    (String) cmbEvent.getSelectedItem()).toArray()));
//        }
//        cmbEvent.setModel(this.getEventsModel(ControllerReference.getInstance().getUser()));
//        
//        
//    }
    
    public void refresh() {
        logger.info("in refresh()");
        // Refresh Event - meanwhile couuld one Peer participate to this event
        if (cmbEvent.getSelectedItem() != null) {
            logger.info("SELECTED EVENT: " + cmbEvent.getSelectedItem().toString());
            DoodleEvent de = ControllerReference.getInstance().findEventByNameAndUser(cmbEvent.getSelectedItem().toString());
            //we do not need the next line of code.. findEvent does a refresh...
            DoodleEvent refreshedEvent = (DoodleEvent) ControllerReference.getInstance().refresh(de);
            logger.info("REFRESHED EVENT: " + refreshedEvent);
            // refresh Participant List
            if (refreshedEvent != null) {
                lstParicipants.setModel(new DefaultComboBoxModel(refreshedEvent.retrieveParticipants().toArray()));
            }
        }else{
            //  if no event ist in ComboBox the list of Participants must be empty
            logger.info("clearing Participation Box");
            lstParicipants.setModel(new DefaultComboBoxModel(new Object[]{}));
        }
        
        //  update Registered Peers
        lstInvites.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getAllPeers()));
        
        //  update ComboBox
        cmbEvent.setModel(getEventsModel(ControllerReference.getInstance().getUser()));
        
        btnEdit.setEnabled(this.isUpdateAllowed());
    }

}

package at.tuwien.sbc.feeder.gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
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
    private JButton commentAddNew;
    private JButton commentDelete;
    private JList commentList;
    private JPanel commentsPanel;
    private JPanel pnlCenter;
    private JButton btnRemoveInvite;
    private JButton btnAddInvite;
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
    private JComboBox cmbEvent;

    public EventOrganizationPanel() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(600, 600));
            this.setSize(600, 600);
            {
            	commentsPanel = new JPanel();
            	this.add(commentsPanel, BorderLayout.SOUTH);
            	commentsPanel.setLayout(null);
            	commentsPanel.setPreferredSize(new java.awt.Dimension(500, 140));
            	commentsPanel.setSize(500, 140);
            	commentsPanel.setBorder(BorderFactory.createTitledBorder("Comments"));
            	{
            		commentList = new JList();
            		commentsPanel.add(commentList);
            		commentList.setModel(new DefaultComboBoxModel(getCommentsForSelectedEvent().toArray()));
            		commentList.setBounds(17, 20, 451, 103);
            	}
            	{
            		commentDelete = new JButton();
            		commentsPanel.add(commentDelete);
            		commentDelete.setText("-");
            		commentDelete.setBounds(475, 20, 44, 26);
            		commentDelete.setActionCommand(Constants.CMD_BTN_REMOVE_COMMENT);
            		commentDelete.addActionListener(this);

            	}
            	{
            		commentAddNew = new JButton();
            		commentsPanel.add(commentAddNew);
            		commentAddNew.setText("new comment");
            		commentAddNew.setBounds(475, 101, 121, 22);
            		commentAddNew.setActionCommand(Constants.CMD_BTN_ADD_COMMENT);
            		commentAddNew.addActionListener(this);
            	}
            }
            {
                pnlCenter = new JPanel();
                GridLayout pnlCenterLayout = new GridLayout(1, 2);
                pnlCenterLayout.setColumns(1);
                pnlCenterLayout.setHgap(5);
                pnlCenterLayout.setVgap(5);
                pnlCenter.setLayout(pnlCenterLayout);
                this.add(pnlCenter, BorderLayout.CENTER);
                pnlCenter.setPreferredSize(new java.awt.Dimension(600, 250));
                pnlCenter.setSize(600, 250);
                {
                    pnlNorth = new JPanel();
                    this.add(pnlNorth, BorderLayout.NORTH);
                    pnlNorth.setLayout(null);
                    pnlNorth.setPreferredSize(new java.awt.Dimension(513, 33));
                    {
                        cmbEvent = new JComboBox();
                        pnlNorth.add(cmbEvent, new CellConstraints("2, 2, 1, 1, default, default"));
                        cmbEvent.setBounds(9, 6, 181, 27);
                        cmbEvent.setModel(this.getEventsModel(ControllerReference.getInstance().getUser()));
                        cmbEvent.addActionListener(this);
                        cmbEvent.setActionCommand(Constants.CMD_EVENT_COMBO_CHANGED);
                    }
                }
                {
                    jPanel1 = new JPanel();
                    pnlCenter.add(jPanel1);
                    jPanel1.setLayout(null);
                    jPanel1.setPreferredSize(new java.awt.Dimension(254, 241));
                    jPanel1.setBorder(BorderFactory.createTitledBorder("Dates"));
                    {
                        pnlSchedule = new SingleSchedulePanel();
                        jPanel1.add(pnlSchedule, new CellConstraints("2, 1, 1, 1, default, default"));
                        pnlSchedule.setBounds(9, 20, 232, 61);
                        pnlSchedule.setPreferredSize(new java.awt.Dimension(0, 0));
                    }
                    {
                        btnCreate = new JButton();
                        jPanel1.add(btnCreate);
                        btnCreate.setText("Create");
                        btnCreate.setBounds(9, 87, 110, 23);
                        btnCreate.setActionCommand(Constants.CMD_BTN_CREATE);
                        btnCreate.addActionListener(this);
                    }
                    {
                        btnEdit = new JButton();
                        jPanel1.add(btnEdit);
                        btnEdit.setText("Update");
                        btnEdit.setBounds(130, 87, 110, 23);
                        btnEdit.setActionCommand(Constants.CMD_BTN_UPDATE);
                        btnEdit.addActionListener(this);
                        btnEdit.setEnabled(this.isUpdateAllowed());

                    }
                }
                {
                    pnlPeers = new JPanel();
                    pnlCenter.add(pnlPeers);
                    pnlPeers.setLayout(null);
                    pnlPeers.setPreferredSize(new java.awt.Dimension(236, 283));
                    pnlPeers.setBorder(BorderFactory.createTitledBorder("Peers"));
                    {
                        lblInvites = new JLabel();
                        pnlPeers.add(lblInvites);
                        lblInvites.setText("Invitations:");
                        lblInvites.setIcon(new ImageIcon(ClassLoader.getSystemResource("images/refresh.png")));
                        lblInvites.setBounds(5, 15, 134, 20);
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
                            lstInvites.setPreferredSize(new java.awt.Dimension(131, 74));
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
                            lstParicipants.setSize(240, 75);
                            lstParicipants.setPreferredSize(new java.awt.Dimension(131, 71));
                        }
                    }
                    {
                        removeParticipantBtn = new JButton();
                        pnlPeers.add(removeParticipantBtn);
                        removeParticipantBtn.setText("-");
                        removeParticipantBtn.setBounds(145, 141, 43, 28);
                        removeParticipantBtn.addActionListener(this);
                        removeParticipantBtn.setActionCommand(Constants.CMD_BTN_REMOVE_PARTICIPANT);
                    }
                    {
                        btnAddInvite = new JButton();
                        pnlPeers.add(btnAddInvite);
                        btnAddInvite.setText("+");
                        btnAddInvite.setBounds(145, 36, 48, 27);
                    }
                    {
                        btnRemoveInvite = new JButton();
                        pnlPeers.add(btnRemoveInvite);
                        btnRemoveInvite.setText("-");
                        btnRemoveInvite.setBounds(196, 36, 42, 27);
                    }
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
        if (cmbEvent.getSelectedItem() != null) {
            String e = (String) cmbEvent.getSelectedItem();

            DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(e);

            if (event != null) {
                return event.retrieveParticipants();
            }
        }
        return new ArrayList<String>();
    }
    
    private List<String> getCommentsForSelectedEvent() {
    	if (cmbEvent != null && cmbEvent.getSelectedItem() != null) {
    		String e = (String) cmbEvent.getSelectedItem();
    		
    		DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(e);
    		
    		if (event != null) {
    			return event.retrieveComments();
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
        } else if(cmd.equals(Constants.CMD_BTN_ADD_COMMENT)) {
        	DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser((String)cmbEvent.getSelectedItem());
            if (event != null) {
            	String comment = JOptionPane.showInputDialog("Type your comment for Event: " + event.getName(), "");
            	if(comment != null && comment.length()>0) {
            		event.retrieveComments().add(ControllerReference.getInstance().getUser().getName() + ": " + comment);
            		ControllerReference.getInstance().getGigaSpace().write(event);
            		refresh();
            	}
            }else{
            	JOptionPane.showMessageDialog(this, "No Event selected");
            }
        } else if(cmd.equals(Constants.CMD_BTN_REMOVE_COMMENT)) {
			String selectedComment = (String) commentList.getSelectedValue();
			DoodleEvent selectedEvent = ControllerReference.getInstance().findEventByNameAndUser((String)cmbEvent.getSelectedItem());
			if (selectedComment != null && selectedEvent != null) {
				selectedEvent.removeComment(selectedComment);
				ControllerReference.getInstance().getGigaSpace().write(selectedEvent);
				refresh();
			}else{
            	JOptionPane.showMessageDialog(this, "To delete a comment please select one Event a one Comment from the list");
            }
        }else if(cmd.equals(Constants.CMD_EVENT_COMBO_CHANGED)) {
        	refresh();
        }
    }

    private void updateEvent() {
        String name = (String) this.cmbEvent.getSelectedItem();
        DoodleEvent event = ControllerReference.getInstance().findEventByNameAndUser(name);
        if (event != null) {
            logger.info("updating event with id: " + event.getId());
            try {
                if (event.retrieveParticipants().isEmpty()) {
                    Date start = this.pnlSchedule.getTxtStart().getDate();
                    Date end = this.pnlSchedule.getTxtEnd().getDate();
                    Calendar endCal = Calendar.getInstance();
                    Calendar startCal = Calendar.getInstance();
                    endCal.setTime(end);
                    startCal.setTime(start);

                    if (start.before(end) && (startCal.get(Calendar.HOUR_OF_DAY) < endCal.get(Calendar.HOUR_OF_DAY))) {

                        name = JOptionPane.showInputDialog("Please type in a name for this event.", name);

                        if (name != null && !name.equals("")) {

                            Peer current = ControllerReference.getInstance().getUser();
                            List<DoodleSchedule> schedules = new ArrayList<DoodleSchedule>();
                            for (int d = startCal.get(Calendar.DAY_OF_YEAR); d <= endCal.get(Calendar.DAY_OF_YEAR); d++) {
                                for (int h = startCal.get(Calendar.HOUR_OF_DAY); h < endCal.get(Calendar.HOUR_OF_DAY); h++) {
                                    DoodleSchedule day = new DoodleSchedule(current.getName(), event.getId());
                                    day.setDay(d + "");
                                    day.setHour(h + "");
                                    ControllerReference.getInstance().getGigaSpace().write(day);
                                    schedules.add(day);
                                    for (int i = 0; i < lstInvites.getSelectedValues().length; i++) {
                                        if (lstInvites.getSelectedValues()[i].equals(current)) {
                                            continue;
                                        }
                                        Peer p = (Peer) lstInvites.getSelectedValues()[i];
                                        DoodleSchedule forPeer = new DoodleSchedule(d + "", h + "", p.getName(), event
                                                .getId());
                                        ControllerReference.getInstance().getGigaSpace().write(forPeer);
                                        schedules.add(forPeer);
                                    }
                                }
                            }

                            event = (DoodleEvent) ControllerReference.getInstance().refresh(event);
                            if (event.retrieveParticipants().isEmpty()) {
                                List<String> sIds = new ArrayList<String>();
                                ControllerReference.getInstance().deleteOldSchedules(event.getId());
                                for (DoodleSchedule s : schedules) {
                                    ControllerReference.getInstance().getGigaSpace().write(s);
                                    sIds.add(s.getId());

                                }
                                event.retrieveSchedules().clear();
                                event.retrieveSchedules().addAll(sIds);
                                ControllerReference.getInstance().getGigaSpace().write(event);
                            }

                            ControllerReference.getInstance().getGigaSpace().write(event);
                            this.refresh();
                        } else {
                            logger.warn("event name cannot be empty");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "This event has subscribed peers and cannot be changed!");
                }
            } catch (ParseException e) {
                // TODO something
            }
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

                if (name != null && !name.equals("")) {

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
                                DoodleSchedule forPeer = new DoodleSchedule(d + "", h + "", p.getName(), event.getId());
                                ControllerReference.getInstance().getGigaSpace().write(forPeer);
                                event.retrieveSchedules().add(forPeer.getId());
                            }
                        }
                    }

                    ControllerReference.getInstance().getGigaSpace().write(event);
                    //	TODO wenn ein neues Event erzeugt wird soll man cmbEvent.setSelectedItem(event.getId) aufrufen
                    this.refresh();
                    

                } else {
                    logger.warn("event name cannot be empty");
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

    public void refresh() {
        logger.info("in refresh()");
       
        // update ComboBox
        cmbEvent.setModel(getEventsModel(ControllerReference.getInstance().getUser()));
       
        String selectedEvent = (String) cmbEvent.getSelectedItem();
        
        // Refresh Event - meanwhile couuld one Peer participate to this event
        if (cmbEvent.getSelectedItem() != null) {
            DoodleEvent refreshedEvent = ControllerReference.getInstance().findEventByNameAndUser(
                    cmbEvent.getSelectedItem().toString());
            // refresh Participant List
            if (refreshedEvent != null) {
                lstParicipants.setModel(new DefaultComboBoxModel(refreshedEvent.retrieveParticipants().toArray()));
                // update CommentList
                commentList.setModel(new DefaultComboBoxModel(getCommentsForSelectedEvent().toArray()));
                this.refreshSchedules(refreshedEvent.getId());
            }
        } else {
            // if no event ist in ComboBox the list of Participants must be
            // empty
            logger.info("clearing Participation Box");
            lstParicipants.setModel(new DefaultComboBoxModel(new Object[] {}));
        }

        // update Registered Peers
        lstInvites.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getAllPeers()));

        // update ComboBox
        cmbEvent.setModel(getEventsModel(ControllerReference.getInstance().getUser()));
        
        if(selectedEvent!=null) {
        	cmbEvent.setSelectedItem(selectedEvent);
        }

        btnEdit.setEnabled(this.isUpdateAllowed());
    }

    private void refreshSchedules(String id) {
        DoodleSchedule[] schedules = ControllerReference.getInstance().retrieveSchedules(id);
        
        int minday = Integer.MAX_VALUE;
        int minhour = Integer.MAX_VALUE;
        int maxday = Integer.MIN_VALUE;
        int maxhour = Integer.MIN_VALUE;
        
        for (DoodleSchedule s : schedules) {
            int day = Integer.parseInt(s.getDay());
            int hour = Integer.parseInt(s.getHour());
         
            if (day < minday) {
                minday = day;
            }
            
            if (day > maxday) {
                maxday = day;
            }
            
            if (hour < minhour) {
                minhour = hour;
            }
            
            if (hour > maxhour) {
                maxhour = hour;
            }
        }
        this.pnlSchedule.getTxtStart().setText(minhour+"."+minday);
        this.pnlSchedule.getTxtEnd().setText(maxhour+"."+maxday);
        
    }

}

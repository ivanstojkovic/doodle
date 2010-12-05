package at.tuwien.sbc.feeder.gui.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.DoodleSchedule;
import at.tuwien.sbc.model.Notification;
import at.tuwien.sbc.model.Peer;

import com.gigaspaces.internal.backport.java.util.Arrays;
import com.j_spaces.core.EventIdFactory;

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
public class PeerEventsPanel extends javax.swing.JPanel implements ActionListener, ComponentListener {
    private JPanel pnlSelection;
    private JScrollPane schedulePanel;
    private JButton addCommentButton;
    private JComboBox eventComboBox;
    private JButton rejectBtn;
    private JButton subscribeBtn;
    private JComboBox invitationsCmb;
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
                this.add(getSchedulePanel(), BorderLayout.CENTER);
                this.add(pnlSelection, BorderLayout.NORTH);
                pnlSelection.setPreferredSize(new java.awt.Dimension(400, 74));
                pnlSelection.setLayout(null);
                {
                    ComboBoxModel cmbTypeModel = new DefaultComboBoxModel(new String[] { "Participations",
                            "Invitations" });
                    cmbType = new JComboBox();
                    pnlSelection.add(cmbType);
                    cmbType.setModel(cmbTypeModel);
                    cmbType.setActionCommand("cmbType");
                    cmbType.setBounds(12, 5, 142, 22);
                    cmbType.addActionListener(this);
                }
                {
                    ComboBoxModel invitationsCmbModel = new DefaultComboBoxModel(ControllerReference.getInstance()
                            .getInvitations().toArray());
                    invitationsCmb = new JComboBox();
                    pnlSelection.add(invitationsCmb);
                    invitationsCmb.setModel(invitationsCmbModel);
                    invitationsCmb.setBounds(12, 41, 141, 22);
                    invitationsCmb.addActionListener(this);
                }
                {
                    subscribeBtn = new JButton();
                    pnlSelection.add(subscribeBtn);
                    subscribeBtn.setText("subscribe");
                    subscribeBtn.setBounds(165, 40, 105, 22);
                    subscribeBtn.addActionListener(this);
                    subscribeBtn.setActionCommand(Constants.CMD_BTN_INVITATION_CONFIRM);
                }
                {
                    rejectBtn = new JButton();
                    pnlSelection.add(rejectBtn);
                    rejectBtn.setText("reject");
                    rejectBtn.setBounds(281, 40, 91, 22);
                    rejectBtn.addActionListener(this);
                    rejectBtn.setActionCommand(Constants.CMD_BTN_INVITATION_REJECT);
                }
                {
                    ComboBoxModel eventComboBoxModel = getParticipationEventsModel();
                    eventComboBox = new JComboBox();
                    pnlSelection.add(eventComboBox);
                    eventComboBox.setModel(eventComboBoxModel);
                    eventComboBox.setBounds(13, 41, 140, 19);
                    eventComboBox.setVisible(false);
                }
                {
                    addCommentButton = new JButton();
                    pnlSelection.add(addCommentButton);
                    addCommentButton.setText("add new comment");
                    addCommentButton.setBounds(183, 40, 174, 22);
                    addCommentButton.setVisible(false);
                    addCommentButton.setActionCommand(Constants.CMD_BTN_ADD_COMMENT);
                    addCommentButton.addActionListener(this);
                }
            }

            hideInvitationComponents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("cmbType")) {
            hideInvitationComponents();
            hideParticipationComponents();
            if (cmbType.getSelectedItem().equals("Invitations")) {
                showInvitationComponents();
            } else if (cmbType.getSelectedItem().equals("Participations")) {
                showParticipationComponents();
            }
        } else if (evt.getActionCommand().equals(Constants.CMD_BTN_INVITATION_CONFIRM)) {
            DoodleEvent e = (DoodleEvent) invitationsCmb.getSelectedItem();
            if (e != null) {
                Peer user = ControllerReference.getInstance().getUser();
                e.removeInvitation(user);
                e.addParticipant(user);
                e.setAction("processIt");
                // Because of the Model of Combo Box
                showInvitationComponents();
                updateScheduleInfo();
                ControllerReference.getInstance().getGigaSpace().write(e);
                Notification n = new Notification(e.getOwner(), "Peer " + user.getName() + " subscribed to event "
                        + e.getName());
                ControllerReference.getInstance().getGigaSpace().write(n);
                schedulePanel.getViewport().removeAll();
                refresh();
            }
        } else if (evt.getActionCommand().equals(Constants.CMD_BTN_INVITATION_REJECT)) {
            DoodleEvent e = (DoodleEvent) invitationsCmb.getSelectedItem();
            if (e != null) {
                Peer user = ControllerReference.getInstance().getUser();
                e.removeInvitation(user);
                List<DoodleSchedule> schedules = ControllerReference.getInstance().readSchedulesForEvent(e.getId());
                e.removeSchedules(user, schedules);
                // Because of the Model of Combo Box
                showInvitationComponents();
                ControllerReference.getInstance().deleteOldSchedules(user.getId(), e.getId());
                ControllerReference.getInstance().getGigaSpace().write(e);
                Notification n = new Notification(e.getOwner(), "Peer " + user.getName() + " rejected the event "
                        + e.getName());
                ControllerReference.getInstance().getGigaSpace().write(n);
            }
        } else if (evt.getActionCommand().equals(Constants.CMD_BTN_ADD_COMMENT)) {
            DoodleEvent event = ControllerReference.getInstance().findEventByName(
                    (String) eventComboBox.getSelectedItem());
            if (event != null) {
                String comment = JOptionPane.showInputDialog("Type your comment for Event: " + event.getName(), "");
                if (comment != null && comment.length() > 0) {
                    event.retrieveComments()
                            .add(ControllerReference.getInstance().getUser().getName() + ": " + comment);
                    ControllerReference.getInstance().getGigaSpace().write(event);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No Event selected");
            }
        } else if (evt.getSource() == this.invitationsCmb) {
            if (this.invitationsCmb.getSelectedItem() != null) {
                this.schedulePanel.getViewport().removeAll();
                this.scheduleIntialisieren();
            }
        }
    }

    private void showParticipationComponents() {
        eventComboBox.setVisible(true);
        addCommentButton.setVisible(true);
        eventComboBox.setModel(getParticipationEventsModel());
    }

    private void hideParticipationComponents() {
        eventComboBox.setVisible(false);
        addCommentButton.setVisible(false);
    }

    private void showInvitationComponents() {
        rejectBtn.setVisible(true);
        subscribeBtn.setVisible(true);
        invitationsCmb.setVisible(true);
        invitationsCmb.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getInvitations().toArray()));
        invitationsCmb.setSelectedIndex(-1);
    }

    private void scheduleIntialisieren() {
        System.out.println("INIT");
        DoodleEvent event = (DoodleEvent) ControllerReference.getInstance().refresh(
                (DoodleEvent) invitationsCmb.getSelectedItem());
        System.out.println(event);
        if (event != null) {
            List<DoodleSchedule> schedules = ControllerReference.getInstance().readSchedulesForCurrentUser(event.getId());
            Collections.sort(schedules);
            JPanel pnl = new JPanel();
            pnl.setLayout(this.getLayout(schedules));
            for (DoodleSchedule ds : schedules) {
                System.out.println(ds.toString());
                SchedulePanel sp = new SchedulePanel(ds);
                sp.setVisible(true);
                pnl.add(sp);
            }
            schedulePanel.getViewport().add(pnl);
            //pnl.setPreferredSize(new java.awt.Dimension(66, 223));
            schedulePanel.repaint();
            schedulePanel.updateUI();

        }
    }

    private GridLayout getLayout(List<DoodleSchedule> schedules) {
        Set<String> days = new HashSet<String>();
        Set<String> hours = new HashSet<String>();

        for (DoodleSchedule s : schedules) {
            days.add(s.getDay());
            hours.add(s.getHour());
        }

        return new GridLayout(days.size(), hours.size());
    }

    private void updateScheduleInfo() {
        JPanel userSchedulePanel = (JPanel) schedulePanel.getViewport().getComponentAt(0, 0);
        for (Component c : userSchedulePanel.getComponents()) {
            System.out.println("Changing");
            SchedulePanel sp = (SchedulePanel) c;
            DoodleSchedule ds = sp.getDs();
            ds = (DoodleSchedule) ControllerReference.getInstance().refresh(ds);
            ds.setSelected(sp.getScheduleCheckBox().isSelected());
            ControllerReference.getInstance().getGigaSpace().write(ds);
        }
    }

    private void hideInvitationComponents() {
        rejectBtn.setVisible(false);
        subscribeBtn.setVisible(false);
        invitationsCmb.setVisible(false);
        invitationsCmb.setSelectedIndex(-1);
    }

    public void refresh() {
        this.hideInvitationComponents();
        this.hideParticipationComponents();
        this.cmbType.setSelectedIndex(0);
    }

    private DefaultComboBoxModel getParticipationEventsModel() {
        if (ControllerReference.getInstance().getUser() != null) {
            List<String> names = ControllerReference.getInstance().getEventNamesFromIds(
                    ControllerReference.getInstance().getUser().retrieveEvents());
            return new DefaultComboBoxModel(names.toArray());
        } else {
            return new DefaultComboBoxModel();
        }
    }

    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    public void componentShown(ComponentEvent e) {
        refresh();
    }

    private JScrollPane getSchedulePanel() {
        if (schedulePanel == null) {
            schedulePanel = new JScrollPane();
            schedulePanel.setBounds(12, 67, 376, 58);
            schedulePanel.setPreferredSize(new java.awt.Dimension(88, 202));
        }
        return schedulePanel;
    }
}

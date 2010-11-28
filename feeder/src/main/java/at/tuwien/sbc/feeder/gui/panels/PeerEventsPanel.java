package at.tuwien.sbc.feeder.gui.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.springframework.util.comparator.InvertibleComparator;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.model.DoodleEvent;
import at.tuwien.sbc.model.Peer;

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
public class PeerEventsPanel extends javax.swing.JPanel implements ActionListener {
	private JPanel pnlSelection;
	private JButton rejectBtn;
	private JButton subscribeBtn;
	private JComboBox invitationsCmb;
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
				pnlSelection.setPreferredSize(new java.awt.Dimension(400, 66));
				pnlSelection.setLayout(null);
				{
					ComboBoxModel cmbTypeModel = new DefaultComboBoxModel(new String[] { "Organizer", "Participant", "Invitations" });
					cmbType = new JComboBox();
					pnlSelection.add(cmbType);
					cmbType.setModel(cmbTypeModel);
					cmbType.setActionCommand("cmbType");
					cmbType.setBounds(58, 5, 130, 22);
					cmbType.addActionListener(this);
				}
				{
					btnSelect = new JButton();
					pnlSelection.add(btnSelect);
					btnSelect.setText("Show");
					btnSelect.setActionCommand(Constants.CMD_BTN_SHOW);
					btnSelect.setBounds(208, 6, 56, 22);
					btnSelect.addActionListener(this);
				}
				{
					ComboBoxModel invitationsCmbModel = new DefaultComboBoxModel(ControllerReference.getInstance().getInvitations()
							.toArray());
					invitationsCmb = new JComboBox();
					pnlSelection.add(invitationsCmb);
					invitationsCmb.setModel(invitationsCmbModel);
					invitationsCmb.setBounds(23, 39, 165, 22);
				}
				{
					subscribeBtn = new JButton();
					pnlSelection.add(subscribeBtn);
					subscribeBtn.setText("subscribe");
					subscribeBtn.setBounds(208, 40, 75, 22);
					subscribeBtn.addActionListener(this);
					subscribeBtn.setActionCommand(Constants.CMD_BTN_INVITATION_CONFIRM);
				}
				{
					rejectBtn = new JButton();
					pnlSelection.add(rejectBtn);
					rejectBtn.setText("reject");
					rejectBtn.setBounds(294, 40, 66, 22);
					rejectBtn.addActionListener(this);
					rejectBtn.setActionCommand(Constants.CMD_BTN_INVITATION_REJECT);
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

			hideInvitationComponents();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals("cmbType")) {
			if (cmbType.getSelectedItem().equals("Invitations")) {
				showInvitationComponents();
			} else {
				hideInvitationComponents();
			}
		} else if (evt.getActionCommand().equals(Constants.CMD_BTN_SHOW)) {
			if (cmbType.getSelectedItem().equals("Organizer")) {
				this.txtArea.setText(this.retrieveOrganizedEvents());
			} else if (cmbType.getSelectedItem().equals("Participant")) {
				this.txtArea.setText(this.retrieveParticipantEvents());
			} else if (cmbType.getSelectedItem().equals("Invitations")) {
				this.txtArea.setText(this.retrieveInvitationsString());
			} else {
				this.txtArea.setText("An Error occurred");
			}
		} else if (evt.getActionCommand().equals(Constants.CMD_BTN_INVITATION_CONFIRM)) {
			DoodleEvent e = (DoodleEvent) invitationsCmb.getSelectedItem();
			if(e!=null) {
				Peer user = ControllerReference.getInstance().getUser();
				e.removeInvitation(user);
				e.addParticipant(user);
				//TODO do we need this.. Event processor shall be responsible
				user.getEvents().add(e.getId()); 
				ControllerReference.getInstance().updateObject(e);
				ControllerReference.getInstance().updateObject(user);
				//	Because of the Model of Combo Box
				showInvitationComponents();
			}
		} else if (evt.getActionCommand().equals(Constants.CMD_BTN_INVITATION_REJECT)) {
			DoodleEvent e = (DoodleEvent) invitationsCmb.getSelectedItem();
			if(e!=null) {
				Peer user = ControllerReference.getInstance().getUser();
				e.removeInvitation(user);
				ControllerReference.getInstance().updateObject(e);
//				Because of the Model of Combo Box
				showInvitationComponents();
			}
		}
	}

	private String retrieveParticipantEvents() {
		StringBuffer buf = new StringBuffer(42);
		// TODO be careful what happens if a new event comes in the space
		// for this user.. the field in the controller has to be updated..
		Peer you = ControllerReference.getInstance().getUser();

		if (you.getEvents().isEmpty()) {
			buf.append("You are not participating in any event");
		} else {
			buf.append("Participation Events:");
			//TODO retrieve events
			for (String e : you.getEvents()) {
				buf.append("\n");
				buf.append(e);
			}
		}

		return buf.toString();
	}

	private String retrieveInvitationsString() {
		StringBuffer buf = new StringBuffer(42);

		List<DoodleEvent> eventsInvitedTo = ControllerReference.getInstance().getInvitations();

		if (eventsInvitedTo.size() == 0) {
			buf.append("You have no new invitations");
		} else {
			buf.append("Invitations:");
			for (DoodleEvent e : eventsInvitedTo) {
				buf.append("\n");
				buf.append(e.toString());
			}
		}

		return buf.toString();
	}

	private String retrieveOrganizedEvents() {
		StringBuffer buf = new StringBuffer(42);
		// TODO be careful what happens if a new event comes in the space
		// for this user.. the field in the controller has to be updated..
		Peer you = ControllerReference.getInstance().getUser();

		if (you.getOrganized().isEmpty()) {
			buf.append("You have not organized any event");
		} else {
			buf.append("Participation Events:");
			//TODO retrieve Events
			for (String e : you.getOrganized()) {
				buf.append("\n");
				buf.append(e);
			}
		}

		return buf.toString();
	}

	private void showInvitationComponents() {
		rejectBtn.setVisible(true);
		subscribeBtn.setVisible(true);
		invitationsCmb.setVisible(true);
		invitationsCmb.setModel(new DefaultComboBoxModel(ControllerReference.getInstance().getInvitations().toArray()));
	}

	private void hideInvitationComponents() {
		rejectBtn.setVisible(false);
		subscribeBtn.setVisible(false);
		invitationsCmb.setVisible(false);
	}

}

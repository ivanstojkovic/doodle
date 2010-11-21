package at.tuwien.sbc.feeder.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.openspaces.core.EntryAlreadyInSpaceException;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.feeder.interfaces.LoginCallback;
import at.tuwien.sbc.model.Peer;

import com.j_spaces.core.LeaseContext;
import com.j_spaces.core.client.UpdateModifiers;

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
public class LoginFrame extends javax.swing.JFrame implements ActionListener {

	private JButton registerButton;
	private JButton LoginButton;
	private JPasswordField txtPass;
	private JLabel jLabel2;
	private JButton jButton1;
	private JPanel pnlButtons;
	private JTextField txtUser;
	private JLabel jLabel1;
	private JPanel jPanel1;

	private LoginCallback call;

	public LoginFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			{
				jPanel1 = new JPanel();
				GridLayout jPanel1Layout = new GridLayout(2, 2);
				jPanel1Layout.setColumns(1);
				jPanel1Layout.setHgap(5);
				jPanel1Layout.setVgap(5);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				jPanel1.setPreferredSize(new java.awt.Dimension(384, 53));
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("username: ");
				}
				{
					txtUser = new JTextField();
					jPanel1.add(txtUser);
					txtUser.setToolTipText("Type in your username");
				}
				{
					jLabel2 = new JLabel();
					jPanel1.add(jLabel2);
					jLabel2.setText("password: ");
				}
				{
					txtPass = new JPasswordField();
					jPanel1.add(txtPass);
					txtPass.setToolTipText("Type in your password");
				}
			}
			{
				pnlButtons = new JPanel();
				getContentPane().add(pnlButtons, BorderLayout.SOUTH);
				{
					LoginButton = new JButton();
					pnlButtons.add(LoginButton);
					LoginButton.setText("Login");
					LoginButton.setActionCommand(Constants.CMD_BTN_LOGIN);
					LoginButton.addActionListener(this);
				}
				{
					registerButton = new JButton();
					pnlButtons.add(registerButton);
					registerButton.setText("Register");
					registerButton.setPreferredSize(new java.awt.Dimension(102, 25));
					registerButton.addActionListener(this);
					registerButton.setActionCommand(Constants.CMD_BTN_REGISTER);
				}
				{
					jButton1 = new JButton();
					pnlButtons.add(jButton1);
					jButton1.setText("Cancel");
					jButton1.setActionCommand(Constants.CMD_BTN_CANCEL);
					jButton1.addActionListener(this);
				}
			}
			this.setSize(300, 130);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void processWindowEvent(WindowEvent evt) {
		if (evt.getID() == WindowEvent.WINDOW_CLOSING) {
			this.call.callback(false);
			this.dispose();
		}
	}

	public void actionPerformed(ActionEvent evt) {
		if (evt.getActionCommand().equals(Constants.CMD_BTN_REGISTER)) {

			if (txtUser.getText().equals("") || String.valueOf(txtPass.getPassword()).equals("")) {
				JOptionPane.showMessageDialog(this, "Username and/or password are empty");
			} else {
				
				try {
					Peer reg = ControllerReference.getInstance().register(txtUser.getText(), String.valueOf(txtPass.getPassword()));
					// success
					if (reg == null) {
						JOptionPane.showMessageDialog(this, "Congrats! You are successfully registered");
					}
				} catch (EntryAlreadyInSpaceException e) {
					JOptionPane.showMessageDialog(this, "This username is already taken!");
				}
			}
		}

		if (evt.getActionCommand().equals(Constants.CMD_BTN_LOGIN)) {
			Peer p = ControllerReference.getInstance().login(txtUser.getText(), String.valueOf(txtPass.getPassword()));
			
			if (p == null) {
				// NICHT EINGELOGGT => nicht registriert
				JOptionPane.showMessageDialog(this, "You are not registered! Please register");
			} else {
				if (this.call != null) {
					this.call.callback(true);
				} else {
					System.err.println("An error occurred, no callback defined");
				}

				this.setVisible(false);
				this.dispose();
			}
		}
		
		if (evt.getActionCommand().equals(Constants.CMD_BTN_CANCEL)) {
			this.call.callback(false);
			this.setVisible(false);
			this.setTitle("Doodle - Login/Register");
			this.dispose();
		}

	}

	public void setCall(LoginCallback call) {
		this.call = call;
	}

	public LoginCallback getCall() {
		return call;
	}
}

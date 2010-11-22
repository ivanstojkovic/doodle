package at.tuwien.sbc.feeder.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import at.tuwien.sbc.feeder.ControllerReference;
import at.tuwien.sbc.feeder.common.Constants;
import at.tuwien.sbc.feeder.interfaces.LoginCallback;
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
public class MainFrame extends javax.swing.JFrame implements ActionListener, LoginCallback {
    
    private JTabbedPane tbPnl;
    private JPanel pnlOverview;
    private SearchPanel pnlSearch;
    private JLabel lblGreet;
    private JPanel pnlGreet;
    private JMenuItem itmLogout;
    private JMenuItem itmHelp;
    private JMenuItem itmAbout;
    private JMenuItem itmQuit;
    private JMenuItem itmLogReg;
    private JMenu jMenu2;
    private JMenu jMenu1;
    private JMenuBar menuBar;
    private JPanel pnlMain;
    private JLabel lbl2;
    private JLabel lbl;
    private JPanel pnlOrg;
    
    private TabbedPanel tabs;
    
    public MainFrame() {
        super();
        initGUI();
    }
    
    protected void processWindowEvent(WindowEvent evt) {
        if (evt.getID() == WindowEvent.WINDOW_CLOSING) {
            this.dispose();
            System.exit(0);
        }
    }
    
    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            // this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            getContentPane().setLayout(thisLayout);
            this.setTitle("Doodle");
            ImageIcon cal = new ImageIcon(ClassLoader.getSystemResource("images/cal.png"));
            this.setIconImage(cal.getImage());
            {
                pnlGreet = new JPanel();
                getContentPane().add(pnlGreet, BorderLayout.NORTH);
                pnlGreet.setLayout(null);
                pnlGreet.setPreferredSize(new java.awt.Dimension(725, 40));
                {
                    lblGreet = new JLabel();
                    pnlGreet.add(lblGreet);
                    lblGreet.setText("You are currently not logged in");
                    lblGreet.setBounds(4, 6, 223, 15);
                }
                {
                    pnlSearch = new SearchPanel();
                    pnlGreet.add(pnlSearch);
                    pnlSearch.setBounds(427, 1, 298, 33);
                    pnlSearch.getTxtSearch().setText("peer name");
                }
            }
            {
                menuBar = new JMenuBar();
                setJMenuBar(menuBar);
                {
                    jMenu1 = new JMenu();
                    menuBar.add(jMenu1);
                    jMenu1.setText("Doodle");
                    {
                        itmLogReg = new JMenuItem();
                        jMenu1.add(itmLogReg);
                        itmLogReg.setText("Login/Register");
                        itmLogReg.setActionCommand(Constants.CMD_BTN_LOGIN);
                        itmLogReg.addActionListener(this);
                    }
                    {
                        itmLogout = new JMenuItem();
                        jMenu1.add(itmLogout);
                        itmLogout.setText("Logout");
                        itmLogout.setEnabled(false);
                        itmLogout.setActionCommand(Constants.CMD_MENU_LOGOUT);
                        itmLogout.addActionListener(this);
                    }
                    {
                        itmQuit = new JMenuItem();
                        jMenu1.add(itmQuit);
                        itmQuit.setText("Quit");
                        itmQuit.setActionCommand(Constants.CMD_MENU_QUIT);
                        itmQuit.addActionListener(this);
                    }
                }
                {
                    jMenu2 = new JMenu();
                    menuBar.add(jMenu2);
                    jMenu2.setText("Help");
                    {
                        itmAbout = new JMenuItem();
                        jMenu2.add(itmAbout);
                        itmAbout.setText("About");
                        itmAbout.setActionCommand(Constants.CMD_MENU_ABOUT);
                        itmAbout.addActionListener(this);
                    }
                    {
                        itmHelp = new JMenuItem();
                        jMenu2.add(itmHelp);
                        itmHelp.setText("Help");
                        itmHelp.setActionCommand(Constants.CMD_MENU_HELP);
                        itmHelp.addActionListener(this);
                    }
                }
            }
            {
                this.tabs = new TabbedPanel();
                this.tabs.enableTab(-1, false);
                getContentPane().add(this.tabs, BorderLayout.CENTER);
                tabs.setPreferredSize(new java.awt.Dimension(725, 357));
            }
            
            pack();
            this.setSize(735, 432);
        } catch (Exception e) {
            // add your error handling code here
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        
        if (cmd.equals(Constants.CMD_BTN_LOGIN)) {
            this.itmLogReg.setEnabled(false);
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
            frame.setLocationRelativeTo(this);
            frame.setCall(this);
        }
        
        if (cmd.equals(Constants.CMD_MENU_LOGOUT)) {
            this.callback(false);
        }
        
        if (cmd.equals(Constants.CMD_MENU_QUIT)) {
            this.dispose();
            System.exit(0);
        }
        
        if (cmd.equals(Constants.CMD_MENU_ABOUT)) {
            JOptionPane.showMessageDialog(this, "Doodle - by Ivan Stojkovic and Petar Petrov");
        }
        
        if (cmd.equals(Constants.CMD_MENU_HELP)) {
            JOptionPane.showMessageDialog(this, "No help! Abandon ship...");
        }
    }
    
    public void callback(boolean loggedIn) {
        if (loggedIn) {
            this.itmLogout.setEnabled(true);
            this.itmLogReg.setEnabled(false);
            this.tabs.enableTab(-1, true);
            
            Peer user = ControllerReference.getInstance().getUser();
            if (user == null) {
                System.err.println("An error must have occurred");
                this.lblGreet.setText("Welcome");
            } else {
                this.lblGreet.setText("Welcome " + user.getName());
            }
            
        } else {
            ControllerReference.getInstance().logout();
            this.itmLogout.setEnabled(false);
            this.itmLogReg.setEnabled(true);
            this.tabs.enableTab(-1, false);
            this.lblGreet.setText("You are currently not logged in!");
        }
        
    }
    
}

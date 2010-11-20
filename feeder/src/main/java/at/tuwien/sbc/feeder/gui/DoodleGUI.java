package at.tuwien.sbc.feeder.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.openspaces.core.EntryAlreadyInSpaceException;
import org.openspaces.core.GigaSpace;

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
public class DoodleGUI extends javax.swing.JFrame implements ActionListener {
    
    private JMenuItem helpMenuItem;
    private JMenu jMenu5;
    private JButton registerButton;
    private JButton LoginButton;
    private JTextField jTextField2;
    private JLabel jLabel2;
    private JTextField jTextField1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JMenuItem deleteMenuItem;
    private JSeparator jSeparator1;
    private JMenuItem pasteMenuItem;
    private JMenuItem copyMenuItem;
    private JMenuItem cutMenuItem;
    private JMenu jMenu4;
    private JMenuItem exitMenuItem;
    private JSeparator jSeparator2;
    private JMenuItem closeFileMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem openFileMenuItem;
    private JMenuItem newFileMenuItem;
    private JMenu jMenu3;
    private JMenuBar jMenuBar1;
    
    private GigaSpace gigaSpace;
    
    public DoodleGUI(GigaSpace gigaSpace) {
        super();
        initGUI();
        this.gigaSpace = gigaSpace;
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
                jPanel1.setPreferredSize(new java.awt.Dimension(400, 83));
                {
                    jLabel1 = new JLabel();
                    jPanel1.add(jLabel1);
                    jLabel1.setText("username: ");
                }
                {
                    jTextField1 = new JTextField();
                    jPanel1.add(jTextField1);
                }
                {
                    jLabel2 = new JLabel();
                    jPanel1.add(jLabel2);
                    jLabel2.setText("password: ");
                }
                {
                    jTextField2 = new JTextField();
                    jPanel1.add(jTextField2);
                }
            }
            {
                LoginButton = new JButton();
                getContentPane().add(LoginButton, BorderLayout.CENTER);
                LoginButton.setText("Login");
                LoginButton.setActionCommand("login");
                LoginButton.addActionListener(this);
            }
            {
                registerButton = new JButton();
                getContentPane().add(registerButton, BorderLayout.WEST);
                registerButton.setText("register");
                registerButton.setPreferredSize(new java.awt.Dimension(199, 49));
                registerButton.addActionListener(this);
                registerButton.setActionCommand("register");
            }
            this.setSize(400, 217);
            {
                jMenuBar1 = new JMenuBar();
                setJMenuBar(jMenuBar1);
                {
                    jMenu3 = new JMenu();
                    jMenuBar1.add(jMenu3);
                    jMenu3.setText("File");
                    {
                        newFileMenuItem = new JMenuItem();
                        jMenu3.add(newFileMenuItem);
                        newFileMenuItem.setText("New");
                    }
                    {
                        openFileMenuItem = new JMenuItem();
                        jMenu3.add(openFileMenuItem);
                        openFileMenuItem.setText("Open");
                    }
                    {
                        saveMenuItem = new JMenuItem();
                        jMenu3.add(saveMenuItem);
                        saveMenuItem.setText("Save");
                    }
                    {
                        saveAsMenuItem = new JMenuItem();
                        jMenu3.add(saveAsMenuItem);
                        saveAsMenuItem.setText("Save As ...");
                    }
                    {
                        closeFileMenuItem = new JMenuItem();
                        jMenu3.add(closeFileMenuItem);
                        closeFileMenuItem.setText("Close");
                    }
                    {
                        jSeparator2 = new JSeparator();
                        jMenu3.add(jSeparator2);
                    }
                    {
                        exitMenuItem = new JMenuItem();
                        jMenu3.add(exitMenuItem);
                        exitMenuItem.setText("Exit");
                    }
                }
                {
                    jMenu4 = new JMenu();
                    jMenuBar1.add(jMenu4);
                    jMenu4.setText("Edit");
                    {
                        cutMenuItem = new JMenuItem();
                        jMenu4.add(cutMenuItem);
                        cutMenuItem.setText("Cut");
                    }
                    {
                        copyMenuItem = new JMenuItem();
                        jMenu4.add(copyMenuItem);
                        copyMenuItem.setText("Copy");
                    }
                    {
                        pasteMenuItem = new JMenuItem();
                        jMenu4.add(pasteMenuItem);
                        pasteMenuItem.setText("Paste");
                    }
                    {
                        jSeparator1 = new JSeparator();
                        jMenu4.add(jSeparator1);
                    }
                    {
                        deleteMenuItem = new JMenuItem();
                        jMenu4.add(deleteMenuItem);
                        deleteMenuItem.setText("Delete");
                    }
                }
                {
                    jMenu5 = new JMenu();
                    jMenuBar1.add(jMenu5);
                    jMenu5.setText("Help");
                    {
                        helpMenuItem = new JMenuItem();
                        jMenu5.add(helpMenuItem);
                        helpMenuItem.setText("Help");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getActionCommand().equals("register")) {
            Peer newPeer = new Peer(jTextField1.getText(), jTextField2.getText(), "register");
            try {
                LeaseContext<Peer> ctx = gigaSpace.write(newPeer, 1000 * 60 * 60, 5000, UpdateModifiers.WRITE_ONLY);
                Peer reg = ctx.getObject();
                // success
                if (reg == null) {
                    JOptionPane.showMessageDialog(this, "Congrats! You are successfully registered");
                    // TODO switch panel, show...
                }
            } catch (EntryAlreadyInSpaceException e) {
                JOptionPane.showMessageDialog(this, "This username is already taken!");
            }
        }
        
        if (arg0.getActionCommand().equals("login")) {
            Peer p = gigaSpace.readIfExists(new Peer(jTextField1.getText(), jTextField2.getText(), "login"));
            if (p == null) {
                // NICHT EINGELOGGT => nicht registriert
                JOptionPane.showMessageDialog(this, "You are not registered!");
            } else {
                JOptionPane.showMessageDialog(this, "We are screwed");
            }
        }
        
    }
    
    public GigaSpace getGigaSpace() {
        return gigaSpace;
    }
    
}

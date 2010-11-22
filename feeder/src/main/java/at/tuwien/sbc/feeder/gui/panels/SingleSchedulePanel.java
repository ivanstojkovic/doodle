package at.tuwien.sbc.feeder.gui.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;

import at.tuwien.sbc.feeder.gui.components.DateTextField;

public class SingleSchedulePanel extends javax.swing.JPanel {
    private JLabel lblStart;
    private JLabel lblEnd;
    private DateTextField txtEnd;
    private DateTextField txtStart;

    public SingleSchedulePanel() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            GridLayout thisLayout = new GridLayout(2, 2);
            thisLayout.setColumns(1);
            thisLayout.setHgap(5);
            thisLayout.setVgap(5);
            this.setLayout(thisLayout);
            this.setPreferredSize(new java.awt.Dimension(292, 45));
            this.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
            {
                lblStart = new JLabel();
                this.add(lblStart);
                lblStart.setText("Start:");
                lblStart.setPreferredSize(new java.awt.Dimension(37, 36));
            }
            {
                lblEnd = new JLabel();
                this.add(lblEnd);
                lblEnd.setText("End:");
            }
            {
                txtStart = new DateTextField();
                this.add(txtStart);
                txtStart.setPreferredSize(new java.awt.Dimension(33, 36));
            }
            {
                txtEnd = new DateTextField();
                this.add(txtEnd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

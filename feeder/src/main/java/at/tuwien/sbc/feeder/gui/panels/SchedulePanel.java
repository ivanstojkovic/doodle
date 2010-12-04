package at.tuwien.sbc.feeder.gui.panels;

import java.awt.Dimension;
import javax.swing.JCheckBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;

import at.tuwien.sbc.model.DoodleSchedule;

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
public class SchedulePanel extends javax.swing.JPanel {
	private JCheckBox scheduleCheckBox;
	private JLabel scheduleLabel;
	private DoodleSchedule ds;

	public SchedulePanel(DoodleSchedule ds) {
		super();
		this.ds = ds;
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(100, 30));
			{
				scheduleCheckBox = new JCheckBox();
				this.add(scheduleCheckBox);
			}
			{
				scheduleLabel = new JLabel(ds.getDay() + "." + ds.getHour());
				this.add(scheduleLabel);
				scheduleLabel.setPreferredSize(new java.awt.Dimension(70, 20));
				scheduleLabel.setSize(new java.awt.Dimension(70, 20));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DoodleSchedule getDs() {
		return ds;
	}

	public void setDs(DoodleSchedule ds) {
		this.ds = ds;
	}

	public JCheckBox getScheduleCheckBox() {
		return scheduleCheckBox;
	}

}

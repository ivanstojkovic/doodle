package at.tuwien.sbc.feeder.gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPanel extends JPanel {

	private JTabbedPane tabs;

	public TabbedPanel() {
		super(new GridLayout(1, 1));

		tabs = new JTabbedPane();
		ImageIcon icon = createImageIcon("images/middle.gif");

		JComponent panel1 = makeTextPanel("Panel #1");
		this.tabs.addTab("Organization", icon, panel1, "Does nothing");
		this.tabs.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = makeTextPanel("Panel #2");
		this.tabs.addTab("Overview", icon, panel2, "Does twice as much nothing");
		this.tabs.setMnemonicAt(1, KeyEvent.VK_2);

		// Add the tabbed pane to this panel.
		add(this.tabs);

		// The following line enables to use scrolling tabs.
		this.tabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public void enableTab(int tab, boolean enable) {
		if (tab == -1) {
			this.setEnabled(enable);
		} else {
			Component t = this.tabs.getComponentAt(tab);
			t.setEnabled(enable);
		}
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = TabbedPanel.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
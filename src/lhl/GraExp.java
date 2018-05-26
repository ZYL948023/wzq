package lhl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraExp extends JFrame {
	CInstead c1 = new CInstead();
	Container c;

	public GraExp() {
		setContentPane(c1);
		c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
	

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 300));
		show();
	}

}


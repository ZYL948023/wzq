package lhl;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class CInstead extends JPanel {
	ImageIcon icon;
	Image img;

	public CInstead() {
		
		icon = new ImageIcon(GraExp.class.getClassLoader().getResource("img/wzq.jpg"));
		img = icon.getImage();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
}
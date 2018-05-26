package lxw;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Queren extends JFrame{
	public Queren(){
		JLabel jl = new JLabel("是否确认退出",JLabel.CENTER);
		jl.setFont(new Font("微软雅黑",Font.PLAIN,20));
		JPanel jp = new JPanel();
		JButton jcShi = new JButton("是");
		JButton jcFou = new JButton("否");
		add(jl);
		jp.add(jcShi);
		jp.add(jcFou);
		jcShi.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.exit(0);
			}
		});
		jcFou.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dispose();
			}
		});
		add(jp,BorderLayout.SOUTH);
		setLocation(300,300);
		setSize(300,150);
		setVisible(true);
	}

}
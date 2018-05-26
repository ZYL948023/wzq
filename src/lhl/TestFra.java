package lhl;
import java.awt.*;
import javax.swing.*;

import lxw.FiveChessJFrame;

import java.awt.event.*;


public class  TestFra extends JFrame{
	CInstead c1 = new CInstead();
	Container c;
	
	
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JPanel jpCenter;
	private JLabel jla;
	private JLabel jla1;
	
	
	public TestFra (){
		setContentPane(c1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		c = getContentPane();
		//创建标题，三个游戏按钮，一个面板
		btn1 = new JButton("本地对战");
		btn1.setBackground(Color.YELLOW);
		btn2 = new JButton("网络对战");
		btn2.setBackground(Color.YELLOW);
		btn3 = new JButton("退出游戏");
		btn3.setBackground(Color.YELLOW);
		setTitle("欢迎来到五子棋");
		jla = new JLabel("五子棋",JLabel.CENTER);
		jla1 = new JLabel("帮  助",JLabel.LEFT);
		jla.setFont(new Font("宋体",Font.BOLD,60));
		jla.setForeground(Color.BLUE);
		jpCenter = new JPanel();
	
	

	//整体边界布局，加入标题和面板
     c.setLayout(new BorderLayout());
	 c.add(jla,BorderLayout.NORTH);
	 c.add(jpCenter,BorderLayout.CENTER);
	 c.add(jla1,BorderLayout.SOUTH);
	 

     //中间面板网格布局，加入三个按钮
	 jpCenter.setLayout(new GridLayout(3,1,10,0));
	 jpCenter.setSize(100,150);
	 jpCenter.add(btn1);
	 jpCenter.add(btn2);
	 jpCenter.add(btn3);
	 btn1.setFont(new Font("宋体",Font.BOLD,20));
	 btn2.setFont(new Font("宋体",Font.BOLD,20));
	 btn3.setFont(new Font("宋体",Font.BOLD,20));
	 btn1.setForeground(Color.GRAY);
	 btn2.setForeground(Color.GRAY);
	 btn3.setForeground(Color.GRAY);
	 c.add(new JLabel("                                                 "),BorderLayout.EAST);
	 c.add(new JLabel("                                                 "),BorderLayout.WEST);
	
	 
     //鼠标事件
	 btn1.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				btn1.setFont(new Font("微软黑体",Font.BOLD,30));
			}
			public void mouseExited(MouseEvent e){
				btn1.setFont(new Font("微软黑体",Font.BOLD,20));
			}
			public void mouseClicked(MouseEvent e) {
				//添加点击人机对战所触发的事件
				new FiveChessJFrame();
			}
		});
	 
	 btn2.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				btn2.setFont(new Font("微软黑体",Font.BOLD,30));
			}
			public void mouseExited(MouseEvent e){
				btn2.setFont(new Font("微软黑体",Font.BOLD,20));
			}
			public void mouseClicked(MouseEvent e) {
				//点击网络对战
				new wzq.ModeSelectWin();
			}
		});
	 
		btn3.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e){
				btn3.setFont(new Font("微软黑体",Font.BOLD,30));
			}
			public void mouseExited(MouseEvent e){
				btn3.setFont(new Font("微软黑体",Font.BOLD,20));
			}
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"确定退出游戏吗？");
				setVisible(false);
			}
		});
		
		jla1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null,"先手要攻，后手要守，以攻为守，以守待攻。"
						+ "\n攻守转换，慎思变化，先行争夺，地破天惊。守取外势，攻聚内力，八卦易守，成角易攻。"
						+ "\n阻断分隔，稳如泰山，不思争先，胜如登天。初盘争二，终局抢三，留三不冲，变化万千。"
						+ "\n多个先手，细算次先，五子要点，次序在前。斜线为阴，直线为阳，阴阳结合，防不胜防。"
						+ "\n连三连四，易见为明，跳三跳四，暗剑深藏。己落一子，敌增一兵，攻其要点，守其必争。"
						+ "\n势已形成，败即降临，五子精华，一子输赢。");
			}
		});
		

	//窗体大小，窗体出现的位置
	setSize(500,450);
	setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-250,
			Toolkit.getDefaultToolkit().getScreenSize().height/2-225, 
			500, 450);
	setVisible(true);
	}

  public static void main(String[] args) {
    new TestFra();
  }
}

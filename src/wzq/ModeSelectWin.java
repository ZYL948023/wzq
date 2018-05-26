package wzq;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ModeSelectWin {
	private JFrame frame;
	private Container container;
	private JButton btnHost;
	private JButton btnAdder;

	public ModeSelectWin() {
		init();
		buildWin();
	}
	
	/**
	 * 添加控件
	 */
	public void addC() {
		container.add(btnHost);
		container.add(btnAdder);
	}
	
	/**
	 * 初始化控件
	 */
	public void init() {
		frame = new JFrame("模式选择");
		container = frame.getContentPane();
		
		btnHost=new JButton("创建房间");
		btnHost.setBounds(200, 50, 100, 50);
		btnHost.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CreateRoom();
			}
		});
		
		btnAdder=new JButton("加入房间");
		btnAdder.setBounds(200, 140, 100, 50);
		btnAdder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddRoom();
			}
		});
	}
	/**
	 * 创建窗口
	 */
	public void buildWin() {
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-250,
				Toolkit.getDefaultToolkit().getScreenSize().height/2-150,
				500, 300);
		frame.setLayout(null);
		
		addC();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
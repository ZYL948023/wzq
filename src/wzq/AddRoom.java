package wzq;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddRoom{
	
	private JFrame frame;
	private Container container;
	private JLabel label;
	private JLabel lbConnecInfo;
	private JTextField edtIP;
	private JButton btnConnection;
	private String strIP;
	
	private Socket socket;
	private IntnetGame game;
	private IntnetGameWin gameWin;
	
	public AddRoom() {
		init();
		buildWin();
	}
	
	public void init() {
		frame = new JFrame("加入房间");
		container = frame.getContentPane();
		label = new JLabel("对方IP:");
		label.setBounds(120, 50, 100, 30);
		
		lbConnecInfo = new JLabel("连接信息");
		lbConnecInfo.setBounds(200, 150, 200, 50);
		
		
		edtIP = new JTextField();
		edtIP.setBounds(200, 50, 150, 30);
		
		
		btnConnection = new JButton("连接");
		btnConnection.setBounds(150, 100, 150, 30);
		
		
		btnConnection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBtnAction();
			}
		});
	}
	
	public void buildWin() {
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-250,
				Toolkit.getDefaultToolkit().getScreenSize().height/2-150,
				500, 300);
		frame.setLayout(null);
		
		addC();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void addC() {
		container.add(label);
		container.add(edtIP);
		container.add(btnConnection);
		container.add(lbConnecInfo);
	}
	
	/**
	 * 连接按钮事件
	 */
	private void setBtnAction() {
		lbConnecInfo.setText("尝试连接");
		strIP = edtIP.getText();
		getConnecion();
	}
	
	private void createGameWin(String strIP) {
		game=new IntnetGame();
		gameWin=new IntnetGameWin(2,game,strIP);
	}

	//确认连接，创建游戏界面
	private void getConnecion() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					socket = new Socket(strIP, 19961);
					lbConnecInfo.setText("连接成功");
					String strIP=socket.getInetAddress().getHostAddress();
					createGameWin(strIP);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
}

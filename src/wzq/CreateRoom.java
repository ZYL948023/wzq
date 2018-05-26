package wzq;

import java.awt.Container;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CreateRoom {
	
	private IntnetGame game;
	private JFrame frame;
	private JLabel lbIP;
	private JLabel lbConnectInfo;
	private Container container;
	
	private ServerSocket ss;
	private Socket s;
	
	public CreateRoom() {
		init();
		buildWin();
	}
	
	public void init() {
		frame = new JFrame("创建房间");
		container = frame.getContentPane();
		lbIP = new JLabel("IP:");
		lbIP.setBounds(100, 50, 200, 50);
		
		lbConnectInfo = new JLabel("等待链接");
		lbConnectInfo.setBounds(100, 140, 200, 50);
	
		//获取本机IP
		try {
			String strIP=InetAddress.getLocalHost().getHostAddress();
			lbIP.setText("IP:"+strIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void buildWin() {
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-250,
				Toolkit.getDefaultToolkit().getScreenSize().height/2-150,
				500, 300);
		frame.setLayout(null);
		
		addC();
		getConnection();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}

	private void addC() {
		container.add(lbIP);
		container.add(lbConnectInfo);
	}
	
	private void createGameWin(String strIP) {
		game=new IntnetGame();
		new IntnetGameWin(1, game,strIP);
	}
	
	/**
	 * 创建网络连接
	 */
	public void getConnection() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ss = new ServerSocket(19961);
					s = ss.accept();
					lbConnectInfo.setText("连接完成");
					String strIP=s.getInetAddress().getHostAddress();
					createGameWin(strIP);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
	}
}

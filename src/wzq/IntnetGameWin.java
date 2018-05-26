package wzq;

import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class IntnetGameWin {
	
	IntnetGame game;
	
	
	
	private JLabel label;
	private JLabel lbMyImg;
	private JLabel lbImg;
	
	private String strIP;//对方玩家的IP地址
	private int playerID;
	private int size=40;//棋盘按钮大小
	private JFrame oene;
	private Container container;
	private JButton[][] datas=new JButton[15][15];

	private DatagramSocket socket;
	private DatagramPacket packet;



	private JLabel lbMyInfo;
	
	public IntnetGameWin(int playerID,IntnetGame game,String strIP) {
		this.playerID=playerID;
		this.game=game;
		this.strIP=strIP;
		init();
		buildWin();
		
		
		if (playerID==1) {
			oene.setTitle("玩家1");
		}else{
			oene.setTitle("玩家2");
		}
		getUDPConnect();
	}
	
	/**
	 * create game win
	 */
	public void  buildWin() {
		//oene.setSize(760, 650);
		oene.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-380,
				Toolkit.getDefaultToolkit().getScreenSize().height/2-325,
				760, 650);
		oene.setLayout(null);
		
		addControl();
		
		oene.setVisible(true);
		oene.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//新建线程反复刷新数据
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (game.winnerID==0) {
					getUpdata();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				getUpdata();
			}
		}).start();
	}
	
	/**
	 * add control
	 */
	private void addControl() {
		//add datas
		for(int x=0;x<15;x++){
			for(int y=0;y<15;y++){
				datas[x][y].setBounds(x*size, y*size, size, size);
				container.add(datas[x][y]);
			}
		}
		
	}
	
	/**
	 * init
	 */
	private void init() {
		oene = new JFrame();
		container = oene.getContentPane();
		label = new JLabel();
		label.setBounds(618, 100, 100, 60);
		container.add(label);
		
		lbMyInfo = new JLabel("我是");
		lbMyInfo.setBounds(610, 200, 40, 40);
		container.add(lbMyInfo);
		lbMyImg=new JLabel("本地图例");
		lbMyImg.setBounds(680, 200, 40, 40);
		if(playerID==1) {
			lbMyImg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player1.png")));
		}else {
			lbMyImg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player2.png")));
		}
		container.add(lbMyImg);
		
		JLabel lbImgInfo=new JLabel("现在是");
		lbImgInfo.setBounds(610,300,40,40);
		container.add(lbImgInfo);
		lbImg=new JLabel("玩家图例");
		lbImg.setBounds(680, 300, 40, 40);
		container.add(lbImg);
		
		//初始化棋盘
		for(int x=0;x<15;x++) {
			for(int y=0;y<15;y++) {
				datas[x][y]=new JButton();
				datas[x][y].setBorderPainted(false);
			}
		}
		getUpdata();
		setDatasAction();
	}
	
	/**
	 * 设置UDP连接接收，接收数据为对方落子
	 */
	public void getUDPConnect() {
			try {
				if(playerID==1) {
					socket = new DatagramSocket(19962);
				}else {
					socket=new DatagramSocket(19963);
				}
				byte[] data=new byte[1024];

				while (true) {
					packet = new DatagramPacket(data, data.length);
					socket.receive(packet);
					//处理数据
					String strData=new String(packet.getData(),0,packet.getLength());
					String[] strDatas=strData.split("#");
					game.addData(Integer.valueOf(strDatas[0]), 
							Integer.valueOf(strDatas[1]), Integer.valueOf(strDatas[2]));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * set datas action
	 */
	public void setDatasAction() {
		for(int x=0;x<15;x++){
			for(int y=0;y<15;y++){
				setOneDatasAction(x, y);
			}
		}
	}
	
	/**
	 * 设置一个棋格点击事件
	 */
	public void setOneDatasAction(final int x,final int y) {
		datas[x][y].addActionListener(new ActionListener() {
			private DatagramPacket packet;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(game.addData(playerID,x, y)) {
					//添加数据成功，发送数据至对方
					String strData=playerID+"#"+x+"#"+y;
					byte[] addDatas=strData.getBytes();
					try {
						DatagramSocket socket=new DatagramSocket();
						InetAddress address=InetAddress.getByName(strIP);
						if(playerID==1) {
							packet = new DatagramPacket(addDatas, addDatas.length,address,19963);
						}else {
							packet=new DatagramPacket(addDatas, addDatas.length,address,19962);
						}
						socket.send(packet);
						System.out.println(playerID+"发送的数据"+Arrays.toString(addDatas));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * 获取数据重绘界面
	 */
	public void getUpdata() {
		label.setText("现在轮到玩家"+game.waitPlayer);
		if(game.waitPlayer==1) {
			lbImg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player1.png")));
		}else {
			lbImg.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player2.png")));
		}
		int [][] getDatas=game.getDatas();
		
		for(int x=0;x<15;x++) {
			for(int y=0;y<15;y++) {
				if(getDatas[x][y]==0)
					datas[x][y].setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/background.png")));
				if(getDatas[x][y]==1)
					datas[x][y].setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player1.png")));
				if(getDatas[x][y]==2)
					datas[x][y].setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/player2.png")));
			}
		}
		
		//当出现胜利者时，弹出窗口，提示赢家
		if(game.getWinner()!=0) {
			String str="赢家为玩家"+String.valueOf(game.getWinner());
			JOptionPane.showMessageDialog(null, str);
		}
	}
}


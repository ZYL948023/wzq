package lxw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class FiveChessJFrame extends JFrame{
	//判断游戏是否结束
	boolean canPlay = true;
	//棋子的坐标
	int x = 0;
	int y = 0;
	//定义数组存放棋子
	int[][] allChess = new int[19][19];
	//判断棋子的颜色
	boolean isBlack = true;
	int n = 0;
	public FiveChessJFrame(){
		setLayout(null);
		JButton jbStart = new JButton("重新开始");
		jbStart.setBounds(650,500,100,30);
		JButton jbShu = new JButton("认输");
		jbShu.setBounds(650,450,100,30);
		JButton jbHe = new JButton("求和");
		jbHe.setBounds(650,400,100,30);
		JButton jbExit = new JButton("退出");
		jbExit.setBounds(650,550,100,30);
		add(jbExit);
		add(jbStart);
		add(jbHe);
		add(jbShu);
		//求和事件
		jbHe.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JOptionPane.showMessageDialog(null,"平手！");
				canPlay = false;
			}
		});
		//认输事件
		jbShu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(isBlack == true)
					JOptionPane.showMessageDialog(null,"玩家二获胜！");
				else
					JOptionPane.showMessageDialog(null,"玩家一获胜！");
				canPlay = false;
			}
		});
		/**
		 *按钮“重新开始”事件
		 *重新开始
		 */
		jbStart.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				resetChess();
			}
		});
		/**
		 *按钮“退出”事件
		 *退出游戏
		 */
		jbExit.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				new Queren();
			}
		});	
		//添加事件
		addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				System.out.println(e.getX()+","+e.getY());
			}
			public void mousePressed(MouseEvent e){
				if(canPlay == false){
					return;
				}
				x = e.getX();
				y = e.getY();
				if(x >= 12 && x <= 582 && y >= 73 && y < 643){
					x = (x +15- 12)/30;
					y = (y +15- 73)/30;
					if(allChess[x][y] == 0){
						if(isBlack == true){
							allChess[x][y] = 1;
							isBlack = false;
							n = 30;
						}else{
							allChess[x][y] = 2;
							isBlack = true;
							n = 0;
						}
						boolean win = Check_Win();
						System.out.println(win);
						repaint();
						//落子结束后判断输赢
						if(win){
							JOptionPane.showMessageDialog(null, "游戏结束,"  + (allChess[x][y] == 1 ? "黑方" : "白方") + "获胜 ");
							canPlay = false;
						}
					}else{
						
						JOptionPane.showMessageDialog(null, "当前已有棋子,不能再下了!");
					}
				}
				
			}
		});
		
		setTitle("五子棋");
		setSize(800,700);
		setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-400,
				Toolkit.getDefaultToolkit().getScreenSize().height/2-350,
				800, 700);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public void paint(Graphics g){	
		super.paint(g);
		g.setFont(new Font("黑体", Font.BOLD, 20)); 
        g.drawString("游戏信息", 640, 100); 
        g.setFont(new Font("宋体", Font.BOLD, 15)); 
        g.drawString("黑方", 582, 160); 
        g.drawString("白方", 582, 190); 
        //画棋盘 
        //画19条横线 
        int y_index = 0; 
        for (int i = 0; i < 19; i++) { 
            g.drawLine(12, 73 + y_index, 552, 73 + y_index); 
            y_index += 30; 
        } 
        //画19条纵线 
        int x_index = 0; 
        for (int i = 0; i < 19; i++) { 
            g.drawLine(12 + x_index, 73, 12 + x_index, 613); 
            x_index += 30; 
        } 
        //画四个点 
        g.fillOval(98, 159, 8,8); 
        g.fillOval(458, 159, 8,8); 
        g.fillOval(98, 518, 8,8); 
        g.fillOval(458, 518, 8,8); 
        //画全部棋子 
        for (int i = 0; i < 19; i++) { 
            for (int j = 0; j < 19; j++) { 
                if (this.allChess[i][j] == 1) { 
                    //画黑子 
                    int tempX = i * 30 + 12; 
                    int tempY = j * 30 + 73; 
                    g.fillOval(tempX - 10, tempY - 10, 20, 20); 
                } 
                if (this.allChess[i][j] == 2) { 
                    //画白子 
                    int tempX = i * 30 + 12; 
                    int tempY = j * 30 + 73; 
                    g.setColor(Color.WHITE); 
                    g.fillOval(tempX - 10, tempY - 10, 20, 20); 
                    g.setColor(Color.BLACK); 
                    g.drawOval(tempX - 10, tempY - 10, 20, 20); 
                }	
            } 
        } 
		//判断玩家下棋状态
		g.setColor(Color.red);
		g.fillOval(670,150+n,10,10);
		
	}
	
	//判断输赢
	public boolean Check_Win(){
		boolean flag = false; 
        //保存共有多少相同颜色的棋子相连 
        int count = 1; 
		
        //先判断横向,特点，y坐标相同，即allChess[x][y]中y相同 
        //判断这个棋子的颜色 
        int color = this.allChess[x][y]; 
        count = Check_Count(1, 0, color); 
		System.out.println(count);
        if (count >= 5) { 
            flag = true; 
        } else { 
            //判断纵向 
            count = Check_Count(0, 1, color); 
            if (count >= 5) { 
                flag = true; 
            } else { 
                //判断右上，左下 
                count = Check_Count(1, -1, color); 
                if (count >= 5) { 
                    flag = true; 
                } else { 
                    //判断左上，右下 
                    count = Check_Count(1, 1, color); 
                    if (count >= 5) { 
                        flag = true; 
                    } 
                } 
            } 
        } 
        return flag; 
	}
	//得到棋子的数目
	public int Check_Count(int xChange,int yChange,int color){
		
		int count = 1;
		int tempX = xChange;
		int tempY = yChange;
		while(color == this.allChess[x + xChange][y + yChange]){
			count++;
			if(xChange != 0){
				xChange++;
			}
			if(yChange != 0){	
				if (yChange > 0) 
                    yChange++; 
                if (yChange < 0) 
                    yChange--; 
			}
		}
		xChange = tempX;
		yChange = tempY;
		while(color == this.allChess[x - xChange][y - yChange]){
			count++;
			if (xChange != 0) { 
				xChange++; 
			} 
			if (yChange != 0) { 
				if (yChange > 0) 
					yChange++; 
				if (yChange < 0) 
					yChange--; 
				 
			}
		}
		return count;
	}
	//清除棋子
	public void resetChess(){
		for(int i = 0;i < 19;i++){
			for(int j = 0;j < 19;j++){
				this.allChess[i][j] = 0;				
			}
		}
		//恢复游戏相关的变量值
		canPlay = true;
		isBlack = true;
		repaint();
		
	}

}

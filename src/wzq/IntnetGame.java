package wzq;

import java.io.Serializable;

public class IntnetGame{
	
	/*
	 * 棋盘数据规范
	 * 0:空
	 * 1:玩家1落子
	 * 2:玩家2落子
	 */
	int[][] datas=new int[15][15];

	

	//等待落子的玩家ID
	int waitPlayer=1;
	
	//赢家ID
	int winnerID=0;
	
	//初始化棋盘
	public void init() {
		for(int x=0;x<15;x++) {
			for(int y=0;y<15;y++) {
				datas[x][y]=0;
			}
		}
	}

	/**
	 * 下棋
	 * 设置棋盘数据
	 * 玩家waitPlayer落子在(w,h)上
	 * @param x 落子X下标
	 * @param y 落子Y下标
	 */
	public boolean addData(int playerID, int x, int y) {
		if(playerID==waitPlayer) {//试图添加数据的玩家ID和等待ID匹配？
			if(winnerID==0&&datas[x][y]==0) {//可以添加数据？
				datas[x][y] = waitPlayer;
				// 判断
				if (checkV(waitPlayer, x, y) 
						|| checkLeftToRight(waitPlayer, x, y) 
						|| checkRightToLeft(waitPlayer, x, y)
						|| checkH(waitPlayer, x, y)) {
					winnerID = waitPlayer;
					System.out.println("赢家为玩家" + getWinner());
				}
				changePlayer();
				return true;
			}
		}
		return false;
	}
	
	

	private void changePlayer() {
		if(waitPlayer==1) {
			waitPlayer=2;
		}else {
			waitPlayer=1;
		}
	}
	
	/**
	 * 获取棋盘数据
	 */
	public int[][] getDatas() {
		return datas;
	}
	
	/**
	 * 设置棋盘数据
	 */
	public void setDatas(int[][] datas) {
		this.datas=datas;
	}

	/**
	 * 根据落子判断纵向是否成子
	 * @return 是否出现
	 */
	public boolean checkV(int player,int x,int y) {
		int length=1;
		int a,b;
		//上下检查
		//向上
		a=x;
		b=y;
		while(b>0) {
			b--;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		//向下
		a=x;
		b=y;
		while (b<14) {
			b++;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		if(length>=5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 根据落子判断横向是否成子
	 * @return 是否出现
	 */
	public boolean checkH(int player,int x,int y) {
		int length=1;
		int a,b;
		//左右检查
		//向左
		a=x;
		b=y;
		while(a>0) {
			a--;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		//向右
		a=x;
		b=y;
		while (a<14) {
			a++;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		if(length>=5) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * 根据落子判断左上到右下是否成子
	 * @return 是否出现
	 */
	public boolean checkLeftToRight(int player,int x,int y) {
		int length=1;
		int a,b;
		//左上右下检查
		//左上
		a=x;
		b=y;
		while(b>0&&a>0) {
			a--;
			b--;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		//右下
		a=x;
		b=y;
		while (b<14&&a<14) {
			a++;
			b++;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		if(length>=5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 根据落子判断右上左下是否成子
	 * @return 是否出现
	 */
	public boolean checkRightToLeft(int player,int x,int y) {
		int length=1;
		int a,b;
		//上下检查
		//右上
		a=x;
		b=y;
		while(b>0&&a<14) {
			a++;
			b--;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		//下左
		a=x;
		b=y;
		while (b<14&&a>0) {
			a--;
			b++;
			if(datas[a][b]==player) {
				length++;
			}else {
				break;
			}
		}
		if(length>=5) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 获取赢家ID
	 * @return 赢家ID
	 */
	public int getWinner() {
		return winnerID;
	}
}

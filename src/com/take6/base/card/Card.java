package com.take6.base.card;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.take6.base.user.Player;

/**
 * 创建游戏卡牌类，该类是用户及ai及游玩区域中的牌
 * @author 余周锦
 * @version 1.0
 * 2014-12-10
*/
@SuppressWarnings("serial")
public class Card extends JLabel{
	
	/** 定义牌类的属性*/
	private int number = 0;//牌面的数值，是牌类的唯一识别
	private int score = 0;//牌面的分数，由牌面的数值决定其大小
	private URL path = null;//牌面的图片路径，用来显示不同的牌的不同效果
	private Player owner = null;//当前持有该牌的用户
	private boolean selectState = false;//当前此牌是否处于被选中的状态
	private int cardLocation;//当前此牌的位置

	/** 定义构造函数
	 * @param number 纸牌的数值，用来构造唯一的纸牌对象
	 * */
	public Card(int number){
		this.number = number;
		String pathName = "/com/take6/source/picture/"+number+".png";
		setName(""+number);
		setSize(100,140);
		path = this.getClass().getResource(pathName);
		Image img = Toolkit.getDefaultToolkit().getImage(path);
		ImageIcon icon = new ImageIcon(img);
		setIcon(icon);
	}
	
	//为属性添加方法
	public int getNumber() {
		return number;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public boolean isSelectState() {
		return selectState;
	}

	public void setSelectState(boolean selectState) {
		this.selectState = selectState;
	}
	
	public int getCardLocation() {
		return cardLocation;
	}

	public void setCardLocation(int cardLocation) {
		this.cardLocation = cardLocation;
	}
	
}

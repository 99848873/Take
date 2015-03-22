package com.take6.base.action;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import javax.swing.JPanel;
import com.take6.base.card.Card;
import com.take6.swing.PlayArea;
import com.take6.system.StartPlay;


/**
 * 实现给手牌添加的鼠标监听器
 * @author 余周锦
 * @version 1.0
 * 2014-12-17
*/
public class SelectCard implements MouseListener{
	
	/**定义私有变量，确认需要添加的属性*/
	private StartPlay stratPlay;
	private Card card;
	private PlayArea playArea;
	private JPanel playerPanel;
	JButton pushButton = null;

	/**构造函数,初始化需要的属性
	 * @param stratPlay 游玩区域的引用
	 * @param card 需要被玩家打出的手牌
	 * */
	public SelectCard(StartPlay stratPlay, Card card) {
		
		this.stratPlay = stratPlay;
		this.card = card;
		this.playArea = stratPlay.getPlayArea();
		this.playerPanel = playArea.getPlayerPanel();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == 1){
			//判断当前区域是否是允许出牌状态，以及当前卡牌是否处于已经被选择状态
			if(playArea.isReadyState() == true&&card.isSelectState() ==false){
				selectCard();
			}else if(playArea.isReadyState() == false&&card.isSelectState() == true){
				cancleCard();
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//无监听效果
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//无监听效果
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// 当卡牌处于没有被选择的状态下，如果鼠标移入，将卡牌上移10个像素
		if(card.isSelectState() == false&&playArea.isReadyState() == true){
			card.setLocation(card.getX(), 0);
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// 当卡牌处于没有被选择的状态下，如果鼠标移出，将卡牌下移10个单位
		if(card.isSelectState() == false&&playArea.isReadyState() == true){
			card.setLocation(card.getX(), 10);
		}
		
	}
	
	
	/**选中卡牌，改变当前界面状态及卡牌状态*/
	private void selectCard(){
		
		System.out.println("被选中的牌是"+card.getName());
		pushButton = new JButton("出牌");
		playArea.setReadyState(false);
		card.setSelectState(true);
		card.setLocation(card.getX(), 0);
		pushButton.addActionListener(new PushCard(stratPlay,card,pushButton));
		pushButton.setBounds(1120, 560, 100, 35);
		playArea.add(pushButton);
		playerPanel.repaint();
		
	}
	
	/**取消选中卡牌，改变当前界面状态及卡牌状态*/
	private void cancleCard(){
		
		playArea.setReadyState(true);
		card.setSelectState(false);
		card.setLocation(card.getX(), 10);
		playArea.remove(pushButton);
		playArea.repaint();	
		
	}
	

}

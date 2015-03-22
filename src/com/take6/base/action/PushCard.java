package com.take6.base.action;

import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JButton;

import com.take6.base.card.Card;
import com.take6.system.Display;
import com.take6.system.StartPlay;

/**
 * 实现出牌按钮响应动作
 * @author 余周锦
 * @version 1.0
 * 2014-12-17
*/
public class PushCard implements ActionListener{
	
	/**定义私有变量，需要使用到的显示类引用*/
	private Display display;
	private StartPlay stratPlay;
	private JButton pushButton;
	private Card card;
	
	/**构造函数
	 * @param stratPlay 游玩区域的引用
	 * @param card 需要被玩家打出的手牌
	 * @param pushButton 当前被使用的按钮
	 * */
	public PushCard(StartPlay stratPlay, Card card, JButton pushButton) {
		this.card = card;
		this.stratPlay = stratPlay;
		this.pushButton = pushButton;
		display = new Display(stratPlay,card);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//在移动手牌前就将按钮移除避免用户操作
		stratPlay.getPlayArea().remove(pushButton);
		stratPlay.getPlayArea().repaint();
		
		//播放出牌音乐
		URL musicPath = this.getClass().getResource("/com/take6/source/music/pushcard.wav");
					
		try{
			AudioClip playMusic = JApplet.newAudioClip(musicPath);
			playMusic.play();
		}catch(Exception ex){
			System.out.println("不能播放背景音乐");
		}
		
		//移除手牌中所有的监听器
		for(int i =0 ;i<card.getMouseListeners().length;i++){
			
			card.removeMouseListener(card.getMouseListeners()[i]);
			
		}	
		
		display.moveCard();		
			
	}
	
}



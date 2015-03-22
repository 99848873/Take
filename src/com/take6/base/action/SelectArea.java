package com.take6.base.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.take6.base.card.Card;
import com.take6.swing.CardArea;
import com.take6.system.Display;

/**
 * 实现选择位置按钮响应动作
 * @author 余周锦
 * @version 1.0
 * 2014-12-24
*/
public class SelectArea implements ActionListener{
	
	/**定义私有变量，确认需要添加的属性*/
	private CardArea cardArea;
	private Card card;
	private Display display;
	
	/**构造函数,初始化需要的属性
	 * @param cardArea 位置引用
	 * @param card 需要被玩家打出的手牌
	 * */
	public SelectArea(CardArea cardArea, Card card){
		this.cardArea = cardArea;
		this.card = card;
	}

	public SelectArea(Display display) {
		this.display = display;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand().equals("auto")){
			display.setAuto(true);
		}else
		card.setCardLocation(cardArea.getCards().get(0).getCardLocation());
		
	}

	
}

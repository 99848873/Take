package com.take6.swing;

import java.awt.Component;

import javax.swing.JPanel;

import com.take6.base.card.Card;
import com.take6.base.card.Cards;

/**
 * 创建游玩区域中牌摆放位置的类，这个类直接绑定一个Cards类型的集合类，这样在界面上添加和删除Card的时候同时可以将牌组中的Card删除
 * @author 余周锦
 * @version 1.0
 * 2014-12-15
*/
@SuppressWarnings("serial")
public class CardArea extends JPanel{
	
	/**定义捆绑的牌组对象*/
	private Cards cards = null;
	private int location = 0;
	
	/**
	 * 创建游玩区域中牌摆放类构造函数，确认其location
	 * 当创建一个区域的同时，在区域内创建了一个捆绑在一起的牌组
	 * @param location 创建一个卡牌区域需要给他定一个区域识别标示
	 * */
	public CardArea(int location){
		super();
		this.location = location;
		cards = new Cards();
	}
	
	//重构其add方法，在add新的card的同时，将其添加到panel中,并且将其location传给card
	public Component add(Card card) {
		
		card.setCardLocation(location);
		cards.add(card);
		return super.add(card);
	}
	

	//重构其remove方法，在删除card的同时，将其从panel中删除
	public void remove(Card card) {
		cards.remove(card);
		super.remove(card);
	}
	
	//重构其removeAll方法，在删除所有card的同时，将其从panel中删除
	public void removeAll() {
		
		cards.clear();
		super.removeAll();
		
	}
	
	public Cards getCards() {
		return cards;
	}
	
	
}
